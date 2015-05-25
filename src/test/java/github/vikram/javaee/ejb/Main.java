package github.vikram.javaee.ejb;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();
		
		BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/ejb-0.0.1-SNAPSHOT/BookEJB!github.vikram.javaee.ejb.BookEJBRemote");
		
		 // Gets and displays all the books from the database
	    List<Book> books = bookEJB.findBooks();
	    for (Book book : books) {
	      System.out.println(book);
	    }

	}

}
