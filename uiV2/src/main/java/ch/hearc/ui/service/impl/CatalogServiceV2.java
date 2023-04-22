package ch.hearc.ui.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ch.hearc.ui.model.Book;
import ch.hearc.ui.service.CatalogService;

/**
 * This implementation of the CatalogService interface uses the REST API to
 * communicate with the catalog microservice.
 */

@Service
public class CatalogServiceV2 implements CatalogService {

	private static final String CATALOG_URL = "http://localhost:8081/api/";

	private RestTemplate restTemplate;

	public CatalogServiceV2() {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<Book> fetchBooks() {
		ResponseEntity<List<Book>> response = restTemplate.exchange(CATALOG_URL + "/books", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		System.out.println(response);
		return response.getBody();
	}

	@Override
	public void createBook(Book book) {
		restTemplate.postForObject(CATALOG_URL + "/books", book, Book.class);
	}

	@Override
	public Book updateBook(Book book) {
		restTemplate.put(CATALOG_URL + "/books/" + book.getId(), book);
		return book;
	}

	@Override
	public void deleteBook(Long id) {
		restTemplate.delete(CATALOG_URL + "/books/" + id);
	}

	@Override
	public Book getBookById(Long id) {
		return restTemplate.getForObject(CATALOG_URL + "/books/" + id, Book.class);
	}

}