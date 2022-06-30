package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;

import java.util.List;

public interface InterfaceAuthorService {
    List<Author> getAll();
    Author create(AuthorRequest request);
    Author getById(long authorId) throws NotFound;
    Author update(AuthorUpdateRequest body, long id) throws  NotFound;
    void delete(long authorId) throws NotFound;
   void addBookToList(Book b, Author author);
   void removeBook(Book b, Author author);

}
