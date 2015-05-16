package edu.uw.data.lecture6.service;


import edu.uw.data.lecture6.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Embedded database is  always initialized cleanly  as its stored in the target sub dir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)

/**
 * JAVA CONFIG
 */
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class,  classes = {  EmbeddedTestDataSourceInit.class,  PersistenceJPAConfig.class,})
@ContextConfiguration(locations = {"classpath:/services-spring.xml",
        "classpath:/datasource-embedded-init.xml"
        //  "classpath:/datasource-embedded-init-p6spy.xml"
        //  "classpath:/datasource-standalone-test.xml"
        //  "classpath:/datasource-standalone-p6spy-test.xml"
})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ActiveProfiles("dev")
public class BookServiceTest extends AbstractJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(BookServiceTest.class);

    @Resource(name="bookService")
    private BookService bookService;



    @Test
    public void findAll() {
        List<Book> books = bookService.findAllBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
        for (Book book : books) {
            log.info("book "+book);
        }
    }

/*

    @Test
    public void verifyCustomerEntityIsCached() {
        long start = System.currentTimeMillis();
       Book book = bookService.findBookById(1);
        log.info(" first cust " +book);
        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " + duration + " ms");

        start = System.currentTimeMillis();
      Book book2 = bookService.findBookById(1);
        log.info(" seond  cust " +book2);
        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " + duration + " ms");

        Statistics stats = bookService.getHibernateStatistics();


        String regionName = "edu.uw.data.lecture6.model.Book";
        SecondLevelCacheStatistics level2CustomerEntityStats = stats.getSecondLevelCacheStatistics(regionName);
        assertNotNull("ehcache region may be missing or incorrectly setup, or @Cache annotation may not exist for: "+regionName,level2CustomerEntityStats);

        log.info("2nd Level Cache(" + regionName + ") Put Count: " + level2CustomerEntityStats.getPutCount());
        log.info("2nd Level Cache(" + regionName + ") HIt Count: " + level2CustomerEntityStats.getHitCount());
        log.info("2nd Level Cache(" + regionName + ") Miss Count: " + level2CustomerEntityStats.getMissCount());

        assertThat(level2CustomerEntityStats.getHitCount() , Is.is(greaterThan(0L)));

    }

*/


}