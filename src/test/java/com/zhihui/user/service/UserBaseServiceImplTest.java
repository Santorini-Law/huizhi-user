package com.zhihui.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huizhi.rpc.RpcIdGenerationService;
import com.huizhi.rpc.model.IdGenerationRequestDTO;
import com.huizhi.rpc.model.IdGenerationResponseDTO;
import com.zhihui.user.dao.UserBaseDAO;
import com.zhihui.user.dao.UserDAO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserBaseExtraDO;
import com.zhihui.user.domain.UserDO;
import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.domain.enums.UserRoleEnum;
import com.zhihui.user.service.api.IUserBaseService;
import com.zhihui.user.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Slf4j
class UserBaseServiceImplTest {

    @Resource
    IUserBaseService userBaseService;

    @Test
    void testGetUserBaseInfoByMobile() {
        UserBaseDO userBaseInfoByMobile = userBaseService.getUserBaseInfoByMobile("18629670402");
        log.info("uid = {}", userBaseInfoByMobile);
    }

    @Test
    void testGetUserBaseInfoByUid() {
        UserBaseDO userBaseInfoByMobile = userBaseService.getUserBaseInfoByUid(6296120001507040L);
        log.info("uid = {}", userBaseInfoByMobile);
    }

    @Test
    void testInsertUserBaseInfo() {
        UserBaseDO userBaseDO = new UserBaseDO();
        IdGenerationRequestDTO re = new IdGenerationRequestDTO();
        re.setMobile("18629670402");
        IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(re);
        userBaseDO.setUid(idGenerationResponseDTO.getId());
        userBaseDO.setUserRole(UserRoleEnum.INNER);
        userBaseDO.setRegisterSource(RegisterSourceEnum.INIT);
        userBaseDO.setUserName("father");
        userBaseDO.setNickName("yinglishzhi");
        userBaseDO.setGender(GenderEnum.MALE);
        userBaseDO.setBirthday(LocalDate.of(1992, 04, 02));
        userBaseDO.setMobile("18629670402");
        userBaseDO.setMobileBindTime(LocalDateTime.now());
        userBaseDO.setEmail("yinglishzhi@gmail.com");
        userBaseDO.setEmailBindTime(LocalDateTime.now());
        userBaseDO.setCreateTime(LocalDateTime.now());
        userBaseDO.setUpdateTime(LocalDateTime.now());
        userBaseDO.setRealName("劉德志");
        userBaseDO.setIdCard("232301199204020010");
        UserBaseExtraDO userBaseExtraDO = new UserBaseExtraDO();
        userBaseExtraDO.setStature(new BigDecimal(185));
        userBaseExtraDO.setWeight(new BigDecimal(88));
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

    @Resource
    IUserService userService;

    @Reference
    RpcIdGenerationService rpcIdGenerationService;


    @Test
    void washData() {

        // 1000个号码
        List<UserDO> userListByOffset = userService.getUserListByOffset(0);

        List<String> mobileList = userService.getMobile();

//        Map<String, Integer> m = mobileList.stream().


        for (UserDO user : userListByOffset) {

            Long uid;
            try {
                IdGenerationRequestDTO idGenerationRequestDTO = new IdGenerationRequestDTO();
                idGenerationRequestDTO.setMobile(user.getMobile());
                IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(idGenerationRequestDTO);
                uid = idGenerationResponseDTO.getId();
            } catch (Exception e) {
                log.error("user = {} 获取id失败", user.toString(), e);
                continue;
            }

            UserBaseDO userBaseDO = new UserBaseDO();
            userBaseDO.setUid(uid);
            userBaseDO.setUserRole(UserRoleEnum.NORMAL);
            userBaseDO.setRegisterSource(RegisterSourceEnum.INIT);
            userBaseDO.setUserName("");
            userBaseDO.setNickName(user.getNickname());
            GenderEnum genderEnum = user.getSex() == 1 ? GenderEnum.MALE : user.getSex() == 2 ? GenderEnum.FEMALE : null;
            userBaseDO.setGender(genderEnum);
            String birthday = user.getBirthday();
            LocalDate b = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            userBaseDO.setBirthday(b);
            userBaseDO.setMobile(user.getMobile());
            userBaseDO.setMobileBindTime(user.getAddTime());
            userBaseDO.setEmail(user.getEmail());
            userBaseDO.setEmailBindTime(user.getAddTime());
            userBaseDO.setCreateTime(user.getAddTime());
            userBaseDO.setUpdateTime(user.getAddTime());
            userBaseDO.setRealName(user.getRealName());
            userBaseDO.setIdCard(user.getIdCard());
            UserBaseExtraDO userBaseExtraDO = new UserBaseExtraDO();
            userBaseExtraDO.setStature(Optional.of(user.getTall()).map(BigDecimal::new).orElse(null));
            userBaseExtraDO.setWeight(Optional.of(user.getHeavy()).map(BigDecimal::new).orElse(null));
            userBaseDO.setBaseExtra(userBaseExtraDO);

        }

    }


}