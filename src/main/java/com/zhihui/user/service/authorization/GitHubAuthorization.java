package com.zhihui.user.service.authorization;

import com.google.gson.Gson;
import com.netflix.ribbon.proxy.annotation.Http;
import com.zhihui.user.config.properties.GithubProperties;
import com.zhihui.user.service.api.ThirdAuthorization;
import com.zhihui.user.vo.GithubUserInfo;
import com.zhihui.utils.http.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LDZ
 * @date 2020-03-19 20:22
 */
@Service("gitHubAuthentication")
public class GitHubAuthorization implements ThirdAuthorization {

    @Resource
    GithubProperties githubProperties;


    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    @Override
    public Long getUserId(String githubCode) {

        Map<String, String> requestEntity = new HashMap<>(4);
        requestEntity.put("client_id", githubProperties.getClientId());
        requestEntity.put("client_secret", githubProperties.getClientSecret());
        requestEntity.put("code", githubCode);
        String message = HttpUtils.postForm(GITHUB_ACCESS_TOKEN_URL, requestEntity);

        String access_token = message.split("&")[0].split("=")[1];

        if (access_token == null || "".equals(access_token)) {
            throw new RuntimeException(message);
        }
//
        Map<String, String> request = new HashMap<>(2);
        request.put("access_token", access_token);
        String userInfo = HttpUtils.get(GITHUB_USER_URL, request);

        try {

            GithubUserInfo githubUserInfo = new Gson().fromJson(userInfo, GithubUserInfo.class);

            String login = githubUserInfo.getLogin();

            if (login == null) {
                throw new RuntimeException(githubUserInfo.toString());
            }

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
}
