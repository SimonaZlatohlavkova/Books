package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookAmount;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;
import java.util.List;

public interface IBookService {
    List<Book>getAll();
    Book create(BookRequest request)throws NotFound;
    Book getById(Long id)throws NotFound;
    Book update(BookUpdateRequest body, Long id)throws NotFound;
    void delete(Long id)throws NotFound;
    int getAmount(Long id)throws NotFound;
    int addAmount(Long id, BookAmount amount)throws NotFound, BadRequest;
    int getLendCount(Long id)throws NotFound;
}
