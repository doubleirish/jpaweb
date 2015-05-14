package edu.uw.data.lecture6.dao;

import edu.uw.data.lecture6.model.Book;

import java.util.List;

/**
 *
 * @author Bcarrick
 */
public interface BookDao {
  // CRUD
  Book createBook(Book book);
  Book findByISBN(String isbn);
  void updateBook(Book book);
  void deleteBook(Book book);

  // Queries
  List<Book> findByGenre(String genre);
  List<Book> findAll();


         // print
  public   org.hibernate.stat.Statistics getHibernateStatistics() ;


public   void  printEhcacheStatistics() ;
}
