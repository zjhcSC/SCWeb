package com.zjhc.web.controller;

import com.zjhc.mybatis.service.UserService;
import com.zjhc.web.bean.DataPage;
import com.zjhc.web.bean.JsonResult;
import com.zjhc.web.bean.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 漏水亦凡
 * @create 2017-05-05 15:09.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequiresPermissions("system:user:view")
    public String index() {
        return "system/user";
    }


    @PostMapping("list")
    @ResponseBody
    @RequiresPermissions("system:user:view")
    public DataPage list(@RequestParam("offset") int offset,
                         @RequestParam("limit") int limit,
                         @RequestParam("name") String name) {

        DataPage dp = userService.getUsers(offset, limit, name);

        return dp;
    }

    @PostMapping("info")
    @ResponseBody
    @RequiresPermissions("system:role:view")
    public JsonResult info(String id) {

        Map<String, Object> map = userService.getUserInfo(id);

        return JsonResult.success(map);
    }

    @PostMapping("delete")
    @ResponseBody
    @RequiresPermissions("system:user:delete")
    public JsonResult delete(long id) {

        int num = userService.delete(id);

        return JsonResult.success(num);
    }

    @PostMapping("update")
    @ResponseBody
    @RequiresPermissions("system:user:update")
    public JsonResult update(@RequestBody UserVO userUpdate) {

        int num = userService.updateUser(userUpdate);

        return JsonResult.success(num);
    }

    @PostMapping("add")
    @ResponseBody
    @RequiresPermissions("system:user:add")
    public JsonResult add(@RequestBody UserVO userUpdate) throws Exception{

        int num = userService.addUser(userUpdate);

        return JsonResult.success(num);
    }


}
