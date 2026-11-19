package com.inventorypro.controller;
import com.inventorypro.repository.*;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;
@Controller public class DashboardController {
 private final ProductRepository products; private final SupplierRepository suppliers; private final PurchaseRequestRepository requests;
 public DashboardController(ProductRepository p,SupplierRepository s,PurchaseRequestRepository r){products=p;suppliers=s;requests=r;}
 @GetMapping("/") public String dashboard(Model m){
  m.addAttribute("productCount",products.count());m.addAttribute("supplierCount",suppliers.count());
  m.addAttribute("pendingCount",requests.countByStatus("PENDING"));m.addAttribute("lowStock",products.findAll().stream().filter(p->p.getQuantity()<=p.getReorderLevel()).toList());
  m.addAttribute("requests",requests.findAllByOrderByCreatedAtDesc());return "dashboard";
 }
}
