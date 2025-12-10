package de.gfn.web.customermanagement;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// POJO = Plain Old Java Object
@Entity
@Table(name = "users")
public class User {

    @Id // Pimärschlüssel
    @GeneratedValue // Autoincrement
    private int id;

    @Column(length = 50)
    @Size(min = 2, max = 50)
    private String firstname;

    @Column(length = 50)
    @Size(min = 2, max = 50)
    private String lastname;

    @NotNull
    @Past
    private LocalDate birthDate;

    @ManyToOne
    private UserGroup group;

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

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }
}
