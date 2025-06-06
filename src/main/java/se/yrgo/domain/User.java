package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_game_library", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> library = new HashSet<>();

    public User() {

    }

    public User(String username, String email, String password, Set<Game> library) {
        this.username = username;
        this.email = email;
        this.password = password;
        
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public Set<Game> getLibrary() {
        return library;
    }

    public void setLibrary(Set<Game> library) {
        this.library = library;
    }
}
