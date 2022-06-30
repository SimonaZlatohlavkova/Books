package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;
import sk.stuba.fei.uim.oop.assignment3.list.data.LentList;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookId;
import java.util.List;

public interface InterfaceListService {
    List<LentList> getAll();
    LentList create() ;
    LentList getById(Long id)throws NotFound;
    void delete(Long id)throws NotFound;
    LentList addBookToList(Long listId, BookId body) throws NotFound, BadRequest;
    void deleteBook(Long idList, BookId body)throws NotFound;
    void lend(Long idList)throws NotFound, BadRequest;
}
