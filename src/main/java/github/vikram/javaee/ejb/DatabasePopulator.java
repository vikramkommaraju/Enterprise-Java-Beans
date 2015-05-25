package github.vikram.javaee.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


@Singleton
@Startup
@DataSourceDefinition (
        className = "org.apache.derby.jdbc.EmbeddedDataSource",
        name = "java:global/jdbc/ejbDS",
        user = "app",
        password = "app",
        databaseName = "ejbDB",
        properties = {"connectionAttributes=;create=true"}
)
public class DatabasePopulator {
	
	@Inject
	private BookEJB bookEJB;
	
	Book book1;
	Book book2;
	
	
	@PostConstruct
	private void populateDB() {
		
		book1 = new Book("Beginning Java EE 7", 35F, "This is a great java book", "1-8763-9125-7", 605, true);
		book2 = new Book("The Lord of the Rings", 50.4F, "This is a great adventure book", "1-84023-742-2", 1216, true);
		
		System.out.println("Creating 2 books...");
		bookEJB.createBook(book1);
		bookEJB.createBook(book2);
		System.out.println("Created 2 books...");
	}
	
	@PreDestroy
	private void clearDB() {
		bookEJB.deleteBook(book1);
		bookEJB.deleteBook(book2);
		
	}
	
	

}
