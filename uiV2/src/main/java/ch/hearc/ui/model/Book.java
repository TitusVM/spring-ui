package ch.hearc.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The API sends something like this: { "id": 1, "author": "François Beaujoli",
 * "name": "Maniefestation en France ?", "editor": "Payot", "release":
 * "2023-04-22" },
 * 
 * So we need to create a Book class that matches this structure.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
	// Attributes
	private Long id;
	private String author;
	private String name;
	private String editor;
	private String release;
}