package edu.uw.data.lecture6.dao;

import edu.uw.data.lecture6.model.Book;
import edu.uw.data.lecture6.model.Genre;

import java.util.List;

/**
 *
 * @author Bcarrick
 */
public interface BookDao {
  // CRUD
  Book createBook(Book book);
  Book findByISBN(String isbn);
  Book  findBookById(Integer id);
  void updateBook(Book book);
  void deleteBook(Book book);

  // Queries
  List<Book> findBooksByGenre(String genre);
  List<Book> findAllBooks();

  Genre findGenreByName(String genreName) ;


         // print
  public   org.hibernate.stat.Statistics getHibernateStatistics() ;


public   void  printEhcacheStatistics() ;
}
