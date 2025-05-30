package se.yrgo.domain;

import java.util.*;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String genre;
    private String developer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private Set<Review> reviews = new HashSet<>();

    public Game() {

    }

    public Game(String title, String genre, String developer) {
        this.title = title;
        this.genre = genre;
        this.developer = developer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", genre=" + genre + ", developer=" + developer + ", reviews="
                + reviews + "]";
    }

}
