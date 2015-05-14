package edu.uw.data.lecture6.service;

import edu.uw.data.lecture6.dao.*;
import edu.uw.data.lecture6.model.*;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

/**
 * Created by Conor on 4/12/2015.
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    BookDao bookDao;

    @Override
    public Book createBook(Book book) {
        return bookDao.createBook(book);
    }

    @Override
    public void updateBook(Book book) {
          bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
       bookDao.deleteBook(book);
    }

  @Override
     public Book findBookById(Integer id) {
        return bookDao.findBookById(id);
     }


     @Override
     public Statistics getHibernateStatistics() {
           return bookDao.getHibernateStatistics();
     }
}
