package github.vikram.javaee.ejb;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;



public class BookEJBIT {
	
	  @Test
	  public void shouldCreateBook() throws Exception{
	 
		  Map<String, Object> properties = new HashMap<>();
		    properties.put(EJBContainer.MODULES, new File("target/classes"));
		 
		    try ( EJBContainer ec = EJBContainer.createEJBContainer(properties) ) {
		      Context ctx = ec.getContext();
		      
		      assertNotNull(ctx.lookup("java:global/jdbc/ejbDS"));
		      assertNotNull(ctx.lookup("java:global/classes/BookEJB!github.vikram.javaee.ejb.BookEJBRemote"));
		      assertNotNull(ctx.lookup("java:global/classes/BookEJB!github.vikram.javaee.ejb.BookEJB"));
		      
		      
		      BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/classes/BookEJB!github.vikram.javaee.ejb.BookEJB");
		      
		      for(Book bk : bookEJB.findBooks()) {
		    	  System.out.println("Book-->"+bk);
		      }
		      
		      assertEquals(bookEJB.findBooks().size(), 2);
		      
		      Book b = new Book("Test Book", 30F, "This is a test book", "1-234567-8901-2", 100, true);
		      
		      b = bookEJB.createBook(b);
		      
		      assertNotNull("Id should not be null!", b.getId());
		      
		      for(Book bk : bookEJB.findBooks()) {
		    	  System.out.println("Book-->"+bk);
		      }
		      
		      assertEquals(bookEJB.findBooks().size(), 3);
		      
		      bookEJB.deleteBook(b);
		      
		      assertEquals(bookEJB.findBooks().size(), 2);
		      
		      System.out.println("Done testing.");
		     
		    }
	    
	}
	
	
	

}
