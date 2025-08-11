package com.example.users.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = {"email"})
})
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant created;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant modified;

    @Column(nullable = false)
    private Instant lastLogin;

    @Column(length = 2048)
    private String token;

    @Column(nullable = false)
    private boolean isActive = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<PhoneEntity> getPhones() { return phones; }
    public void setPhones(List<PhoneEntity> phones) {
        this.phones.clear();
        if (phones != null) {
            for (PhoneEntity p : phones) {
                addPhone(p);
            }
        }
    }

    public void addPhone(PhoneEntity phone) {
        phone.setUser(this);
        this.phones.add(phone);
    }

    public Instant getCreated() { return created; }
    public void setCreated(Instant created) { this.created = created; }

    public Instant getModified() { return modified; }
    public void setModified(Instant modified) { this.modified = modified; }

    public Instant getLastLogin() { return lastLogin; }
    public void setLastLogin(Instant lastLogin) { this.lastLogin = lastLogin; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
