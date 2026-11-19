package com.inventorypro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity @Table(name="products")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @Column(unique=true) private String sku;
    private String category;
    @Min(0) private int quantity;
    @Min(0) private int reorderLevel=5;
    @DecimalMin("0.0") private BigDecimal unitPrice=BigDecimal.ZERO;
    public Product(){}
    public Long getId(){return id;} public String getName(){return name;} public String getSku(){return sku;}
    public String getCategory(){return category;} public int getQuantity(){return quantity;}
    public int getReorderLevel(){return reorderLevel;} public BigDecimal getUnitPrice(){return unitPrice;}
    public void setName(String v){name=v;} public void setSku(String v){sku=v;} public void setCategory(String v){category=v;}
    public void setQuantity(int v){quantity=v;} public void setReorderLevel(int v){reorderLevel=v;}
    public void setUnitPrice(BigDecimal v){unitPrice=v;}
}
