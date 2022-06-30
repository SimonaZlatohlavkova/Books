package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;
import sk.stuba.fei.uim.oop.assignment3.list.data.LentList;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookId;
import java.util.List;

@Service
public class ListService implements InterfaceListService{


    @Autowired
    private ListRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<LentList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LentList create()  {
       return this.repository.save(new LentList());
    }

    @Override
    public LentList getById(Long id) throws NotFound {
        LentList list= this.repository.findListById(id);
        if(list==null){
            throw new NotFound();
        }
       return list;
    }

    @Override
    public void delete(Long id) throws NotFound {
        LentList list=getById(id);
        if(list.isLended()==true){
            for(Book b:list.getLendingList()){
                int currentLendCount= b.getLendCount();
                b.setLendCount(currentLendCount-1);
            }
        }
        this.repository.delete(list);
    }

    @Override
    public LentList addBookToList(Long listId, BookId body) throws NotFound, BadRequest {
        LentList list=getById(listId);
        if(list.isLended()){
            throw new BadRequest();
        }
        Long id=body.getId();
        Book b=this.bookService.getById(id);
        for(int i =0; i<list.getLendingList().size(); i++){
            if(list.getLendingList().get(i)==b){
                throw  new BadRequest();
            }
        }
        list.getLendingList().add(b);
        return this.repository.save(list);
    }

    @Override
    public void deleteBook(Long idList, BookId bookId) throws NotFound{
        Book b=bookService.getById(bookId.getId());
        LentList list=getById(idList);
        if(list.isLended()){
            throw new NotFound();
        }
        list.getLendingList().remove(b);
    }

    @Override
    public void lend(Long idList) throws NotFound, BadRequest {
        LentList list=getById(idList);
        if(list.isLended()){
            throw new BadRequest();
        }
        list.setLended(true);
        for(Book b:list.getLendingList()){
            int currentLendCount= b.getLendCount();
            if(b.getAmount()==currentLendCount){throw new BadRequest();}
            b.setLendCount(currentLendCount+1);
        }

    }
}
