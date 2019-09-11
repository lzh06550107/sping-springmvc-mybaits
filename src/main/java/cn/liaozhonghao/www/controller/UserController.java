package cn.liaozhonghao.www.controller;

import cn.liaozhonghao.www.model.User;
import cn.liaozhonghao.www.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.finaAll();
        for(User user : userList) {
            System.out.println("id: " + user.getId());
            System.out.println("name: " + user.getName());
        }
        return "hello";
    }
}
