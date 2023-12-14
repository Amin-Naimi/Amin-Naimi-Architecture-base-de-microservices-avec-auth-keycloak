package com.Mohamed.front_service.models;
import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String email;
}