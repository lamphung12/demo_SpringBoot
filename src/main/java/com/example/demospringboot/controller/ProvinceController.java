package com.example.demospringboot.controller;

import com.example.demospringboot.model.Province;
import com.example.demospringboot.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProvinceController {
    @Autowired
    IProvinceService iProvinceService;

    @GetMapping("/home")
    public String home(){
        return "/index";
    }
    @GetMapping("/province")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("provinces",iProvinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }
    @PostMapping("create-province")
    public ModelAndView save(@ModelAttribute("province") Province province){
        ModelAndView modelAndView = new ModelAndView("/create");//redirect:/province
        iProvinceService.save(province);
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message","New province created successfully");
        return modelAndView;
    }


    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable("id")Long id){
        Optional<Province> province = iProvinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("province",province.get());
        return modelAndView;
    }
    @PostMapping("edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province){
        iProvinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/edit");//redirect:/province
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        Optional<Province> province = iProvinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("province",province.get());
        return modelAndView;
    }
    @PostMapping("/delete-province")
    public ModelAndView remote(@ModelAttribute("province") Province province){
        iProvinceService.remove(province.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        return modelAndView;
    }

}
