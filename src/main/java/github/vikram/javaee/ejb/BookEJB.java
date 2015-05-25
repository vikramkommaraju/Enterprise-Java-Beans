package github.vikram.javaee.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {
	
	@Inject
	EntityManager em;
	
	public Book findBookById(Long id) {
		return em.find(Book.class, id);
	}

	public List<Book> findBooks() {
		TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
		return query.getResultList();
	}

	public Book createBook(@NotNull Book book) {
		em.persist(book);
		return book;
	}

	public void deleteBook(@NotNull Book book) {
		em.remove(em.merge(book));
		
	}

	public Book updateBook(@NotNull Book book) {
		em.merge(book);
		return book;
	}
	
	

}
