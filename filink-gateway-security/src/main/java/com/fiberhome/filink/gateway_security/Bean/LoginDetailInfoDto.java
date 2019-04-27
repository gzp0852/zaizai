package com.fiberhome.filink.gateway_security.Bean;

import com.fiberhome.filink.menuapi.bean.MenuTemplateAndMenuInfoTree;
import com.fiberhome.filink.user_api.bean.User;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.Serializable;

@Data
public class LoginDetailInfoDto implements Serializable{

    private OAuth2AccessToken accessToken;

    private MenuTemplateAndMenuInfoTree showMenuTemplate;

    private Object LoginInfo;

    public LoginDetailInfoDto(OAuth2AccessToken accessToken, MenuTemplateAndMenuInfoTree showMenuTemplate, Object loginInfo) {
        this.accessToken = accessToken;
        this.showMenuTemplate = showMenuTemplate;
        LoginInfo = loginInfo;
    }
}
