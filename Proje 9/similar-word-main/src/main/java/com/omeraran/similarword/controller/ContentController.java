package com.omeraran.similarword.controller;

import com.omeraran.similarword.business.ContentService;
import com.omeraran.similarword.modal.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<List<Content>> getAllContents(){
        return new ResponseEntity(contentService.getAllContents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable("id") String id){
        return new ResponseEntity(contentService.getContent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Content> compareInputs(@RequestBody String[] contents){
        return new ResponseEntity<>(contentService.compareInputs(contents), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Content> saveContent(@RequestBody Content content){
        return new ResponseEntity(contentService.saveContent(content), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll(){
        contentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
