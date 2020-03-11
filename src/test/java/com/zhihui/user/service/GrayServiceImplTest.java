package com.zhihui.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huizhi.rpc.RpcIdGenerationService;
import com.huizhi.rpc.model.IdGenerationResponseDTO;
import com.zhihui.user.service.api.GrayService;
import com.zhihui.user.vo.GrayCheckRequestVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GrayServiceImplTest {

    @Resource
    GrayService grayService;

    @Reference
    private RpcIdGenerationService rpcIdGenerationService;

    @Test
    public void rpcTest() {
        IdGenerationResponseDTO idGenerationResponseDTO = rpcIdGenerationService.generateUid(null);
        System.out.println(idGenerationResponseDTO.getId());
    }


    @Test
    void checkInGray() {
        GrayCheckRequestVO request = new GrayCheckRequestVO();
        request.setGrayId(200L);
        request.setUid(23230179L);
        boolean gray = grayService.checkInGray(request);
        try {
            Method oldMethod = GrayServiceImplTest.class.getDeclaredMethod("oldLogic", int.class, String.class, String.class);
            Method newMethod = GrayServiceImplTest.class.getDeclaredMethod("newLogic", String.class, Long.class);
            M oldM = new M(oldMethod, new Object[]{1, "1", "2"});
            M newM = new M(newMethod, new Object[]{"1", 2L});
            R s = executeGrayFunciton(request, new GrayServiceImplTest(), oldM, newM);
            System.out.println(s.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public R oldLogic(int a, String b, String c) {
        return new R(b, (long) a, Integer.valueOf(c));
    }

    public R newLogic(String a, Long b) {
        return new R(a, b, Integer.valueOf(a + b));
    }

    class M {
        Method method;

        Object[] object;

        public M(Method method, Object[] object) {
            this.method = method;
            this.object = object;
        }
    }

    public <T> T executeGrayFunciton(GrayCheckRequestVO request, Object o, M oldLogic, M newLogic) {
        boolean gray = grayService.checkInGray(request);
        Object result = null;
        try {
            if (gray) {
                result = newLogic.method.invoke(o, newLogic.object);
            } else {
                result = oldLogic.method.invoke(o, oldLogic.object);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T) result;
    }


    public String oldLogic(int i, double d) {
        return String.valueOf(i * d);
    }


    public String newLogic(String a, BigDecimal b) {
        return a + b.toPlainString();
    }


    @Data
    @AllArgsConstructor
    class R {
        String a;

        Long b;

        Integer c;
    }


}