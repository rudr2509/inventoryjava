package com.inventorypro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="request_history")
public class RequestHistory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private PurchaseRequest request;
    private String action;
    private String actor;
    private String note;
    private LocalDateTime actionAt=LocalDateTime.now();
    public RequestHistory(){}
    public RequestHistory(PurchaseRequest r,String a,String by,String n){request=r;action=a;actor=by;note=n;}
    public Long getId(){return id;} public PurchaseRequest getRequest(){return request;} public String getAction(){return action;}
    public String getActor(){return actor;} public String getNote(){return note;} public LocalDateTime getActionAt(){return actionAt;}
}
