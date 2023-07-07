package com.massif.article.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 频道
 */

@Data
public class Channel implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 频道名 ------- channel_name
     */
    private String channelName;

    /**
     * 状态
     */
    private String state;


}
