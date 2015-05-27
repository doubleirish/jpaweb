package edu.uw.data.lecture6.model;

import javax.persistence.*;

/**
 * User can have one Adress but many Phone Numbers
 */
@Entity
// TODO add hibernate cache annotation here
@Table(name="BOOKS")
public class Book {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="ISBN")
    private String isbn;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="AUTHOR")
    private String author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GENRE_ID")
    private Genre genre;

    @Column(name="PRICE")
    private double price;


  public Book() {
  }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
            //    ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }


    public static class Builder {
    private Book book;

    public Builder() {
      book = new Book();
    }

    public Builder builder() {
      return new Builder();
    }

        public Builder id(Integer id) {
            book.setId(id);
            return this;
        }

    public Builder isbn(String isbn) {
      book.setIsbn(isbn);
      return this;
    }

    public Builder title(String title) {
      book.setTitle(title);
      return this;
    }

    public Builder description(String description) {
      book.setDescription(description);
      return this;
    }

    public Builder author(String author) {
      book.setAuthor(author);
      return this;
    }

    public Builder genre(Genre  genre) {
      book.setGenre(genre);
      return this;
    }

        public Builder price(Double  price) {
            book.setPrice(price);
            return this;
        }



    public Book build() {
      //validate ??;
      return book;
    }
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Book book = (Book) o;

        if (!isbn.equals(book.isbn)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
