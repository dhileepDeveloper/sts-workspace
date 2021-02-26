package com.practise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.practise.entity.Book;
import com.practise.repo.BookRepository;
@RestController
@RequestMapping(path="/book",produces={"application/json","application/xml"})
public class BookController {
	
	@Value("${server.port}")
	private int serverPort;

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping()
	public String home()
	{
		return "Welcome to Book Service";
	}
	
	/*
	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id)
	{
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent())
			return book.get();
		return null;
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id)
	{
		Optional<Book> book = bookRepository.findById(id);
		System.out.println("Server port is " + serverPort);
		if(book.isPresent())
		{
			book.get().setName(book.get().getName() + serverPort);
			return new ResponseEntity(book,HttpStatus.OK);
		}
		System.out.println();
		return new ResponseEntity(new Book(),HttpStatus.NOT_FOUND);																							
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<List<Book>> getAllBooks()
	{
		List<Book> bookList = bookRepository.findAll();
		return new ResponseEntity(bookList,HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes= {"application/json","application/xml"})
	public ResponseEntity<Book> createBook(@RequestBody Book book)
	{
		System.out.println("Book sended : " + book);
		if(book.getName() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Book or author name shouldn't be null");
		Book output =  bookRepository.save(book);	
		return new ResponseEntity<Book>(output,HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path="/{id}",consumes = {"application/json","application/xml"})
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Long id)
	{
		Optional<Book> tmpBook = bookRepository.findById(id);
		if(tmpBook.isPresent())
		{
			book.setId(id);
			book = bookRepository.save(book);
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);								
	}
	
	@PatchMapping(path="/{id}",consumes = {"application/json","application/xml"})
	public ResponseEntity<Book> partialBookUpdate(@RequestBody Book book,@PathVariable Long id)
	{
		System.out.println("Patch is called");
		Optional<Book> tmpBook = bookRepository.findById(id);
		if(tmpBook.isPresent())
		{
			Book persistedBook = tmpBook.get();
			if(book.getName() != null)
				persistedBook.setName(book.getName());
			if(book.getAuthor() != null)
				persistedBook.setAuthor(book.getAuthor());				
			persistedBook = bookRepository.save(persistedBook);
			return new ResponseEntity<Book>(persistedBook,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);					
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteBook(@PathVariable Long id)
	{
		Optional<Book> tmpBook = bookRepository.findById(id);
		if(tmpBook.isPresent())
			bookRepository.deleteById(id);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book doesn't exist");
	}
	
	
}
