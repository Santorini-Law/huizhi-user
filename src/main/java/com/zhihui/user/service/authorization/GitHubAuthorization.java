package com.zhihui.user.service.authorization;

import com.google.gson.Gson;
import com.zhihui.user.config.properties.GithubProperties;
import com.zhihui.user.service.api.ThirdAuthorization;
import com.zhihui.user.vo.GithubUserInfo;
import com.zhihui.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author LDZ
 * @date 2020-03-19 20:22
 */
@Service("gitHubAuthentication")
@Slf4j
public class GitHubAuthorization implements ThirdAuthorization {

    @Resource
    GithubProperties githubProperties;


    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    @Override
    public Long getUserId(String githubCode) {
        log.info("get github user id request github code is {}", githubCode);

        String accessToken = null;
        try {
            // access token
            accessToken = getGithubAccessToken(githubCode);
            // github user info
            GithubUserInfo githubUserInfo = getGithubUserInfo(accessToken);
            // 登陆名
            String login = Optional.of(githubUserInfo).map(GithubUserInfo::getLogin).orElseThrow(() -> new RuntimeException(githubUserInfo.toString()));
            //


        } catch (Exception e) {

        }


        try {



            // 根据 github login 搜索用户信息
//            userEntity = userDao.getEntityByGithubid(login);

//            if (userEntity == null) {
//                return insertUser(githubUserInfo);
//            } else {
//                return String.valueOf(userEntity.getId());
//            }
            return null;
        } catch (Exception e) {
            // ignore
        }
//
        return null;
    }


    //  ============================== private ==============================

    private String getGithubAccessToken(String githubCode) {

        Map<String, String> requestEntity = new HashMap<>(4);
        requestEntity.put("client_id", githubProperties.getClientId());
        requestEntity.put("client_secret", githubProperties.getClientSecret());
        requestEntity.put("code", githubCode);
        String message = HttpUtils.postForm(GITHUB_ACCESS_TOKEN_URL, requestEntity);
        String accessToken = message.split("&")[0].split("=")[1];
        return Optional.of(accessToken).filter(s -> !StringUtils.isEmpty(s)).orElseThrow(() -> new RuntimeException(message));
    }

    /**
     * 获取 github 用户信息
     *
     * @param accessToken github access token
     * @return github 用户信息
     */
    private GithubUserInfo getGithubUserInfo(String accessToken) {

        Map<String, String> request = new HashMap<>(2);
        request.put("access_token", accessToken);
        String userInfo = HttpUtils.get(GITHUB_USER_URL, request);
        return new Gson().fromJson(userInfo, GithubUserInfo.class);

    }
}
