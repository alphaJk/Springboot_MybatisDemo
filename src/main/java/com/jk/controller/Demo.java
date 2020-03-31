package com.jk.controller;

import com.jk.model.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2020-03-24
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Log4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 查询
     * @return
     */
    @GetMapping(value = "/getMsgCount")
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getMsgCount(){
        return template.selectOne("getMsgCount");
    }

    /**
     * 增加数据
     * @param msg
     * @return
     */
    @PostMapping(value = "/addMsg")
    @ApiOperation(value = "添加信息",httpMethod = "POST")
    public int addMsg(@RequestBody Msg msg){
        return template.insert("addMsg",msg);
    }

    /**
     * 改
     * @param msg
     * @return
     */
    @PostMapping(value = "/updateMsg")
    public int updateMsg(@RequestBody Msg msg){
        return template.update("updateMsg",msg);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteMsg")
    public int deleteMsg(@RequestParam int id){
        return template.delete("deleteMsg",id);
    }
}
