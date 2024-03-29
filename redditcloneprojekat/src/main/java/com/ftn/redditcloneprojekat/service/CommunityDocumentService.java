package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.dto.CommunityDocumentResponseDTO;
import com.ftn.redditcloneprojekat.dto.mapper.CommunityDocumentMapper;
import com.ftn.redditcloneprojekat.dto.CommunityDocumentDTO;
import com.ftn.redditcloneprojekat.lucene.indexing.handlers.DocumentHandler;
import com.ftn.redditcloneprojekat.lucene.indexing.handlers.PDFHandler;
import com.ftn.redditcloneprojekat.model.CommunityDocument;
import com.ftn.redditcloneprojekat.repository.CommunityDocumentRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;

@Service
public class CommunityDocumentService {

    @Value("${files.path}")
    private String filesPath;

    private final CommunityDocumentRepository communityDocumentRepository;

    private ElasticsearchOperations elasticsearchOperations;

    public CommunityDocumentService(CommunityDocumentRepository communityDocumentRepository, ElasticsearchOperations elasticsearchOperations) {
        this.communityDocumentRepository = communityDocumentRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void index(CommunityDocumentDTO communityDocumentDTO){
        communityDocumentRepository.save(CommunityDocumentMapper.mapModel(communityDocumentDTO));
    }

    public void index(CommunityDocument communityDocument) {
        communityDocumentRepository.save(communityDocument);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByName(String name){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByName(name);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByDescription(String description){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByDescription(description);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByRules(String rules){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByRules(rules);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsBySuspendedReason(String suspendedReason){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllBySuspendedReason(suspendedReason);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByUser(String user){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByUser(user);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCount(int postCount1, int postCount2){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByPostCountGreaterThanAndPostCountLessThan(postCount1, postCount2);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCountGreaterThan(int postCount){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByPostCountGreaterThan(postCount);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCountLessThan(int postCount){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByPostCountLessThan(postCount);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarma(int averageKarma1, int averageKarma2){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByAverageKarmaGreaterThanAndAverageKarmaLessThan(averageKarma1, averageKarma2);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarmaGreaterThan(int averageKarma){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByAverageKarmaGreaterThan(averageKarma);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarmaLessThan(int averageKarma){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByAverageKarmaLessThan(averageKarma);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByNameAndUser(String name, String user){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByNameAndUser(name, user);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByNameOrUser(String name, String user){
        List<CommunityDocument> communityDocuments = communityDocumentRepository.findAllByNameOrUser(name, user);
        return mapCommunityDocumentsToCommunityDocumentsDTO(communityDocuments);
    }

    public List<CommunityDocumentResponseDTO> searchCommunitiesByName(String searchName) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(fuzzyQuery("name", searchName).fuzziness(Fuzziness.TWO))
                .build();

        SearchHits<CommunityDocument> searchHits = elasticsearchOperations.search(searchQuery, CommunityDocument.class);
        List<CommunityDocument> communitiesDocument = searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return communitiesDocument.stream()
                .map(CommunityDocumentMapper::mapResponseDto)
                .collect(Collectors.toList());
    }

    public List<CommunityDocument> searchCommunitiesByDescriptionPhraseImpl(String searchDescription) {
        Query query = new StringQuery(QueryBuilders.matchPhraseQuery("description", searchDescription).toString());
        return elasticsearchOperations.search(query, CommunityDocument.class).stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<CommunityDocument> searchCommunitiesByDescriptionPhraseService(String searchDescription) {
        return searchCommunitiesByDescriptionPhraseImpl(searchDescription);
    }

    private List<CommunityDocumentResponseDTO> mapCommunityDocumentsToCommunityDocumentsDTO(List<CommunityDocument> communityDocuments){
        List<CommunityDocumentResponseDTO> communityDocumentsDTO = new ArrayList<>();
        for(CommunityDocument communityDocument : communityDocuments){
            communityDocumentsDTO.add(CommunityDocumentMapper.mapResponseDto(communityDocument));
        }
        return communityDocumentsDTO;
    }

    public void reindex() {
        File dataDir = new File(filesPath);
        indexUnitFromFile(dataDir);
    }

    public int indexUnitFromFile(File file) {
        DocumentHandler handler;
        String fileName;
        int retVal = 0;
        try {
            File[] files;
            if(file.isDirectory()){
                files = file.listFiles();
            }else{
                files = new File[1];
                files[0] = file;
            }
            assert files != null;
            for(File newFile : files){
                if(newFile.isFile()){
                    fileName = newFile.getName();
                    handler = getHandler(fileName);
                    if(handler == null){
                        System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
                        continue;
                    }
                    index(handler.getIndexUnitCommunityDocument(newFile));
                    retVal++;
                } else if (newFile.isDirectory()){
                    retVal += indexUnitFromFile(newFile);
                }
            }
            System.out.println("indexing done");
        } catch (Exception e) {
            System.out.println("indexing NOT done");
        }
        return retVal;
    }

    public void indexUploadedFile(CommunityDocumentDTO communityDocumentRequestDto) throws IOException {
        for (MultipartFile file : communityDocumentRequestDto.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                CommunityDocument communityDocumentIndexUnit = getHandler(fileName).getIndexUnitCommunityDocument(new File(fileName));
                communityDocumentIndexUnit.setRules(communityDocumentRequestDto.getRules());
                communityDocumentIndexUnit.setSuspendedReason(communityDocumentRequestDto.getSuspendedReason());
                communityDocumentIndexUnit.setUser(communityDocumentRequestDto.getUser());
                communityDocumentIndexUnit.setPostCount(communityDocumentRequestDto.getPostCount());
                communityDocumentIndexUnit.setAverageKarma(communityDocumentRequestDto.getAverageKarma());
                index(communityDocumentIndexUnit);
            }
        }
    }

    public DocumentHandler getHandler(String fileName){
        return getDocumentHandler(fileName);
    }

    public static DocumentHandler getDocumentHandler(String fileName) {
        if (fileName.endsWith(".pdf")){
            return new PDFHandler();
        } else {
            return null;
        }
    }

    private String saveUploadedFileInFolder(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(new File(filesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
            System.out.println(path);
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }
}
