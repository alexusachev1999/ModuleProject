package ru.usachev.LogiWebProject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.usachev.LogiWebProject.converter.api.UserConverter;
import ru.usachev.LogiWebProject.dto.UserDTO;
import ru.usachev.LogiWebProject.entity.User;
import ru.usachev.LogiWebProject.service.api.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "admin/all-users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "admin/add-user";
    }

    @PostMapping(value = "/saveUser", produces = "text/plain;charset=UTF-8")
    public String saveUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult
            , Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("user", userDTO);
            return "admin/add-user";}
        else {
            userService.saveUser(userDTO);
            return "redirect:/admin/users";
        }
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(name = "userName") String userName){
        User user = userService.getUserByUsername(userName);
        userService.deleteUser(user);
        return "redirect:/admin/users";
    }
}
