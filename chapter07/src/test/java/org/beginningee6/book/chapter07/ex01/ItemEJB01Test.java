package org.beginningee6.book.chapter07.ex01;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemEJB01Test {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        ec = EJBContainer.createEJBContainer();
        ctx = ec.getContext();
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null)
            ec.close();
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateABook() throws Exception {

        // Creates an instance of book
        Book01 book = new Book01();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);

        // Looks up the EJB
        ItemEJB01 itemEJB = (ItemEJB01) ctx.lookup("java:global/classes/ItemEJB01!org.beginningee6.book.chapter07.ex01.ItemEJB01");

        // Persists the book to the database
        book = itemEJB.createBook(book);
        assertNotNull("ID should not be null", book.getId());

        // Retrieves all the books from the database
        List<Book01> books = itemEJB.findBooks();
        assertNotNull(books);
    }

    @Test
    public void shouldCreateACD() throws Exception {

        // Creates an instance of CD01
        CD01 cd = new CD01();
        cd.setTitle("Zoot Allure");
        cd.setPrice(23f);
        cd.setDescription("Another Zappa's master piece");
        cd.setMusicCompany("Zappa Corp.");
        cd.setNumberOfCDs(1);
        cd.setTotalDuration(65f);
        cd.setGender("Rock");

        // Looks up the EJB
        ItemEJB01 itemEJB = (ItemEJB01) ctx.lookup("java:global/classes/ItemEJB01!org.beginningee6.book.chapter07.ex01.ItemEJB01");

        // Persists the book to the database
        cd = itemEJB.createCD(cd);
        assertNotNull("ID should not be null", cd.getId());

        // Retrieves all the books from the database
        List<CD01> cds = itemEJB.findCDs();
        assertNotNull(cds);
    }
}