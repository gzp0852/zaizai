package com.fiberhome.filink.gateway_security.login;

import com.fiberhome.filink.bean.Result;
import com.fiberhome.filink.bean.ResultCode;
import com.fiberhome.filink.bean.ResultUtils;
import com.fiberhome.filink.gateway_security.Const.UserConst;
import com.fiberhome.filink.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.SetUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 *验证请求
 *
 * @author yuanyao@wistronits.com
 * create on 2018/12/30 3:46 PM
 */
@Slf4j
@RestController
public class AuthenticationRequire {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 需要身份认证时 跳转到这个方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);



        if (savedRequest != null) {
            String[] tokens = savedRequest.getParameterValues("token");
            String accessToken = tokens[UserConst.TOKEN_SITE];
            Set<String> keys = RedisUtils.keys(UserConst.REDIS_USER_PREFIX);
                //+ "/^([a-fA-F0-9]{32})$/" + accessToken);
            String userKey = keys.stream().filter(key -> key.endsWith(accessToken)).findFirst().orElseGet(null);

            //找不到登录信息
            if(StringUtils.isEmpty(userKey)){
                return ResultUtils.warn(ResultCode.FAIL,"需要登录");
            }

            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：" + redirectUrl);
            // 跳转到页面 到时候删掉
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                // 用这个做跳转
                redirectStrategy.sendRedirect(request, response, "/sign.html");
            }
        }
        // todo 后面修改成没登录提示
        return ResultUtils.warn(ResultCode.FAIL,"需要登录");

    }
}
