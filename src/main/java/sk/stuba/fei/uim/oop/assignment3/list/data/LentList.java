package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter

public class LentList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> lendingList;
    private boolean lended;

    public LentList(){
        this.lendingList=new ArrayList<>();
        this.lended=false;
    }
}
