package com.omeraran.similarword.repository;

import com.omeraran.similarword.modal.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

}
