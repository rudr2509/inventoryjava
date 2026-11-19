package com.inventorypro.controller;
import com.inventorypro.model.Supplier;import com.inventorypro.repository.SupplierRepository;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/suppliers")
public class SupplierController {
 private final SupplierRepository repo;public SupplierController(SupplierRepository r){repo=r;}
 @GetMapping public String list(Model m){m.addAttribute("suppliers",repo.findAll());return "suppliers";}
 @GetMapping("/new") public String form(Model m){m.addAttribute("supplier",new Supplier());return "supplier-form";}
 @PostMapping("/save") public String save(@ModelAttribute Supplier s){repo.save(s);return "redirect:/suppliers";}
}
