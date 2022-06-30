package sk.stuba.fei.uim.oop.assignment3.author.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.InterfaceAuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFound;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")

public class AuthorController {
    @Autowired
    private InterfaceAuthorService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorResponse> getAllAuthors(){
        List<AuthorResponse> temp= new ArrayList<>();
        for(Author a: this.service.getAll()){
            temp.add(new AuthorResponse(a));
        }
        return temp;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest body){
        return  new ResponseEntity<>(new AuthorResponse(this.service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse getAuthorByID(@PathVariable("id") Long authorId) throws NotFound {
        return new AuthorResponse(this.service.getById(authorId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse updateAuthor(@PathVariable("id") Long authorId, @RequestBody AuthorUpdateRequest body) throws NotFound {
               return new AuthorResponse(this.service.update(body, authorId));
    }
    @DeleteMapping(value = "/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws NotFound{
        this.service.delete(authorId);
    }


}
