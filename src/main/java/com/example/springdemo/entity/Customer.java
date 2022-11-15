package com.example.springdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @Size(min = 1, message = "is required!")
    @NotNull(message = "is required!")
    @NotBlank(message = "field cannot be empty!")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "is required!")
    @NotBlank(message = "field cannot be empty!")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "is required!")
    @NotBlank(message = "field cannot be empty!")
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
