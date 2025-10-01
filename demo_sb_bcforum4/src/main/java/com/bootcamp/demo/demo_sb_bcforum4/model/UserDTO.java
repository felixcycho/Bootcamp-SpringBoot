package com.bootcamp.demo.demo_sb_bcforum4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private Long origUserId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String latitude;
    private String longitude;
    private String companyName;
    private String companyPhrase;
    private String companyBs;
}
