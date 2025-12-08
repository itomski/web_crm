package de.gfn.web.customermanagement;

import jakarta.persistence.*;

import java.time.LocalDate;

// POJO = Plain Old Java Object
@Entity
@Table(name = "users")
public class User {

    @Id // Pimärschlüssel
    @GeneratedValue // Autoincrement
    private int id;

    @Column(length = 50)
    private String firstname;

    @Column(length = 50)
    private String lastname;

    private LocalDate birthDate;

    public User() {
    }

    public User(String firstname, String lastname, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
