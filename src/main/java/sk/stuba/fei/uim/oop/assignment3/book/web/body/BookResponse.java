package sk.stuba.fei.uim.oop.assignment3.book.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import javax.persistence.ManyToOne;

@Getter
public class BookResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final int pages;
    private final int amount;
    private final int lendCount;
    @ManyToOne
    private final Long author;

    public  BookResponse(Book b){
        this.author=b.getAuthor().getId();
        this.id= b.getId();
        this.name= b.getName();
        this.description=b.getDescription();
        this.amount=b.getAmount();
        this.pages=b.getPages();
        this.lendCount= b.getLendCount();
    }
}
