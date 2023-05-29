package com.ftn.redditcloneprojekat.repository;

import com.ftn.redditcloneprojekat.model.CommunityDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
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
    List<CommunityDocument> findAllByPostCountGreaterThan(int postCount);
    List<CommunityDocument> findAllByPostCountLessThan(int postCount1);
    List<CommunityDocument> findAllByAverageKarmaGreaterThanAndAverageKarmaLessThan(int averageKarma1, int averageKarma2);
    List<CommunityDocument> findAllByAverageKarmaGreaterThan(int averageKarma);
    List<CommunityDocument> findAllByAverageKarmaLessThan(int averageKarma);
    List<CommunityDocument> findAllByNameAndUser(String name, String user);
    List<CommunityDocument> findAllByNameOrUser(String name, String user);

    @Query("{\"fuzzy\": {\"name\": {\"value\": \"?0\", \"fuzziness\": \"2\"}}}")
    List<CommunityDocument> searchCommunitiesByName(String searchName);

    List<CommunityDocument> searchCommunitiesByDescription(String searchDescription);
}
