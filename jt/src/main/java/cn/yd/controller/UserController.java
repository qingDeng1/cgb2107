package cn.yd.controller;

import cn.yd.pojo.User;
import cn.yd.service.UserService;
import cn.yd.vo.PageResult;
import cn.yd.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * 需求: 根据u/p查询数据库,返回秘钥token
     * URL: /user/login
     * 类型: post
     * 参数: username/password json
     * 返回值: SysResult对象(token)
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {
        String token = userService.findUserByUP(user);
        if (token == null || "".equals(token)) {
            //表示用户名和密码错误
            return SysResult.fail();
        }
        //表示用户名和密码正确,返回秘钥信息
        return SysResult.success(token);
    }

    /*
     * 需求：利用分页查询用户信息
     * URL： /user/list
     * 参数： http://localhost:8091/user/list?query=查询关键字&pageNum=1&pageSize=10
     * 返回值：SysResult(pageResult)
     * */
    @GetMapping("/list")
    //post/put请求才加@RequestBody
    public SysResult getUserListByPage(PageResult pageResult) {
        pageResult = userService.getUserListByPage(pageResult);
        return SysResult.success(pageResult);
    }

    /*
     * 需求：根据id修改状态
     * 请求路径 /user/addUser
     * 请求类型 POST
     * 请求参数: 用户ID/状态值数据
     * 返回值结果: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatusById(User user){
        userService.updateStatusById(user);
        return SysResult.success();
    }

    /*
    * 需求：用户新增
    * 请求路径 /user/addUser
    * 请求类型 POST
    * 请求参数: 整个form表单数据
    * 参数：JS对象经过浏览器解析变为JSON串
    * 返回值结果: SysResult对象
    * 对象转化为JSON @ResponseBody
    * JSON转化为对象 @RequestBody
    * */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user){
        userService.addUser(user);
        return SysResult.success();
    }

    /*
    * 需求：根据ID查询用户信息
    * 请求路径: /user/{id}
    * 请求类型: GET
    * 返回值: SysResult对象
    * 参数：status,msg,data
     * */
    @GetMapping("/{id}")
    public SysResult selectUserById(@PathVariable Integer id) {
        User user = userService.selectUserById(id);
        return SysResult.success(user);
    }

    /*
    * 需求：根据用户ID更新数据
    * 请求路径: /user/updateUser
    * 请求类型: PUT
    * 请求参数: User对象结构
    * 返回值: SysResult对象
    * */
    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return SysResult.success();
    }

    /*
    * 需求： 根据ID删除用户
    * 请求路径: /user/{id}
    * 请求类型: delete
    * 请求参数:id
    * 返回值: SysResult对象
    * */
    @DeleteMapping("/{id}")
    public SysResult deleteById(@PathVariable Integer id) {
        //防止与MP方法冲突，业务方法最好添加业务名
        userService.deleteUserById(id);
        return SysResult.success();
        //异常处理
//        try {
//            userService.deleteUserById(id);
//            return SysResult.success();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return SysResult.fail();
//        }
    }
}
