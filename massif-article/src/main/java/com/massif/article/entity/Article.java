package com.massif.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 专栏ID ---- columnid
     */
    private String columnId;

    /**
     * 用户ID ----- userid
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 文章封面
     */
    private String image;

    /**
     * 发表日期  ----- createtime
     */
    private java.util.Date createTime;

    /**
     * 修改日期  ------ updatetime
     */
    private java.util.Date updateTime;

    /**
     * 是否公开  ----- ispublic
     */
    private String isPublic;

    /**
     * 是否置顶  ----- istop
     */
    private String isTop;

    /**
     * 浏览量
     */
    private Integer visits;

    /**
     * 点赞数  ------ thumbup
     */
    private Integer thumbUp;

    /**
     * 评论数
     */
    private Integer comment;

    /**
     * 审核状态
     */
    private String state;

    /**
     * 所属频道  ------ channelid
     */
    private String channelId;

    /**
     * URL
     */
    private String url;

    /**
     * 种类
     */
    private String type;

}
