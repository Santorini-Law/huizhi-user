package com.zhihui.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huizhi.rpc.RpcIdGenerationService;
import com.huizhi.rpc.model.IdGenerationRequestDTO;
import com.huizhi.rpc.model.IdGenerationResponseDTO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserBaseExtraDO;
import com.zhihui.user.domain.UserDO;
import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.domain.enums.UserRoleEnum;
import com.zhihui.user.service.api.IUserBaseService;
import com.zhihui.user.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class UserBaseServiceImplTest {

    @Resource
    IUserBaseService userBaseService;

    @Resource
    IUserService userService;

    @Reference
    RpcIdGenerationService rpcIdGenerationService;

    @Test
    void testGetUserBaseInfoByMobile() {
        UserBaseDO userBaseInfoByMobile = userBaseService.getUserBaseInfoByMobile("18629670402");
        log.info("uid = {}", userBaseInfoByMobile);
    }

    @Test
    void testGetUserBaseInfoByUid() {

        UserBaseDO userBaseInfoByMobile = userBaseService.getUserBaseInfoByUid(6296130595507040L);
        log.info("uid = {}", userBaseInfoByMobile);
    }

    @Test
    void testGetUserBaseInfoByEmail() {
        List<UserBaseDO> userBaseInfoByMobile = userBaseService.getUserBaseInfoByEmail("a@a.com");

        for (UserBaseDO userBaseDO : userBaseInfoByMobile) {
            log.info("uid = {}", userBaseDO);
        }
    }

    @Test
    void testRpc() {
        IdGenerationRequestDTO re = new IdGenerationRequestDTO();
        re.setMobile("18342206526");
        IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(re);
        log.info("{}", idGenerationResponseDTO.getId());
    }

    @Test
    void testInsertUserBaseInfo() {
        UserBaseDO userBaseDO = new UserBaseDO();
        IdGenerationRequestDTO re = new IdGenerationRequestDTO();
        re.setMobile("18342206526");
        IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(re);
        userBaseDO.setUid(idGenerationResponseDTO.getId());
        userBaseDO.setUserRole(UserRoleEnum.INNER);
        userBaseDO.setRegisterSource(RegisterSourceEnum.INIT);
        userBaseDO.setUserName("mother");
        userBaseDO.setNickName("huihui");
        userBaseDO.setGender(GenderEnum.FEMALE);
        userBaseDO.setBirthday(LocalDate.of(1992, 07, 03));
        userBaseDO.setMobile("18342206526");
        userBaseDO.setMobileBindTime(LocalDateTime.now());
        userBaseDO.setEmail("belivar@gmail.com");
        userBaseDO.setEmailBindTime(LocalDateTime.now());
        userBaseDO.setCreateTime(LocalDateTime.now());
        userBaseDO.setUpdateTime(LocalDateTime.now());
        userBaseDO.setRealName("杨惠");
        userBaseDO.setIdCard("220702199207030825");
        UserBaseExtraDO userBaseExtraDO = new UserBaseExtraDO();
        userBaseExtraDO.setStature(new BigDecimal(168));
        userBaseExtraDO.setWeight(new BigDecimal(50.1));
        userBaseDO.setBaseExtra(userBaseExtraDO);
        userBaseService.insertUserBaseInfo(userBaseDO);
    }


    @Test
    void batchInsertUserBaseInfo() {

        List<UserBaseDO> userBaseDOList = new ArrayList<>();
        for (long i = 64; i < 129; i++) {
            UserBaseDO userBaseDO = new UserBaseDO();
            userBaseDO.setUserRole(UserRoleEnum.NORMAL);
            userBaseDO.setRegisterSource(RegisterSourceEnum.MOBILE);
            userBaseDO.setGender(GenderEnum.FEMALE);
            userBaseDO.setBirthday(LocalDate.now());
            userBaseDO.setMobile("13123123123");
            userBaseDO.setMobileBindTime(LocalDateTime.now());
            userBaseDO.setEmail("");
            userBaseDO.setEmailBindTime(LocalDateTime.now());
            userBaseDO.setCreateTime(LocalDateTime.now());
            userBaseDO.setUpdateTime(LocalDateTime.now());
            userBaseDO.setRealName("");
            userBaseDO.setIdCard("");
            userBaseDO.setBaseExtra(null);
            userBaseDO.setUid(i);
            userBaseDO.setUserName("userName" + i);
            userBaseDO.setNickName("nickName" + i);
            userBaseDOList.add(userBaseDO);
        }
        System.out.println(userBaseDOList);
        userBaseService.batchInsertUserBaseInfo(userBaseDOList);

    }

    @Test
    void washData() {

        // 1000个号码
        List<UserDO> userListByOffset = userService.getUserListByOffset(1000);

        List<String> mobileList = userService.getMobile();

        List<UserBaseDO> userBaseDOS = new ArrayList<>();

        for (UserDO user : userListByOffset) {
            log.info("user = {}", user.toString());

            Long uid;
            try {
                if (!CollectionUtils.isEmpty(mobileList) && mobileList.contains(user.getMobile())) {
                    throw new RuntimeException("手机号重复了");
                }
                IdGenerationRequestDTO idGenerationRequestDTO = new IdGenerationRequestDTO();
                idGenerationRequestDTO.setMobile(user.getMobile());
                IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(idGenerationRequestDTO);
                uid = idGenerationResponseDTO.getId();
                UserBaseDO userBaseDO = new UserBaseDO();
                userBaseDO.setUid(uid);
                userBaseDO.setUserRole(UserRoleEnum.NORMAL);
                userBaseDO.setRegisterSource(RegisterSourceEnum.INIT);
                userBaseDO.setUserName("");
                userBaseDO.setNickName(Optional.ofNullable(user.getNickname()).orElse(""));
                GenderEnum genderEnum = Optional.ofNullable(user.getSex()).map(a -> a == 11 ? GenderEnum.MALE : a == 2 ? GenderEnum.FEMALE : GenderEnum.UNKNOWN).orElse(GenderEnum.UNKNOWN);
                userBaseDO.setGender(genderEnum);
                String birthday = user.getBirthday();
                LocalDate b = Optional.ofNullable(birthday).map(a -> LocalDate.parse(a, DateTimeFormatter.ofPattern("yyyy-M-d"))).orElse(null);
                userBaseDO.setBirthday(b);
                userBaseDO.setMobile(user.getMobile());
                userBaseDO.setMobileBindTime(user.getAddTime());
                userBaseDO.setEmail(Optional.ofNullable(user.getEmail()).orElse(""));
                userBaseDO.setEmailBindTime(user.getAddTime());
                userBaseDO.setCreateTime(Optional.ofNullable(user.getAddTime()).orElse(LocalDateTime.now()));
                userBaseDO.setUpdateTime(Optional.ofNullable(user.getAddTime()).orElse(LocalDateTime.now()));
                userBaseDO.setRealName(Optional.ofNullable(user.getRealName()).orElse(""));
                userBaseDO.setIdCard(Optional.ofNullable(user.getIdCard()).orElse(""));
                UserBaseExtraDO userBaseExtraDO = new UserBaseExtraDO();
                userBaseExtraDO.setStature(Optional.of(user).map(UserDO::getTall).map(BigDecimal::new).orElse(null));
                userBaseExtraDO.setWeight(Optional.of(user).map(UserDO::getHeavy).map(BigDecimal::new).orElse(null));
                userBaseDO.setBaseExtra(userBaseExtraDO);
                mobileList.add(user.getMobile());
                userBaseDOS.add(userBaseDO);
            } catch (Exception e) {
                log.error("user = {} 获取id失败", user.toString(), e);
            }
        }

        userBaseService.batchInsertUserBaseInfo(userBaseDOS);

    }


}