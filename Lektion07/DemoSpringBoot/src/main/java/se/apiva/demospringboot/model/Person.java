package se.apiva.demospringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Size(min = 3, message = "Name must be at least 3 characters in length")
    private String name;

    @Column(unique = true)
    @Pattern(
          regexp = "^\\+[1-9]\\d{1,14}$",
          message = "You have to enter the phone number in international format (E.164)"
    )
    private String phone;

    @Column
    @NotEmpty
    @Email(message = "You have to supply a well formed email")
    private String email;


    public Person() {

    }

    public Person(Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
