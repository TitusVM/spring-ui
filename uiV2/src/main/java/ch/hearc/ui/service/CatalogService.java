package ch.hearc.ui.service;

import java.util.List;

import ch.hearc.ui.model.Book;

public interface CatalogService {

	// Book
	public List<Book> fetchBooks();

	public void createBook(Book book);

	public Book updateBook(Book book);

	public void deleteBook(Long id);

	public Book getBookById(Long id);
}