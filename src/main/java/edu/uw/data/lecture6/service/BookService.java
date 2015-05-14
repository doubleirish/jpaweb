package edu.uw.data.lecture6.service;

import edu.uw.data.lecture6.model.*;
import org.hibernate.stat.Statistics;

/**
 * Created by Conor on 4/12/2015.
 */
public interface BookService {

  Book findBookById(Integer id);
    Statistics getHibernateStatistics();

    Book createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);
}
