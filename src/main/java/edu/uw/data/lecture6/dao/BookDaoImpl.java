package edu.uw.data.lecture6.dao;

import edu.uw.data.lecture6.model.*;
import org.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;


@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    static final Logger log = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book findByISBN(String isbn) {
        return em.createQuery("select b from Book b where b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getResultList().get(0);
    }

    @Override
    public void updateBook(Book book) {

            em.merge(book);

        log.info("saved "+book.getId());
       // return book;

    }

    @Override
    public void deleteBook(Book book) {
        if (book.getId()!=null) {
            em.remove(book);
        } else if (book.getIsbn()!=null) {
            em.createQuery("DELETE FROM Book b WHERE b.isbn = :isbn")
                    .setParameter("isbn", book.getIsbn())
                    .executeUpdate();
        } else {
            throw new IllegalArgumentException("User does not contain identifying id or isbn "+book);
        }
        log.info("updated book"+book);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return em.createQuery("select b from Book b where b.genre = :genre", Book.class)
                .setParameter("genre", genre)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
       return    em.createQuery("select b from Book b",Book.class)
                .getResultList();
    }

}
