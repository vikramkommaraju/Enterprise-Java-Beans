package github.vikram.javaee.ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface BookEJBRemote {
	
	public List<Book> findBooks();
	public Book createBook(Book book);
	public void deleteBook(Book book);
	public Book updateBook(Book book);

}
