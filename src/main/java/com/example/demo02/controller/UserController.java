package com.example.demo02.controller;

import com.example.demo02.entity.User;
import com.example.demo02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired//dependency injection di
            UserRepository userRepository;

    @RequestMapping("/")
    public  String index(Model model){
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users",users);
        return "/index";
    }
    @RequestMapping(value = "/add")
    public  String addUser(Model model){
        model.addAttribute("user",new User());
        return "/addUser";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user){
        userRepository.save(user);
        return"redirect:/";
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUserByName(@RequestParam("name") String name, Model model) {
        List<User> users = userRepository.findByName(name);
        model.addAttribute("users", users);
        return "index";
    }
//    @RequestMapping(value = "/search")
//    public String searchByName(@RequestParam("name") String userName,Model model){
//        model.addAttribute("search",userRepository.Byname(userName));
//        return "index";
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id")long userId, Model model){
        Optional<User> userEdit = userRepository.findById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user",user));
        return "editUser";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id")long userId, Model model){
        userRepository.deleteById(userId);
        return "redirect:/";
    }


}
