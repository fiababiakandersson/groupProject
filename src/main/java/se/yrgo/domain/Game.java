package se.yrgo.domain;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String developer;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "library")
    private Set<User> users = new HashSet<>();

    public Game() {

    }

    public Game(String title, String genre, String developer, Set<Review> reviews, Set<User> users) {
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.reviews = reviews;
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((developer == null) ? 0 : developer.hashCode());
        result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (developer == null) {
            if (other.developer != null)
                return false;
        } else if (!developer.equals(other.developer))
            return false;
        if (reviews == null) {
            if (other.reviews != null)
                return false;
        } else if (!reviews.equals(other.reviews))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
