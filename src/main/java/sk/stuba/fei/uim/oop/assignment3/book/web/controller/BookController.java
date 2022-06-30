package sk.stuba.fei.uim.oop.assignment3.book.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookAmount;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookResponse> getAllBooks(){
        List<BookResponse> temp= new ArrayList<>();
        for(Book book: this.service.getAll()){
            temp.add(new BookResponse(book));
        }
        return temp;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse>addBook(@RequestBody BookRequest body) throws NotFound {
            return new ResponseEntity<>(new BookResponse(this.service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value= {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse getBookById(@PathVariable("id") Long bookId) throws NotFound{
        return (new BookResponse(this.service.getById(bookId)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse updateBook(@PathVariable("id") Long bookId, @RequestBody BookUpdateRequest body) throws NotFound {
        return new BookResponse(this.service.update(body, bookId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFound{
        this.service.delete(bookId);
    }
    @GetMapping(value = "/{id}/amount",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookAmount getBookAmount(@PathVariable("id") Long bookId) throws NotFound{
        return new BookAmount(this.service.getAmount(bookId));
    }

    @PostMapping(value = "/{id}/amount",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookAmount addBookAmount(@PathVariable("id") Long bookId,@RequestBody BookAmount body) throws NotFound, BadRequest {
        return new BookAmount(this.service.addAmount(bookId,body));
    }

    @GetMapping(value = "/{id}/lendCount",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookAmount getLendCount(@PathVariable("id") Long bookId) throws NotFound{
        return new BookAmount(this.service.getLendCount(bookId));
    }

}
