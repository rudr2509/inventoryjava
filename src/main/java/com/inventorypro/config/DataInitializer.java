package com.inventorypro.config;

import com.inventorypro.model.*;
import com.inventorypro.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seed(AppUserRepository users, ProductRepository products, SupplierRepository suppliers, PasswordEncoder encoder){
  return args -> {
   if(users.findByUsername("admin").isEmpty()) users.save(new AppUser("admin",encoder.encode("Admin@123"),"ADMIN"));
   if(users.findByUsername("employee").isEmpty()) users.save(new AppUser("employee",encoder.encode("Employee@123"),"EMPLOYEE"));
   if(products.count()==0){
    Product p=new Product(); p.setName("Wireless Mouse");p.setSku("IT-001");p.setCategory("Electronics");p.setQuantity(18);p.setReorderLevel(5);p.setUnitPrice(new BigDecimal("599.00"));products.save(p);
    Product q=new Product(); q.setName("A4 Paper Ream");q.setSku("OFF-001");q.setCategory("Stationery");q.setQuantity(4);q.setReorderLevel(10);q.setUnitPrice(new BigDecimal("280.00"));products.save(q);
   }
   if(suppliers.count()==0){Supplier s=new Supplier();s.setName("Demo Supplier");s.setEmail("supplier@example.com");s.setPhone("9876543210");suppliers.save(s);}
  };
 }
}
