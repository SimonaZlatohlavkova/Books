package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.InterfaceAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookAmount;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;

import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository repository;
    @Autowired
    private InterfaceAuthorService authorService;

    @Override
    public List<Book> getAll() {
       return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws NotFound {
        Author author;
        author=authorService.getById(request.getAuthor());
        Book b = new Book(request, author);
        authorService.addBookToList(b,author);
        return this.repository.save(b);
    }

    @Override
    public Book getById(Long id) throws NotFound {
        Book book= this.repository.findBooksById(id);
        if(book==null){
            throw  new NotFound();
        }
        return book;
    }

    @Override
    public Book update(BookUpdateRequest body, Long id)throws NotFound {
        Book book=getById(id);
        if(body.getDescription()!=null){
         book.setDescription(body.getDescription());
        }
        if(body.getPages()!=0&&body.getPages()>0){
            book.setPages(body.getPages());
        }
        if(body.getName()!=null){
            book.setName(body.getName());
        }
        if(body.getAuthor()!=0){
            Author authorNew=authorService.getById(body.getAuthor());
            Author authorOld=book.getAuthor();
            this.authorService.removeBook(book,authorOld);
            this.authorService.addBookToList(book,authorNew);
            book.setAuthor(authorNew);
        }
        return this.repository.save(book);
    }

    @Override
    public void delete(Long id) throws NotFound{
        Book book=getById(id);
        this.authorService.removeBook(book,book.getAuthor());
        this.repository.delete(book);
    }

    @Override
    public int getAmount(Long id) throws NotFound {
        Book book = getById(id);
        return book.getAmount();
    }

    @Override
    public int addAmount(Long id, BookAmount amount) throws NotFound , BadRequest {
        Book book = getById(id);
        int currentAmount= book.getAmount();
        if(amount.getAmount()<0){
            throw new BadRequest();
        }
        book.setAmount(currentAmount+amount.getAmount());
        return book.getAmount();
    }

    @Override
    public int getLendCount(Long id) throws NotFound {
        Book book = getById(id);
        return  book.getLendCount();
    }
}
