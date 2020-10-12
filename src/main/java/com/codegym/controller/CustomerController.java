package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerServiceImpl;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String showAll(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        model.addAttribute("customer",new Customer());
        return "create";
    }
    @PostMapping("/create")
    public String create(Customer customer){
      customerService.save(customer);
        return "redirect:/customer";
    }
    @GetMapping("/delete/{id}")
    public String remove(@PathVariable ("id") Long id){
        customerService.remove(id);
        return "redirect:/customer";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "edit";
    }
    @PostMapping("/edit")
    public String edit(Customer customer){
        customerService.save(customer);
        return "redirect:/customer";
    }
}
