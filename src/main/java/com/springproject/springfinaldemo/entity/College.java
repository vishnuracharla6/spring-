package com.springproject.springfinaldemo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="college")

public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    @Getter @Setter
    private int id;

    @Column(name="collegename")
    @Getter @Setter
    private String collegeName;

    @Column(name="address")
    @Getter @Setter
    private String address;


    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="college_user",
            joinColumns=@JoinColumn(name="college_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> users;

    public College()
    {

    }
    public College(String collegeName, String address)
    {
        this.collegeName=collegeName;
        this.address=address;
    }

}
