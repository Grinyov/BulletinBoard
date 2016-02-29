package com.grinyov.bulletinboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * Entity used to store information about ads.
 */
@Entity
@Table(name = "ads", schema = "", catalog = "bulletin_board")
@NamedQueries({
        @NamedQuery(name = "Advert.findAll", query = "SELECT a FROM Advert a"),
        @NamedQuery(name = "Advert.findByPattern", query = "SELECT a FROM Advert a WHERE a.shortDesc " +
                "LIKE :pattern OR a.contact.name LIKE :pattern"),
        @NamedQuery(name = "Advert.findByCategory", query = "SELECT a FROM Advert a " +
                "WHERE a.category.id = :categoryId AND a.top = FALSE"),
        @NamedQuery(name = "Advert.findById", query = "SELECT a FROM Advert a WHERE a.id = :id")})
public class Advert implements Serializable{

    private static final long serialVersionUID = 6935513514218522345L;

    private long id;
    private Account account;
    private Category category;
    @NotNull(message = "Title can't be empty.")
    @Size(min = 10, max = 30, message = "Title size must be between 10 and 30 characters long.")
    private String title;
    @NotNull(message = "Advert can't be empty")
    @Size(min = 20, max = 400, message = "Message size must be between 20 and 400 characters long.")
    private String text;
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    Timestamp publication;

    public Advert(){}

    public Advert(long id, Account account, Category category, String title, String text, Timestamp publication) {

        this.id = id;
        this.account = account;
        this.category = category;
        this.title = title;
        this.text = text;
        this.publication = publication;
    }
    @Id
    @GeneratedValue
    @Column(name = "announcement_id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    @OneToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @Column(name = "title", nullable = false, insertable = true, updatable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "text", nullable = false, insertable = true, updatable = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true)
    public Timestamp getPublication() {
        return publication;
    }

    public void setPublication(Timestamp publication) {
        this.publication = publication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (account != null ? !account.equals(advert.account) : advert.account != null) return false;
        if (category != null ? !category.equals(advert.category) : advert.category != null) return false;
        if (title != null ? !title.equals(advert.title) : advert.title != null) return false;
        if (text != null ? !text.equals(advert.text) : advert.text != null) return false;
        return publication != null ? publication.equals(advert.publication) : advert.publication == null;

    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (publication != null ? publication.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", account=" + account +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", publication=" + publication +
                '}';
    }
}
