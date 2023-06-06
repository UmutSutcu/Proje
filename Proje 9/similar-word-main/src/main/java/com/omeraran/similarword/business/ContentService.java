package com.omeraran.similarword.business;

import com.omeraran.similarword.modal.Content;
import com.omeraran.similarword.repository.ContentRepository;
import com.omeraran.similarword.util.ContentUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;

@Service
public class ContentService {

    private final ContentUtil contentUtil;

    private final ContentRepository contentRepository;

    public ContentService(ContentUtil contentUtil, ContentRepository contentRepository) {
        this.contentUtil = contentUtil;
        this.contentRepository = contentRepository;
    }

    public List<Content> getAllContents(){
        return contentRepository.findAll();
    }

    public Content getContent(String id){
        return contentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Get Content Error"));
    }

    public Content saveContent(Content content){
        return contentRepository.save(content);
    }

    public void deleteAll(){
        contentRepository.deleteAll();
        ResponseEntity.ok(HttpStatus.OK);
    }

    public Content compareInputs(String[] contents) {
        Long startTime = System.nanoTime();
        String result = contentUtil.generateFromMultipleString(contents);
        Long endTime = System.nanoTime();
        Long elapsedTime = (endTime-startTime);
        Content newContent = new Content(contents,result,elapsedTime);
        return newContent;
    }
}
