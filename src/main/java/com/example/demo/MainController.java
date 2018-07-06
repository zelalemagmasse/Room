package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {
@Autowired
    private RoomRipository roomRipository;

    @Autowired
    UserClassRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @RequestMapping("/")
    public String homePage(Model model)
    {

        model.addAttribute("rooms",roomRipository.findAll());
        model.addAttribute("user",userRepository.findAll());
        model.addAttribute("roles",roleRepository.findAll());

        return "displayrooms";
    }

    @RequestMapping("/addroom")
    public String addroom(Model model)
    {
        model.addAttribute("aRoom", new Room());
         model.addAttribute("user",userRepository.findAll());
        model.addAttribute("roles",roleRepository.findAll());
        return "addroom";
    }
    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newUser",new UserClass());
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") UserClass newUser, BindingResult result, Model model)
    {

        if(result.hasErrors())
        {
            System.out.println(result.toString());
            return "register";
        }
        else{
            //Create a new ordinary user
            model.addAttribute(newUser.getUsername()+" created");
            UserRole r = roleRepository.findByRole("DAVE");
            userRepository.save(newUser);
            newUser.addRole(r);
            userRepository.save(newUser);
            return "redirect:/";
        }
    }

    @RequestMapping("/saveroom")
    public String savePet(@ModelAttribute("aRoom") Room room, Model model)
    {

        roomRipository.save(room);
        model.addAttribute("rooms",roomRipository.findAll());
       if(room.isRented()){
           room.setIsRentednow("TAKEN");
       }
       else
           room.setIsRentednow("FREE");

        return "displayrooms";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){

        model.addAttribute("rooms", roomRipository.findById(id).get());
        return "displaydetail";


    }


}
