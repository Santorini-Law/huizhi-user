package com.zhihui.user.domain;

import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.domain.enums.UserRoleEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author LDZ
 * @date 2020-03-06 18:18
 */
@Data
public class UserBaseDO {

    /**
     * 用户ID
     */
    Long uid;

    /**
     * 2 正常用户 3 禁言用户 4 虚拟用户 5 运营
     */
    UserRoleEnum userRole;

    /**
     * 注册来源：1手机号 2邮箱 3用户名 4 qq 5微信 6腾讯微博 7新浪微博
     */
    RegisterSourceEnum registerSource;

    /**
     * 用户账号，必须唯一
     */
    String userName;

    /**
     * 用户昵称
     */
    String nickName;

    /**
     * 用户性别 0-female 1-male
     */
    GenderEnum gender;

    /**
     * 用户生日
     */
    LocalDate birthday;

    /**
     * 手机号码(唯一)
     */
    String mobile;

    /**
     * 手机号码绑定时间
     */
    LocalDateTime mobileBindTime;

    /**
     * 邮箱(唯一)
     */
    String email;

    /**
     * 邮箱绑定时间
     */
    LocalDateTime emailBindTime;

    /**
     * 创建时间
     */
    LocalDateTime createTime;

    /**
     * 修改时间
     */
    LocalDateTime updateTime;

    /**
     * 实名
     */
    String realName;

    /**
     * 身份证
     */
    String idCard;

    /**
     * 基础信息额外信息
     */
    UserBaseExtraDO baseExtra;

    @Override
    public String toString() {
        return "UserBaseDO{" +
                "uid=" + uid +
                ", userRole=" + userRole +
                ", registerSource=" + registerSource +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", mobile='" + mobile + '\'' +
                ", mobileBindTime=" + mobileBindTime +
                ", email='" + email + '\'' +
                ", emailBindTime=" + emailBindTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", baseExtra=" + baseExtra +
                '}';
    }
}
