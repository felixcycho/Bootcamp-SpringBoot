package com.bootcamp.demo.demo_sb_bcforum2.model;

import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

@Getter
// @Setter
// @NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private String username;
  private String email;
  private String phone;
  private String website;
  private Address address;
  private Company company;

  @Getter
  // @Setter
  // @NoArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    // @Setter
    // @NoArgsConstructor
    public static class Geo {
      private String lat;
      private String lng;
    }
  }

  @Getter
  // @Setter
  // @NoArgsConstructor
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
