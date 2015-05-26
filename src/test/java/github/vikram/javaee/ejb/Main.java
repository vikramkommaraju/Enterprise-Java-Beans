package github.vikram.javaee.ejb;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();
		
		BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/ejb-0.0.1-SNAPSHOT/BookEJB!github.vikram.javaee.ejb.BookEJBRemote");
		
		
	    List<Book> books = bookEJB.findBooks();
	    System.out.println("##################################################");
	    for (Book book : books) {
	      System.out.println(book);
	    }
	    System.out.println("##################################################");
	    
	    
	    Book b = new Book("Test Book", 20F, "This is a test book", "1-234456-3232-12", 100, false);
	    System.out.println("Persisting book..." + b);    
	    b = bookEJB.createBook(b);
	    System.out.println("Persisted book:" + b);
	    System.out.println("##################################################");
	    
	    b.setDescription("Updated description for test book");
	    b = bookEJB.updateBook(b);
	    System.out.println("Updated book description to: " + b);
	    System.out.println("##################################################");
	    
	    System.out.println("Deleting book...");
	    bookEJB.deleteBook(b);
	    System.out.println("Deleted book: " + b);
	    System.out.println("##################################################");
	    
	    System.out.println("Getting list of all books again");
	    for (Book book : bookEJB.findBooks()) {
		      System.out.println(book);
		    }
	    

	}

}
