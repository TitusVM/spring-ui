package ch.hearc.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.ui.model.Book;
import ch.hearc.ui.service.CatalogService;

@Controller
public class HomeController {

	@Autowired
	CatalogService catalogService;

	@GetMapping(value = { "/", "/accueil" })
	public String showAccueilPage(Model model) {

		model.addAttribute("userSession", null);
		model.addAttribute("showAcc", Boolean.TRUE);
		model.addAttribute("showNew", Boolean.FALSE);
		return "accueil";
	}

	@GetMapping(value = { "/show-books" })
	public String showBooks(Model model) {
		model.addAttribute("books", catalogService.fetchBooks());

		model.addAttribute("isGlobal", Boolean.TRUE);
		return "showBook";
	}

	@GetMapping(value = { "/new-book" })
	public String showCreateBook(Model model) {

		model.addAttribute("book", new Book());
		model.addAttribute("showNew", Boolean.TRUE);
		model.addAttribute("isNew", Boolean.TRUE);
		model.addAttribute("isEdit", Boolean.FALSE);
		return "showBook";
	}

	@PostMapping(value = "/save-book")
	public String saveBeer(@ModelAttribute Book book, BindingResult errors, Model model, @RequestParam String type) {

		if (type.equals("new")) {
			catalogService.createBook(book);
		} else {
			catalogService.deleteBook(book.getId());
			catalogService.createBook(book);
		}

		return "redirect:/show-books";
	}

	@PostMapping(value = "/delete-book")
	public String removeFromCollection(@ModelAttribute Book book, BindingResult errors, Model model) {
		model.addAttribute("isGlobal", Boolean.FALSE);

		try {
			catalogService.deleteBook(book.getId());
		} catch (Exception e) {
			return "redirect:/show-books";

		}
		return "redirect:/acceuil";
	}
}
