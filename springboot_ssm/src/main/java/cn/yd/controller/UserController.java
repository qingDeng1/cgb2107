package cn.yd.controller;

import cn.yd.pojo.User;
import cn.yd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//编辑Controller
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //1、查询所有信息
    @RequestMapping("findAll")
    public Object findAll() {
        List<User> list = userService.findAll();
        return list;
    }

    //2、根据id查询信息
    //    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @GetMapping("findById") //只能接受get请求类型
    public User findUserById(Integer id) {
//        int id = 3;
        return userService.findById(id);
    }

    //3.1、根据name和age查询用户信息
    @GetMapping("findMore")
    public List<User> findMore(String name, Integer age) {
        return userService.findMore(name, age);
    }

    //3.2查询名称以"xxx"结尾的数据.   URL: http://localhost:8090/findUserLike?name=“xx”
    @GetMapping("findDim")
    public List<User> findDim(String name) {
        return userService.findDim(name);
    }

    //3.3查询id为 1,3,5,6,7的数据    URL: http://localhost:8090/findUserByIds?id=1,3,5,6,7
    @GetMapping("findIds")
    public List<User> findIds(String id) {
        return userService.findIds(id);
    }

    /*3.4根据对象中不为null的元素查询数据 动态Sql查询
        URL: http://localhost:8090/findUser?id=xx&name=“xx”&age=xx&sex=xx
        URL: http://localhost:8090/findUser?age=xx&sex=xx
        URL: http://localhost:8090/findUser?sex=xx
    */
    @GetMapping("findNull")
    public List<User> findNull(Integer id, String name, Integer age, String sex) {
        return userService.findNull(id, name, age, sex);
    }

    /*3.5更新操作 利用restFul的结构, 根据Id修改数据,修改name/age
        URL:http://localhost:8090/user/貂蝉/18/227
        解析: URL:http://localhost:8090/user/{name}/{age}/{id}*/
    @GetMapping("updateMore")
    public Object updateMore(Integer id, String name, Integer age, String sex) {
        userService.updateMore(id, name, age, sex);
        return "修改成功";
    }
}
