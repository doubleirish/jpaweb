package edu.uw.data.lecture6.dao;


import edu.uw.data.lecture6.model.Book;
import edu.uw.data.lecture6.model.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * repeatable tests
 */

@RunWith(SpringJUnit4ClassRunner.class)

// JAVA CONFIG @ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = {   EmbeddedTestDataSourceInit.class,  PersistenceJPAConfig.class})
@ContextConfiguration(locations = {"classpath:/services-spring.xml",
       // "classpath:/datasource-embedded-init.xml"
         "classpath:/datasource-embedded-init-p6spy.xml"
        //  "classpath:/datasource-standalone-test.xml"
        //  "classpath:/datasource-standalone-p6spy-test.xml"
})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ActiveProfiles("dev")
public class BookDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(BookDaoTest.class);

    @Resource
    private BookDao bookDao;

    @Override
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }




    String proSpring3Isbn = "1430241071";   //find
    String springInActionIsbn = "161729120X"; //update
    String hibernateInActionIsbn = "193239415X"; //delete
    String javaPersistenceISBN = "1932394885"; //create

    @Test
    public void createBook() {

        //verify book does not already exist

        List<String> matchingISBNs = jdbcTemplate.queryForList(
                "select ISBN  from BOOKS  where ISBN = ?", String.class, javaPersistenceISBN);
        assertThat(matchingISBNs.size(), is(0));

        //build Book
        Book book = new Book.Builder()
                .isbn(javaPersistenceISBN)
                .author("Gavin King")
                .title("Java Persistence with Hibernate")
                .description("In this revised edition of the bestselling Hibernate in Action, authors Christian Bauer and Gavin King-the founder of the Hibernate project-cover Hibernate 3.2 in detail along with the EJB 3.0 and Java Persistence standard.")
                .price(59.99)
                .genre(new Genre(1))
                .build();

        //save book
        bookDao.createBook(book); // will be auto commited


        //verify book was saved by searching for it
        String foundIsbn = jdbcTemplate.queryForObject(
                "select ISBN  from BOOKS  where ISBN = ?", String.class, javaPersistenceISBN);
        log.info("found javaPersistenceISBN in database  " + foundIsbn);
        assertThat(foundIsbn, is(javaPersistenceISBN));


    }

    @Test
    public void findByISBN() {
//        INSERT INTO BOOKS (ISBN,          TITLE,            AUTHOR,        GENRE,  PRICE, DESCRIPTION )
//        values('1430241071', 'Pro Spring 3'  , 'Clarence Ho', 'Java',  19.99, 'Pro Spring 3 updates the bestselling Pro Spring with the latest that the Spring Framework has to offer');


        Book book = bookDao.findByISBN(proSpring3Isbn);
        log.info("findByISBN returns " + book);
        assertNotNull(book);
        assertThat(book.getIsbn(), is(proSpring3Isbn));
        assertThat(book.getTitle(), is("Pro Spring 3"));
        assertThat(book.getAuthor(), is("Clarence Ho"));
        assertThat(book.getGenre().getName(), is("Java"));
       // assertThat(new BigDecimal(book.getPrice()), eq(new BigDecimal(19.99));
        assertThat(book.getDescription(), is("Pro Spring 3 updates the bestselling Pro Spring with the latest that the Spring Framework has to offer"));


    }


    @Test
    public void updateBook() {

        //
        // check initial state
        //

        Book origBook = bookDao.findByISBN(springInActionIsbn);

        assertThat(origBook.getTitle(), is("Spring In Action"));

        String titleVer2 = "Spring In Action v2";

        //
        // update !
        //
        origBook.setTitle(titleVer2);
        bookDao.updateBook(origBook);


        //
        // verify book was updated
        //
        log.info(" book after update :" +origBook);
        Book updatedBook = bookDao.findByISBN(springInActionIsbn);
        log.info("updated book" +updatedBook);
        assertThat(updatedBook.getTitle(), is(titleVer2));
    }


    @Test
    public void deleteBook() {
       // Create a book object for the one we want to  delete
        Book hibernateInActionBook = new Book.Builder().isbn(hibernateInActionIsbn).build();

        // verify book is already in the database
        String foundIsbn = jdbcTemplate.queryForObject(
                "select ISBN  from BOOKS  where ISBN = ?", String.class, hibernateInActionIsbn);

        assertThat("can't delete book if it don't exist", foundIsbn, notNullValue());

        //  delete   book
        bookDao.deleteBook(hibernateInActionBook);


        //  lookup the book im the database
        List<String> matchinIsbnList = jdbcTemplate.queryForList(
                "select ISBN  from BOOKS  where ISBN = ?", String.class, hibernateInActionIsbn);
        //  and verify it's gone
        assertThat(matchinIsbnList.size(), is(0));
   }


    @Test
    public void findByGenre_Java() {
        String genre = "Java";
        List<Book> javaBooks = bookDao.findBooksByGenre(genre);
        assertThat(javaBooks.size(), greaterThan(0));
        for (Book javaBook : javaBooks) {
            assertThat(javaBook.getGenre().getName(), is(genre));
        }
    }

    @Test
    public void findByGenre_ScienceFiction() {
        String genre = "Sci Fi";
        List<Book> javaBooks = bookDao.findBooksByGenre(genre);
        assertThat(javaBooks.size(), greaterThan(0));
        for (Book javaBook : javaBooks) {
            assertThat(javaBook.getGenre().getName(), is(genre));
        }
    }

    @Test
    public void findAll() {
        List<Book> books = bookDao.findAllBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
        for (Book book : books) {
           log.info("book "+book);
        }
    }


/*

  @Test
     // TODO In the ClassicDaoImpl class annotate the method we call here with spring @Cacheable using the "mymethods" cache name
     public void findOffices_cache_method_Test_LAB() {
         long start;
         long duration;

         //
         // first call should pull from database and push into the method cache named "offices"
         //
         start = System.currentTimeMillis();
         bookDao.findAllBooks(); // TODO add @Cacheable to the method impl  called here
         duration = System.currentTimeMillis() - start;
         log.info("1st  took " + (duration) + " ms");

         //
         // second call should pull from cache
         //
         start = System.currentTimeMillis();
           bookDao.findAllBooks(); // TODO add @Cacheable to the method impl  called here
         duration = System.currentTimeMillis() - start;
         log.info("2nd  took " + duration + " ms");


          bookDao.getHibernateStatistics();

           bookDao.printEhcacheStatistics();

         //
         // assert we got a hit count in the "offices" cache we setup for findAllOffices_method_caching_LAB() method
         //
         CacheManager cacheManager = CacheManager.getInstance();
         Cache officesCache = cacheManager.getCache("mymethods");
         long cacheHits = officesCache.getStatistics().getCacheHits();
         System.out.println(" offices Cache hits =" + cacheHits);
         assertTrue(cacheHits > 0);

     }
*/

}
