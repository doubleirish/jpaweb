package edu.uw.data.lecture6.service;


import edu.uw.data.lecture6.config.EmbeddedTestDataSourceInit;
import edu.uw.data.lecture6.config.PersistenceJPAConfig;
import edu.uw.data.lecture6.model.Book;
import org.hamcrest.core.Is;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Embedded database is  always initialized cleanly  as its stored in the target sub dir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(loader=AnnotationConfigContextLoader.class,
        classes = {
               // EmbeddedDataSource.class,
                    EmbeddedTestDataSourceInit.class,
               // DataSourceStandaloneConfig.class,
        PersistenceJPAConfig.class,

})


@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ActiveProfiles("dev")
public class BookServiceTest extends AbstractJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(BookServiceTest.class);

    @Resource(name="bookService")
    private BookService service;




    @Test
    public void verifyCustomerEntityIsCached() {
        long start = System.currentTimeMillis();
       Book book = service.findBookById(1);
        log.info(" first cust " +book);
        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " + duration + " ms");

        start = System.currentTimeMillis();
      Book book2 = service.findBookById(1);
        log.info(" seond  cust " +book2);
        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " + duration + " ms");

        Statistics stats = service.getHibernateStatistics();


        String regionName = "edu.uw.data.lecture6.model.Book";
        SecondLevelCacheStatistics level2CustomerEntityStats = stats.getSecondLevelCacheStatistics(regionName);
        assertNotNull("ehcache region may be missing or incorrectly setup, or @Cache annotation may not exist for: "+regionName,level2CustomerEntityStats);

        log.info("2nd Level Cache(" + regionName + ") Put Count: " + level2CustomerEntityStats.getPutCount());
        log.info("2nd Level Cache(" + regionName + ") HIt Count: " + level2CustomerEntityStats.getHitCount());
        log.info("2nd Level Cache(" + regionName + ") Miss Count: " + level2CustomerEntityStats.getMissCount());

        assertThat(level2CustomerEntityStats.getHitCount() , Is.is(greaterThan(0L)));

    }




}