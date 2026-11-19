package com.inventorypro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="purchase_requests")
public class PurchaseRequest {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Product product;
    @ManyToOne private Supplier supplier;
    private int quantity;
    private String reason;
    private String requestedBy;
    private String status="PENDING";
    private String reviewedBy;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime reviewedAt;
    public PurchaseRequest(){}
    public Long getId(){return id;} public Product getProduct(){return product;} public Supplier getSupplier(){return supplier;}
    public int getQuantity(){return quantity;} public String getReason(){return reason;} public String getRequestedBy(){return requestedBy;}
    public String getStatus(){return status;} public String getReviewedBy(){return reviewedBy;} public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getReviewedAt(){return reviewedAt;}
    public void setProduct(Product v){product=v;} public void setSupplier(Supplier v){supplier=v;} public void setQuantity(int v){quantity=v;}
    public void setReason(String v){reason=v;} public void setRequestedBy(String v){requestedBy=v;} public void setStatus(String v){status=v;}
    public void setReviewedBy(String v){reviewedBy=v;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
}
