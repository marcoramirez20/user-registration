package com.example.users.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String citycode;

    // Spec has a typo 'contrycode'. We'll store as 'countrycode' but accept both keys in DTO.
    @Column(nullable = false)
    private String countrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCitycode() { return citycode; }
    public void setCitycode(String citycode) { this.citycode = citycode; }

    public String getCountrycode() { return countrycode; }
    public void setCountrycode(String countrycode) { this.countrycode = countrycode; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
}
