package com.inventorypro.controller;
import com.inventorypro.model.*;import com.inventorypro.repository.*;
import org.springframework.security.core.Authentication;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
@Controller @RequestMapping("/requests")
public class RequestController {
 private final PurchaseRequestRepository requests;private final ProductRepository products;private final SupplierRepository suppliers;private final RequestHistoryRepository history;
 public RequestController(PurchaseRequestRepository r,ProductRepository p,SupplierRepository s,RequestHistoryRepository h){requests=r;products=p;suppliers=s;history=h;}
 @GetMapping public String list(Model m,Authentication a){boolean admin=a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));m.addAttribute("requests",admin?requests.findAllByOrderByCreatedAtDesc():requests.findByRequestedByOrderByCreatedAtDesc(a.getName()));m.addAttribute("admin",admin);return "requests";}
 @GetMapping("/new") public String form(Model m){m.addAttribute("products",products.findAll());m.addAttribute("suppliers",suppliers.findAll());return "request-form";}
 @PostMapping("/save") public String save(@RequestParam Long productId,@RequestParam(required=false) Long supplierId,@RequestParam int quantity,@RequestParam String reason,Authentication a,RedirectAttributes ra){
  if(quantity<1){ra.addFlashAttribute("error","Quantity must be at least 1");return "redirect:/requests/new";}
  PurchaseRequest r=new PurchaseRequest();r.setProduct(products.findById(productId).orElseThrow());if(supplierId!=null)r.setSupplier(suppliers.findById(supplierId).orElse(null));r.setQuantity(quantity);r.setReason(reason);r.setRequestedBy(a.getName());requests.save(r);history.save(new RequestHistory(r,"SUBMITTED",a.getName(),reason));ra.addFlashAttribute("message","Purchase request submitted");return "redirect:/requests";
 }
 @PostMapping("/{id}/review") public String review(@PathVariable Long id,@RequestParam String decision,@RequestParam(required=false) String note,Authentication a,RedirectAttributes ra){
  PurchaseRequest r=requests.findById(id).orElseThrow();
  if(!"PENDING".equals(r.getStatus())){ra.addFlashAttribute("error","This request has already been reviewed");return "redirect:/requests";}
  if("APPROVED".equals(decision)){Product p=r.getProduct();p.setQuantity(p.getQuantity()+r.getQuantity());products.save(p);r.setStatus("APPROVED");}
  else if("REJECTED".equals(decision))r.setStatus("REJECTED");else return "redirect:/requests";
  r.setReviewedBy(a.getName());r.setReviewedAt(LocalDateTime.now());requests.save(r);history.save(new RequestHistory(r,r.getStatus(),a.getName(),note));ra.addFlashAttribute("message","Request "+r.getStatus().toLowerCase());return "redirect:/requests";
 }
}
