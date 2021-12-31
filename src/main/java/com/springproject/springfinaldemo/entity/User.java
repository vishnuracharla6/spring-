package com.springproject.springfinaldemo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter @Setter
    private int id;

    @Column(name="name")
    @Getter @Setter
    @NotNull(message = "is Required")
    private String name;

    @Column(name="password")
    @Getter @Setter
    @NotNull(message = "is required")
    @Size(min = 3, message = "is required")
    private String password;

    @Column(name="email")
    @Email
    @Getter @Setter @NotNull(message = "is required")
    private String email;

    @Column(name="role")
    @Getter @Setter
    @NotNull(message = "is required")
    private String role;

    @Column(name="enabled")
    @Getter @Setter
    private boolean enabled;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "college_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "college_id")
    )
    @Getter @Setter
    private List<College> collegeArray;

    public User()
    {}
    public User(String name, String email, String password,String role,boolean enabled)
    {


        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
        this.enabled=enabled;
    }

    public void addCollege(College thecollege) {

        if (collegeArray == null) {
            collegeArray = new ArrayList<>();
        }
        collegeArray.add(thecollege);
    }

}
