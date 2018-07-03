package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
@Autowired
    private RoomRipository roomRipository;

    @RequestMapping("/")
    public String homePage(Model model)
    {

        model.addAttribute("rooms",roomRipository.findAll());

        return "displayrooms";
    }

    @RequestMapping("/addroom")
    public String addroom(Model model)
    {
        model.addAttribute("aRoom", new Room());

        return "addroom";
    }


    @RequestMapping("/saveroom")
    public String savePet(@ModelAttribute("aRoom") Room room, Model model)
    {

        roomRipository.save(room);
        model.addAttribute("rooms",roomRipository.findAll());

        return "displayrooms";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){

        model.addAttribute("rooms", roomRipository.findById(id).get());
        return "displaydetail";


    }


}
