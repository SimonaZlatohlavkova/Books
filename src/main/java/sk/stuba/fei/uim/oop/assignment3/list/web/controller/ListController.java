package sk.stuba.fei.uim.oop.assignment3.list.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;
import sk.stuba.fei.uim.oop.assignment3.list.data.LentList;
import sk.stuba.fei.uim.oop.assignment3.list.logic.InterfaceListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.BookId;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private InterfaceListService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListResponse> getAllLists(){
        List<ListResponse> temp= new ArrayList<>();
        for(LentList list: this.service.getAll()){
            temp.add(new ListResponse(list));
        }
        return temp;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponse> addList()  {
        return new ResponseEntity<>(new ListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value= "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse getListById(@PathVariable("id") Long listId) throws NotFound{
        return (new ListResponse(this.service.getById(listId)));
    }

    @DeleteMapping(value= "/{id}")
    public void deleteList(@PathVariable("id") Long listId) throws NotFound{
        this.service.delete(listId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse addBookToList(@PathVariable("id") Long listId, @RequestBody BookId bookId) throws NotFound, BadRequest {
        return new ListResponse(this.service.addBookToList(listId,bookId));
    }
    @DeleteMapping(value= "/{id}/remove",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBookFromList(@PathVariable("id") Long listId, @RequestBody BookId body) throws NotFound{
        this.service.deleteBook(listId,body);
    }

    @GetMapping(value= "/{id}/lend")
    public void lendList(@PathVariable("id") Long listId) throws NotFound, BadRequest {
      this.service.lend(listId);
    }
}
