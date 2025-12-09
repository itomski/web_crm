package de.gfn.web.customermanagement;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usergroups")
public class UserGroup {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "group") // Eine Gruppe kann viele User enthalten
    private Set<User> users = new HashSet<>();

    public UserGroup() {
    }

    public UserGroup(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return name;
    }
}
