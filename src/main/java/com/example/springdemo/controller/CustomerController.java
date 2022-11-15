package com.example.springdemo.controller;

import com.example.springdemo.dao.CustomerDAO;
import com.example.springdemo.entity.Customer;
import com.example.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        // get customers from the service
        List<Customer> theCustomers = customerService.getCustomers();
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/addCustomer")
    public String showFormForAdd(Model theModel){
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult result){
        if(result.hasErrors()){
            return "customer-form";
        }
        else{
            customerService.saveCustomer(theCustomer);
            return "redirect:/customer/list";
        }
    }

    @GetMapping("/updateCustomer")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model){
        Customer theCustomer = customerService.getCustomer(id);
        model.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id, Model model){
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }
}
