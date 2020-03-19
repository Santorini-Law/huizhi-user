package com.zhihui.user.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * github 用户信息
 *
 * @author LDZ
 * @date 2020-03-19 22:03
 */
@Data
public class GithubUserInfo {

    /**
     * 用户
     */
    String login;

    /**
     * id
     */
    Long id;

    /**
     * node_id
     */
    @SerializedName("node_id")
    String nodeId;

    /**
     *
     */
    @SerializedName("avatar_url")
    String avatarUrl;

    /**
     * Globally Recognized Avatar
     */
    @SerializedName("gravatar_id")
    String gravatarId;

    /**
     *
     */
    String url;

    /**
     *
     */
    @SerializedName("html_url")
    String htmlUrl;

    /**
     *
     */
    @SerializedName("followers_url")
    String followersUrl;

    /**
     *
     */
    @SerializedName("following_url")
    String followingUrl;

    /**
     *
     */
    @SerializedName("gists_url")
    String gistsUrl;

    /**
     *
     */
    @SerializedName("starred_url")
    String starredUrl;

    /**
     *
     */
    @SerializedName("subscriptions_url")
    String subscriptionsUrl;

    /**
     *
     */
    @SerializedName("organizations_url")
    String organizationsUrl;

    /**
     *
     */
    @SerializedName("repos_url")
    String reposUrl;

    /**
     *
     */
    @SerializedName("events_url")
    String eventsUrl;

    /**
     *
     */
    @SerializedName("received_events_url")
    String receivedEventsUrl;

    /**
     *
     */
    String type;

    /**
     *
     */
    @SerializedName("site_admin")
    String siteAdmin;

    /**
     *
     */
    String name;

    /**
     *
     */
    String company;

    /**
     *
     */
    String blog;

    /**
     *
     */
    String location;

    /**
     *
     */
    String email;

    /**
     *
     */
    String hireable;

    /**
     *
     */
    String bio;

    /**
     *
     */
    @SerializedName("public_repos")
    String publicRepos;

    /**
     *
     */
    @SerializedName("public_gists")
    String publicGists;

    /**
     *
     */
    String followers;

    /**
     *
     */
    String following;

    /**
     *
     */
    @SerializedName("created_at")
    Date createTime;

    /**
     *
     */
    @SerializedName("updated_at")
    Date updateTime;

}
