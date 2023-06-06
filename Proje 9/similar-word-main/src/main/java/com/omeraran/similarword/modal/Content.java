package com.omeraran.similarword.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document("contents")
public class Content {
    @Id
    private String id;
    private String[] inputTexts;
    private String result;
    private Long elapsedTime;

    public Content(String[] contents, String result, Long elapsedTime) {
        inputTexts = contents;
       this.result = result;
       this.elapsedTime = elapsedTime;
    }
}