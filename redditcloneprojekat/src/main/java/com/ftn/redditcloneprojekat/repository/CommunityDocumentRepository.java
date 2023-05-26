package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.CommunityDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDocumentRepository extends ElasticsearchRepository<CommunityDocument, String> {

    List<CommunityDocument> findAllByName(String name);
    List<CommunityDocument> findAllByDescription(String description);
    List<CommunityDocument> findAllByRules(String rules);
    List<CommunityDocument> findAllBySuspendedReason(String suspendedReason);
    List<CommunityDocument> findAllByUser(String user);
    List<CommunityDocument> findAllByPostCountGreaterThanAndPostCountLessThan(int postCount1, int postCount2);
    List<CommunityDocument> findAllByAverageKarmaGreaterThanAndAverageKarmaLessThan(int averageKarma1, int averageKarma2);
    List<CommunityDocument> findAllByNameAndUser(String name, String user);
    List<CommunityDocument> findAllByNameOrUser(String name, String user);
}
