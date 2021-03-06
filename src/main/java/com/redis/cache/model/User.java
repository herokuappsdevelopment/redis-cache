package com.redis.cache.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author VenkatS
 *
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    private long followers;

    public User() {
    }

    public User(String name, long followers) {
        this.name = name;
        this.followers = followers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', followers=%d}", id, name, followers);
    }
}
