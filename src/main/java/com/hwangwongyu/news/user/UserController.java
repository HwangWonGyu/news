package com.hwangwongyu.news.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Value("${spring.webservice.index}")
    private String indexURL;


    @GetMapping("/users")
    public List<UserDTO> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/users")
    public String addUser(@RequestBody UserDTO user) {
        userService.addUser(user);
        return "redirect:" + indexURL;
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody UserDTO user) {
        userService.updateUser(user);
        return "redirect:" + indexURL;
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestBody UserDTO user) {
        userService.deleteUser(user.getLoginId());
        return "redirect:" + indexURL;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findUserById(@PathVariable long id) {
        UserDTO user = userService.findUserById(id);
        if (user != null)
            return new ResponseEntity(user, HttpStatus.OK);
        else
            return new ResponseEntity("유저 정보가 없습니다", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users/email")
    public ResponseEntity sendEmailAuthNCode(String toEmail) {
        Boolean success = userService.sendEmailAuthNCode(toEmail);
        if (success)
            return new ResponseEntity("이메일로 코드가 전송 됐습니다.", HttpStatus.OK);
        else
            return new ResponseEntity("이메일 전송에 실패했습니다.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/companies")
    public List<String> allCompanies() {
        return userService.allCompanies();
    }

}
