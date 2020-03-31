package com.jk.controller;

import ch.qos.logback.classic.Logger;
import com.jk.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2020-03-30
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@Log4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManagerController {

    @Autowired
    private SqlSessionTemplate template;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    /**
     * 验证用户cookies是否存在
     * @param request
     * @return
     */
    private Boolean verifyCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            logger.info("cookies为空");
        }
        for (Cookie cookie: cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                logger.info("cookies验证成功");
                return true;
            }
        }
        return false;
    }

    /**
     * 登录接口
     * @param response
     * @param user
     * @return
     */

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @PostMapping(value = "/login")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        //调用xml里的sql
        int i = template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        logger.info("查询到的结果是"+i);
        if (i==1){
            response.addCookie(cookie);
            logger.info("登录的用户是："+user.getUsername());
            return true;
        }
        return false;
    }

    /**
     * 添加用户
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户入口",httpMethod = "POST")
    @PostMapping(value = "/addUser")
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean isCookies = verifyCookies(request);
        int result = 0;
        if (isCookies !=null){
            result = template.insert("addUser",user);
        }
        if (result > 0){
            logger.info("添加用户成功："+result);
            return true;
        }
        logger.info("添加用户失败："+result);
        return false;
    }

    /**
     * 查询用户
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "查询用户",httpMethod = "POST")
    @PostMapping(value = "getUserInfo")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user){
        Boolean isCookies = verifyCookies(request);
        if (isCookies==true){
            List<User> users = template.selectList("getUserInfo",user);
            logger.info("getUserInfo获取到的用户数量是："+users.size());
            return users;
        }else {
            return null;
        }

    }

    /**
     * 更新/删除用户
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "更新/删除用户",httpMethod = "POST")
    @PostMapping(value = "/updateUserInfo")
    public int updateUser(HttpServletRequest request, @RequestBody User user){
        Boolean isCookies = verifyCookies(request);
        int i =0;
        if(isCookies==true){
            i = template.update("updateUserInfo",user);
        }
        logger.info("更新数据的数量为："+i);
        return i;
    }
}
