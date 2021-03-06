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
    private long categoryId;
    @NotNull(message = "Name category can't be empty.")
    @Size(min = 3, max = 30, message = "Name category size must be between 3 and 30 characters long.")
    private String name;

    public Category(){}

    public Category(long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public long getId() {
        return categoryId;
    }

    public void setId(long id) {
        this.categoryId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        return name != null ? name.equals(category.name) : category.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + categoryId
                +
                ", name='" + name + '\'' +
                '}';
    }
}
