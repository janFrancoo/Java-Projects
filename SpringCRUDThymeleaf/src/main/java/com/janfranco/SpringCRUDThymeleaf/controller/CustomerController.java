package com.janfranco.SpringCRUDThymeleaf.controller;

import com.janfranco.SpringCRUDThymeleaf.entity.Customer;
import com.janfranco.SpringCRUDThymeleaf.service.abstracts.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String getCustomerList(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customer/list-customers";
    }

    @GetMapping("/save")
    public String getAddForm(@RequestParam("customerId") Optional<Integer> customerId, Model model) {
        Customer customer;
        if (customerId.isPresent()) {
            customer = customerService.get(customerId.get());
        } else {
            customer = new Customer();
        }
        model.addAttribute("customer", customer);
        return "customer/add-form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String saveCustomer(@RequestParam("customerId") int customerId) {
        customerService.delete(customerId);
        return "redirect:/";
    }
}
