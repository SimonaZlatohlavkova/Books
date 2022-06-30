package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;

import java.util.List;

@Service
public class AuthorService implements InterfaceAuthorService{

    @Autowired
    private AuthorRepository repository;


    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.repository.save(new Author(request));
    }

    @Override
    public Author getById(long authorId) throws NotFound {
        Author author= this.repository.findAuthorById(authorId);
        if(author==null){
            throw  new NotFound();
        }
        return author;
    }

    @Override
    public Author update(AuthorUpdateRequest body, long id) throws NotFound {
        Author author= getById(id);
        if(body.getName()!=null){
            author.setName(body.getName());
        }
        if(body.getSurname()!=null){
            author.setSurname(body.getSurname());
        }
        return this.repository.save(author);
    }

    @Override
    public void delete(long authorId) throws NotFound {
        Author author= getById(authorId);
        this.repository.delete(author);
    }

    @Override
    public void addBookToList(Book b, Author author) {
        author.getBooks().add(b);
    }

    @Override
    public void removeBook(Book b, Author author) {
        author.getBooks().remove(b);
    }

}
