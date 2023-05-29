package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.CommentDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDocumentRepository extends ElasticsearchRepository<CommentDocument, String> {

    List<CommentDocument> findAllByText(String text);
}
