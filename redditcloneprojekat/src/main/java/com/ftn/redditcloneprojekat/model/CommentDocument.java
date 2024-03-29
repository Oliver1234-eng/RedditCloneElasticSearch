package com.ftn.redditcloneprojekat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "comments")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class CommentDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Text)
    private String user;

    @Field(type = FieldType.Integer)
    private int postId;
}
