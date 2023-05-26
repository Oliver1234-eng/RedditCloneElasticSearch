package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDocumentRepository extends ElasticsearchRepository<PostDocument, String> {

    List<PostDocument> findAllByTitle(String title);
    List<PostDocument> findAllByText(String text);
    List<PostDocument> findAllByUser(String user);
    List<PostDocument> findAllByFlair(String flair);
    List<PostDocument> findAllByCommunity(String community);
    List<PostDocument> findAllByCommentCountGreaterThanAndCommentCountLessThan(int commentCount1, int commentCount2);
    List<PostDocument> findAllByKarmaGreaterThanAndKarmaLessThan(int karma1, int karma2);
    List<PostDocument> findAllByFlairAndUser(String flair, String user);
    List<PostDocument> findAllByFlairOrUser(String flair, String user);
}
