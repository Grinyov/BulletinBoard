package com.grinyov.bulletinboard.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * Entity used to store information about users.
 */
@Entity
@Table(name = "accounts", schema = "", catalog = "bulletin_board")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
        @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name")})
public class Account implements Serializable {

    private static final long serialVersionUID = 409793453581901624L;
    private int id;
    @NotNull(message = "Name can't be empty.")
    @Size(min = 3, max = 255, message = "Your name must be between 3 and 255 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Name must be alphanumeric with no spaces.")
    private String name;
    @NotNull
    @Size(min = 3, max = 255, message = "Invalid email address.")
    @Email(message = "Invalid email address.")
    private String email;
    @NotNull(message = "Password can't be empty.")
    @Size(min = 3, max = 255, message = "Your password must be between 3 and 255 characters long.")
    private String password;

    public Account(){}

    public Account(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (email != null ? !email.equals(account.email) : account.email != null) return false;
        return password != null ? password.equals(account.password) : account.password == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
