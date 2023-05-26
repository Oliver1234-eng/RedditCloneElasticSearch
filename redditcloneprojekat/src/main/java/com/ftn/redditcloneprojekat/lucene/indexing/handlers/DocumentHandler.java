package com.ftn.redditcloneprojekat.lucene.indexing.handlers;

import com.ftn.redditcloneprojekat.model.CommunityDocument;
import com.ftn.redditcloneprojekat.model.PostDocument;

import java.io.File;

public abstract class DocumentHandler {
    /**
     * Od prosledjene datoteke se konstruise Lucene Document
     *
     * @param file
     *            datoteka u kojoj se nalaze informacije
     * @return Lucene Document
     */
    public abstract CommunityDocument getIndexUnitCommunityDocument(File file);
    public abstract PostDocument getIndexUnitPostDocument(File file);
    public abstract String getText(File file);

}
