package com.example.users.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UserResponse {
    private UUID id;
    private Instant created;
    private Instant modified;
    private Instant last_login;
    private String token;
    private boolean isactive;
    private String name;
    private String email;
    private List<PhoneResponse> phones;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Instant getCreated() { return created; }
    public void setCreated(Instant created) { this.created = created; }
    public Instant getModified() { return modified; }
    public void setModified(Instant modified) { this.modified = modified; }
    public Instant getLast_login() { return last_login; }
    public void setLast_login(Instant last_login) { this.last_login = last_login; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public boolean isIsactive() { return isactive; }
    public void setIsactive(boolean isactive) { this.isactive = isactive; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<PhoneResponse> getPhones() { return phones; }
    public void setPhones(List<PhoneResponse> phones) { this.phones = phones; }
}
