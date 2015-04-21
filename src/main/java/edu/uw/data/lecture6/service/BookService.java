package edu.uw.data.lecture6.service;

import edu.uw.data.lecture6.model.*;

/**
 * Created by Conor on 4/12/2015.
 */
public interface BookService {
    Book createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);
}
