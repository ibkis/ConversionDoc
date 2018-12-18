package com.conversion.modeles;


import com.conversion.payload.UploadFileResponse;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ConversionFile")
public class ConversionFile {

    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "filteTypeOut")
    private String filteTypeOut;
    @Column(name = "userId")
    private int userId;

    public ConversionFile(UploadFileResponse uploadFileResponse) {
        this.fileName = uploadFileResponse.getFileName();
        this.filteTypeOut = uploadFileResponse.getFilteTypeOut();
        this.id=uploadFileResponse.getId();
        this.userId=uploadFileResponse.getIduser();
    }

    public ConversionFile() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilteTypeOut() {
        return filteTypeOut;
    }

    public void setFilteTypeOut(String filteTypeOut) {
        this.filteTypeOut = filteTypeOut;
    }
}
