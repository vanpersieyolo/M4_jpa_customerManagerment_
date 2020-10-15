package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String findAll(Model model){
        Iterable<Province> provinces = provinceService.findAll();
        model.addAttribute("provinces",provinces);
        return "/province/list";
    }
    @GetMapping("/create-province")
    public String showcreateForm(Model model){
        model.addAttribute("province",new Province());
        return "/province/create";
    }
    @PostMapping("/create-province")
    public String create(Province province){
        provinceService.save(province);
        return "redirect:/province";
    }
    @GetMapping("/edit-province/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        Province province = provinceService.findById(id);
        if (province != null){
            model.addAttribute("province",province);
            return "province/edit";
        }else
            return "province/error.404";
    }
    @PostMapping("/edit-province")
    public String edit(@ModelAttribute("province") Province province){
        provinceService.save(province);
        return "redirect:/province";
    }
    @GetMapping("/delete-province/{id}")
    public String mockupDelete(@PathVariable Long id, Model model){
        Province province = provinceService.findById(id);
        if (province != null){
            model.addAttribute("province",province);
            return "province/delete";
        }else
            return "province/error.404";
    }
    @PostMapping("/delete-province")
    public String delete(Long id){
        provinceService.remove(id);
        return "redirect:/province";
    }
    @GetMapping("/view-province/{id}")
    public String detail (@PathVariable Long id, Model model){
        Province province = provinceService.findById(id);
        if (province == null){
            return "province/error.404";
        }else {
            Iterable<Customer> customers = customerService.findAllByProvince(province);
            model.addAttribute("province", province);
            model.addAttribute("customers", customers);
            return "province/view";
        }
    }
}
