package com.rafalkalita.spitter.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

/**
 * TODO: Use org.springframework.security.crypto.password.PasswordEncoder for password.
 *
 * @author Rafal Kalita
 */
@Entity
@Table(name = "spitter")
public class Spitter {

    private Long id;

    @Size(min=3, max=20, message="Username must be between 3 and 20 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces")
    private String username;

    @Size(min=6, max=20, message="The password must be at least 6 characters long.")
    private String password;

    @Size(min=3, max=50, message="Your full name must be between 3 and 50 characters long.")
    private String fullName;

    public Spitter() {}

    public Spitter(Spitter spitter) {
        this.id = spitter.getId();
        this.username = spitter.getUsername();
        this.password = spitter.getPassword();
        this.fullName = spitter.getFullName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "fullname")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
