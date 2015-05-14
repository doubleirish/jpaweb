package edu.uw.data.lecture6.dao;

import edu.uw.data.lecture6.model.Book;
import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


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
    @Cacheable("mymethods")
    public List<Book> findAll() {
       return    em.createQuery("select b from Book b",Book.class)
                .getResultList();
    }


  @Override
  public org.hibernate.stat.Statistics getHibernateStatistics() {
    Session session = (Session) em.getDelegate();
    SessionFactory sessionFactory = session.getSessionFactory();
    Statistics stats = sessionFactory.getStatistics();
    System.out.println("Hibernate Cache Stats ->" + stats);


    String regions[] = stats.getSecondLevelCacheRegionNames();

    for (String regionName : regions) {
      SecondLevelCacheStatistics stat2 = stats.getSecondLevelCacheStatistics(regionName);
      log.info("2nd Level Cache(" + regionName + ") Put Count: " + stat2.getPutCount());
      log.info("2nd Level Cache(" + regionName + ") HIt Count: " + stat2.getHitCount());
      log.info("2nd Level Cache(" + regionName + ") Miss Count: " + stat2.getMissCount());
    }
    return stats;
  }

  @Override
  public void printEhcacheStatistics() {
    CacheManager cacheManager = CacheManager.getInstance();
    String[] cacheNames = cacheManager.getCacheNames();
    for (String cacheName : cacheNames) {
      net.sf.ehcache.Statistics statistics = cacheManager.getCache(cacheName).getStatistics();
      log.info(cacheName + " - " + statistics.toString());
    }
}

}
