package com.janfranco.springcrud.controller;

import com.janfranco.springcrud.dao.abstracts.CustomerDAO;
import com.janfranco.springcrud.entity.Customer;
import com.janfranco.springcrud.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/add")
    public String addCustomer(@RequestParam(value = "customerId", defaultValue = "") String customerId, Model model) {
        Customer customer;
        if (!customerId.isEmpty()) {
            customer = customerService.getCustomer(Integer.parseInt(customerId));
        } else {
            customer = new Customer();
        }

        model.addAttribute("customer", customer);
        return "add-customer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteCustomer(@RequestParam(value = "customerId", defaultValue = "") String customerId) {
        if (!customerId.isEmpty()) {
            customerService.deleteCustomer(Integer.parseInt(customerId));
        }
        return "redirect:/";
    }
}
