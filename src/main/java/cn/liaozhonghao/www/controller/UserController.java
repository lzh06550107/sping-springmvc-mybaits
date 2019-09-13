package cn.liaozhonghao.www.controller;

import cn.liaozhonghao.www.dto.UserDTO;
import cn.liaozhonghao.www.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/find")
    public String find(String id) {
        UserDTO user = userService.find(id);

        return "hello";
    }

}
