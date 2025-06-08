package com.personal.securenotesapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date creationDate;
    // FOR IMAGE
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
    @JsonIgnore
    private List<Note> notes;
}
