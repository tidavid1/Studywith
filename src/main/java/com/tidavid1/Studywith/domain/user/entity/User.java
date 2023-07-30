package com.tidavid1.Studywith.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 30)
    private String id;

    @Column(nullable = false, length = 30)
    private String passwd;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String phoneCall;

    @Column
    private Boolean isActive;

    @PrePersist
    public void prePersist(){
        this.isActive = this.isActive == null || this.isActive;
    }

    public void deleteUser(){
        this.isActive = false;
    }

    public void updatePhoneCall(String phoneCall){
        this.phoneCall = phoneCall;
    }
}
