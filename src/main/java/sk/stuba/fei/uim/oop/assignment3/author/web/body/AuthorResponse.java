package sk.stuba.fei.uim.oop.assignment3.author.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import java.util.ArrayList;
import java.util.List;


@Getter
public class AuthorResponse {
    private Long id;
    private String name;
    private String surname;
    private List<Long> books;
    public AuthorResponse(Author author){
        this.id=author.getId();
        this.name=author.getName();
        this.surname= author.getSurname();
        this.books= new ArrayList<>();
        for(Book b: author.getBooks()){
            books.add(b.getId());
        }
    }



}
