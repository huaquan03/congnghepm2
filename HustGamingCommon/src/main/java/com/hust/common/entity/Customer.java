package com.hust.common.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CustomerId")
    private int CustomerId;
    @Column(name="CustomerName")
    private String CustomerName;
    @Column(name = "Email")
    private String Email;
    @Column(name = "PasswordHash")
    private String PasswordHash;
    @Column(name="Phone")
    private String Phone;
    @Column(name="CreatedAt")
    private LocalDateTime CreatedAt;
    @Column(name="UpdatedAt")
    private LocalDateTime UpdatedAt;
    @Column(name="Address")
    private String Address;



}
