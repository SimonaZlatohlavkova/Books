package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;


    public Author(AuthorRequest request){
        this.books= new ArrayList<>();
        this.name=request.getName();
        this.surname=request.getSurname();
    }


}
