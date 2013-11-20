package com.rafalkalita.spitter.domain;

import java.util.List;

/**
 * User: rafalkalita
 * Date: 20/11/2013
 * Time: 18:34
 */
public class Spitter {

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private List<Spittle> spittles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Spittle> getSpittles() {
        return spittles;
    }

    public void setSpittles(List<Spittle> spittles) {
        this.spittles = spittles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spitter)) return false;

        Spitter spitter = (Spitter) o;

        if (fullName != null ? !fullName.equals(spitter.fullName) : spitter.fullName != null) return false;
        if (!id.equals(spitter.id)) return false;
        if (password != null ? !password.equals(spitter.password) : spitter.password != null) return false;
        if (spittles != null ? !spittles.equals(spitter.spittles) : spitter.spittles != null) return false;
        if (username != null ? !username.equals(spitter.username) : spitter.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (spittles != null ? spittles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", spittles=" + spittles +
                '}';
    }
}
