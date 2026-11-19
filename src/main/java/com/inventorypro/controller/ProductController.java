package com.inventorypro.controller;
import com.inventorypro.model.Product;import com.inventorypro.repository.ProductRepository;
import jakarta.validation.Valid;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/products")
public class ProductController {
 private final ProductRepository repo;public ProductController(ProductRepository r){repo=r;}
 @GetMapping public String list(Model m){m.addAttribute("products",repo.findAll());return "products";}
 @GetMapping("/new") public String create(Model m){m.addAttribute("product",new Product());return "product-form";}
 @PostMapping("/save") public String save(@Valid @ModelAttribute Product p,BindingResult b,RedirectAttributes ra){
  if(b.hasErrors())return "product-form";repo.save(p);ra.addFlashAttribute("message","Product saved successfully");return "redirect:/products";
 }
 @GetMapping("/{id}/edit") public String edit(@PathVariable Long id,Model m){m.addAttribute("product",repo.findById(id).orElseThrow());return "product-form";}
 @PostMapping("/{id}/delete") public String delete(@PathVariable Long id,RedirectAttributes ra){repo.deleteById(id);ra.addFlashAttribute("message","Product deleted");return "redirect:/products";}
}
