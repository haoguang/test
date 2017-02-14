package com.example.lawre.myapplication.entity;

import com.example.lawre.myapplication.others.IdGenerator;

/**
 * Created by haoguang on 12/27/2016.
 */

public class Document {
    public static final String representor = "D";
    private static int documentNo = 0;
    private String DocumentID;
    private String DocumentURL;

    public Document(String documentID, String documentURL) {
        DocumentID = documentID;
        DocumentURL = documentURL;
    }

    public String getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(String documentID) {
        DocumentID = documentID;
    }

    public String getDocumentURL() {
        return DocumentURL;
    }

    public void setDocumentURL(String documentURL) {
        DocumentURL = documentURL;
    }

    @Override
    public String toString() {
        return "Document{" +
                "DocumentID='" + DocumentID + '\'' +
                ", DocumentURL='" + DocumentURL + '\'' +
                '}';
    }

    public static String getNewID(){
        documentNo++;
        return representor + IdGenerator.generateID(documentNo);
    }
}
