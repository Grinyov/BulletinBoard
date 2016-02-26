package com.grinyov.bulletinboard.model;

import java.io.Serializable;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * Entity used to store information about categories.
 */
public class Category implements Serializable{


    private static final long serialVersionUID = 3590778194628377058L;
    private long id;
    private String name;

    public Category(){}

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
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
