package com.alal.backend.domain.entity.user;

import com.alal.backend.domain.entity.time.DefaultTime;
import com.alal.backend.payload.request.auth.ProfileUpdateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@DynamicUpdate
@Entity
@Getter
@Table(name = "user")
public class User extends DefaultTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String providerId;

    @Column
    private String userGroup;

    @Column
    private String userHistory;
    
    public User(){}

    @Builder
    public User(String name, String email, String password, Role role, Provider provider, String providerId, String imageUrl, Long id, String userHistory){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = provider;
        this.role = role;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        this.userGroup = profileUpdateRequest.getUserGroup();
        this.name = profileUpdateRequest.getUserName();
        this.imageUrl = profileUpdateRequest.getBase64ProfileImage();
    }

    public void historyUpdate(String responseToString) {
        this.userHistory = responseToString;
    }
}
