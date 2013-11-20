package com.rafalkalita.spitter.domain;

import java.util.Date;

/**
 * User: rafalkalita
 * Date: 20/11/2013
 * Time: 18:31
 */
public class Spittle {

    private Long id;
    private Spitter spitter;
    private String message;
    private Date whenCerated;

    public Spittle(Long id, Spitter spitter, String message, Date whenCerated) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.whenCerated = whenCerated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getWhenCerated() {
        return whenCerated;
    }

    public void setWhenCerated(Date whenCerated) {
        this.whenCerated = whenCerated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spittle)) return false;

        Spittle spittle = (Spittle) o;

        if (!id.equals(spittle.id)) return false;
        if (message != null ? !message.equals(spittle.message) : spittle.message != null) return false;
        if (spitter != null ? !spitter.equals(spittle.spitter) : spittle.spitter != null) return false;
        if (whenCerated != null ? !whenCerated.equals(spittle.whenCerated) : spittle.whenCerated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (spitter != null ? spitter.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (whenCerated != null ? whenCerated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", spitter=" + spitter +
                ", message='" + message + '\'' +
                ", whenCerated=" + whenCerated +
                '}';
    }
}
