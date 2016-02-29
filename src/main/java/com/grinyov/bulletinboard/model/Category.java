package com.grinyov.bulletinboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * Entity used to store information about categories.
 */
@Entity
@Table(name = "categories", schema = "", catalog = "bulletin_board")
@NamedQueries({
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
@NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId")}
)
public class Category implements Serializable{

    private static final long serialVersionUID = -2371179084452708204L;
    private long id;
    @NotNull(message = "Name category can't be empty.")
    @Size(min = 3, max = 30, message = "Name category size must be between 3 and 30 characters long.")
    private String name;

    public Category(){}

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        return name != null ? name.equals(category.name) : category.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
