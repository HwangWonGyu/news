package com.hwangwongyu.news.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @Value("${spring.webservice.index}")
    private String indexURL;


    @GetMapping("/allusers")
    public List<UserDTO> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/user")
    public String addUser(UserDTO user)
    {
        userService.addUser(user);
        return "redirect:" + indexURL;
    }

    @PutMapping("/user")
    public String updateUser(UserDTO user) {
        userService.updateUser(user);
        return "redirect:" + indexURL;
    }

    @DeleteMapping("/user")
    public String deleteUser(String loginId)
    {
        userService.deleteUser(loginId);
        return "redirect:" + indexURL;
    }

    @GetMapping("/user/{id}")
    public UserDTO findUserById(@PathVariable long id)
    {
        UserDTO user = userService.findUserById(id);
        if(user != null)
            return user;
        else
            return null;
    }





}
