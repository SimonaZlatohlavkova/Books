package sk.stuba.fei.uim.oop.assignment3.list.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.list.data.LentList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListResponse {
    private final Long id;
    private final List<BookResponse> lendingList;
    private final boolean lended;

    public ListResponse(LentList list){
        this.id=list.getId();
        this.lended=list.isLended();
        this.lendingList=list.getLendingList().stream().map(BookResponse::new).collect(Collectors.toList());
    }

}
