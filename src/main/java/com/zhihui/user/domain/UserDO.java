package com.zhihui.user.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * UserDO bean
 *
 * @author LDZ
 * @date 2020-03-09 23:08
 */
@Data
public class UserDO {

    Long uid;
    LocalDateTime addTime;//datetime               null comment '创建时间',
    LocalDateTime modifyTime;//datetime               null comment '信息修改时间',
    String email;//varchar(100)           null comment '邮件地址',
    Integer sex;//tinyint                null comment '性别 1:男  2:女',
    String realName;//varchar(50)            null comment '真实姓名',
    String idCard;//varchar(18)            null comment '身份证',
    Integer isAut;//tinyint     default 0  null comment '是否认证（0未验证，1验证通过，-1验证未通过）',
    String autBz;//varchar(300)           null comment '验证备注',
    String mobile;//varchar(40) default '' not null,
    String nickname;//varchar(512)           null,
    String password;//varchar(100)           null comment '密码 md5',
    Integer type;//tinyint     default 1  null comment '用户类型，1下游买家',
    Integer isEmail;//tinyint                null comment '是否接收邮件',
    String invitationCode;//varchar(50)            null comment '邀请码',
    String uuid;//varchar(50)            null comment '微信uuid',
    LocalDateTime payEndDate;//datetime               null comment '包月结束日期',
    String defaultSize;//varchar(50)            null comment '默认尺码',
    Long defaultAddr;//int                    null comment '默认地址id',
    String profession;//varchar(100)           null comment '职业',
    String birthday;//varchar(100)           null comment '生日',
    Integer tall;//int                    null comment '身高',
    Integer heavy;//int                    null comment '体重',
    Integer refereeUid;//int                    null comment '推荐人uid',
    String source;//varchar(100)           null comment '来源',
    Integer memberType;//tinyint     default 0  null comment '0未付费,1体验会员2，包月会员,3次数会员',
    String payHavaNub;//int         default 0  null comment '次数会员有效次数',
    LocalDateTime havaNubEndDate;//datetime               null comment '次数会员截至日期',
    String fromType;//tinyint                null comment '0,未知，1微信，2web，3IOS，4安卓，5其他 6淘宝',
    Integer acrossChest;//int                    null comment '胸围',
    Integer waist;//int                    null comment '腰围',
    Integer hipline;//int                    null comment '臀围',

    @Override
    public String toString() {
        return "UserDO{" +
                "uid=" + uid +
                ", addTime=" + addTime +
                ", modifyTime=" + modifyTime +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", isAut=" + isAut +
                ", autBz='" + autBz + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", isEmail=" + isEmail +
                ", invitationCode='" + invitationCode + '\'' +
                ", uuid='" + uuid + '\'' +
                ", payEndDate=" + payEndDate +
                ", defaultSize='" + defaultSize + '\'' +
                ", defaultAddr=" + defaultAddr +
                ", profession='" + profession + '\'' +
                ", birthday='" + birthday + '\'' +
                ", tall=" + tall +
                ", heavy=" + heavy +
                ", refereeUid=" + refereeUid +
                ", source='" + source + '\'' +
                ", memberType=" + memberType +
                ", payHavaNub='" + payHavaNub + '\'' +
                ", havaNubEndDate=" + havaNubEndDate +
                ", fromType='" + fromType + '\'' +
                ", acrossChest=" + acrossChest +
                ", waist=" + waist +
                ", hipline=" + hipline +
                '}';
    }
}
