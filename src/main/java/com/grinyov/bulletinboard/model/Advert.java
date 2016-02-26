package com.grinyov.bulletinboard.model;

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
public class Advert implements Serializable{

    private static final long serialVersionUID = 6935513514218522345L;
    private long id;
    private Account account;
    private Category category;

    @NotNull(message = "title can't be empty")
    @Size(min = 10, max = 30, message = "title size must be between 10 and 30 characters long")
    private String title;

    @NotNull(message = "advert can't be empty")
    @Size(min = 20, max = 400, message = "message size must be between 20 and 400 characters long")
    private String text;

    Timestamp publication;

    public Advert(){}

    public Advert(Account account, Category category, String title, String text, Timestamp publication, long id) {
        this.account = account;
        this.category = category;
        this.title = title;
        this.text = text;
        this.publication = publication;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
