package com.shuaibi.zaizaiuser.zaizai-user.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZaizaiUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 登录账号名
     */
    private String loginName;

    /**
     * 用户类型
     */
    private Integer userRole;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 用户性别（-1女 0未知 1男）
     */
    private String userSex;

    /**
     * 头像路径
     */
    private String userImg;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 账号状态（0正常 1停用）
     */
    private String userStatus;

    /**
     * 是否删除（0存在 1删除 ）
     */
    private String delete;

    /**
     * 最后登录ip
     */
    private byte[] loginIp;

    /**
     * 最后登录时间
     */
    private String loginDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 备注
     */
    private String remark;


}
