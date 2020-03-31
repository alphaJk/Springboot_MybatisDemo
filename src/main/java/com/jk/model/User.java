package com.jk.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2020-03-30
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
public class User {
    private int user_id;
    private String username;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDel;
}
