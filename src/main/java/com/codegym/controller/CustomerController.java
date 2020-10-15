package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
//
//    @Autowired
//    protected Environment environment;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }


    @GetMapping("")
    public String showAll(@RequestParam("s") Optional<String> s, Model model, @PageableDefault(size = 3) Pageable pageable){
        Page<Customer> customers;
        if (s.isPresent()){
                customers = customerService.findCustomerByFirstName(s.get(),pageable);
                model.addAttribute("s",s.get());
        }else{
                customers = customerService.findAll(pageable);
        }
        model.addAttribute("customers",customers);
        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        model.addAttribute("customer",new Customer());
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer){
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