package com.ftn.redditcloneprojekat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "communities")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class CommunityDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String keywords;

    @Field(type = FieldType.Text)
    private String rules;

    @Field(type = FieldType.Text)
    private String suspendedReason;

    @Field(type = FieldType.Text)
    private String user;

    @Field(type = FieldType.Integer)
    private int postCount;

    @Field(type = FieldType.Integer)
    private int averageKarma;

    private String filename;
}
