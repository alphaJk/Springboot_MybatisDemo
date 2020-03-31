package com.jk.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2020-03-26
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class Msg {
    private int id;
    private int uid;
    private String subject;
    private int views;
    private int replies;
    private String strCreatedAt;
    private String strTid;
    private String creat_time;

}
