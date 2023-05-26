package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.dto.PostDocumentDTO;
import com.ftn.redditcloneprojekat.dto.PostDocumentResponseDTO;
import com.ftn.redditcloneprojekat.dto.mapper.PostDocumentMapper;
import com.ftn.redditcloneprojekat.lucene.indexing.handlers.DocumentHandler;
import com.ftn.redditcloneprojekat.lucene.indexing.handlers.PDFHandler;
import com.ftn.redditcloneprojekat.model.PostDocument;
import com.ftn.redditcloneprojekat.repository.PostDocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostDocumentService {

    @Value("${files.path}")
    private String filesPath;

    private final PostDocumentRepository postDocumentRepository;

    public PostDocumentService(PostDocumentRepository postDocumentRepository) {
        this.postDocumentRepository = postDocumentRepository;
    }

    public void index(PostDocumentDTO postDocumentDTO){
        postDocumentRepository.save(PostDocumentMapper.mapModel(postDocumentDTO));
    }

    public void index(PostDocument postDocument) {
        postDocumentRepository.save(postDocument);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByTitle(String title){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByTitle(title);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByText(String text){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByText(text);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByUser(String user){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByUser(user);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByFlair(String flair){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByFlair(flair);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByCommunity(String community){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByCommunity(community);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByCommentCount(int commentCount1, int commentCount2){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByCommentCountGreaterThanAndCommentCountLessThan(commentCount1, commentCount2);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByKarma(int karma1, int karma2){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByKarmaGreaterThanAndKarmaLessThan(karma1, karma2);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByFlairAndUser(String flair, String user){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByFlairAndUser(flair, user);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    public List<PostDocumentResponseDTO> findPostDocumentsByFlairOrUser(String flair, String user){
        List<PostDocument> postDocuments = postDocumentRepository.findAllByFlairOrUser(flair, user);
        return mapPostDocumentsToPostDocumentsDTO(postDocuments);
    }

    private List<PostDocumentResponseDTO> mapPostDocumentsToPostDocumentsDTO(List<PostDocument> postDocuments){
        List<PostDocumentResponseDTO> postDocumentsDTO = new ArrayList<>();
        for(PostDocument postDocument : postDocuments){
            postDocumentsDTO.add(PostDocumentMapper.mapResponseDto(postDocument));
        }
        return postDocumentsDTO;
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
                    index(handler.getIndexUnitPostDocument(newFile));
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

    public void indexUploadedFile(PostDocumentDTO postDocumentRequestDto) throws IOException {
        for (MultipartFile file : postDocumentRequestDto.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                PostDocument postDocumentIndexUnit = getHandler(fileName).getIndexUnitPostDocument(new File(fileName));
                postDocumentIndexUnit.setUser(postDocumentRequestDto.getUser());
                postDocumentIndexUnit.setFlair(postDocumentRequestDto.getFlair());
                postDocumentIndexUnit.setCommunity(postDocumentRequestDto.getCommunity());
                postDocumentIndexUnit.setCommentCount(postDocumentRequestDto.getCommentCount());
                postDocumentIndexUnit.setKarma(postDocumentRequestDto.getKarma());
                index(postDocumentIndexUnit);
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
