package edu.uw.data.lecture6.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by credmond on 21/03/15.
 */
@Entity
@Table(name = "GENRE")
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

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

    public Genre() {
    }

    public Genre(Integer id) {
        this.id = id;
    }
    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return   name  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre that = (Genre) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
