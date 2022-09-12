package com.example.management.controller;

import com.example.management.model.User;
import com.example.management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

     @PostMapping("/user")
    public User saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id)
    {
        User user= userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable("id") long id)
    {
        User user=userService.deleteUser(id);
        Map<String,Boolean> map=new HashMap<>();
        map.put(user+"",true);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user)
    {
         user=userService.updateUser(id,user);
         return ResponseEntity.ok(user);
    }
}
