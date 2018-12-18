package com.conversion.payload;


import com.conversion.modeles.StaticVariables;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "UploadFileResponse")
public class UploadFileResponse {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private  int id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "fileDownloadUri")
    private String fileDownloadUri;
    @Column(name = "fileType")
    private String fileType;
    @Column(name = "filteTypeOut")
    private String filteTypeOut;
    @Column(name = "datecreate")
    private Date datecreate;
    @Column(name = "size")
    private long size;
    @Column(name = "iduser")
    private  int iduser;
    @Column(name = "status")
    private  String status;
    @Column(name = "attente")
    private long attente;
    @Column(name = "Encours")
    private long Encours;
    @Column(name = "Terminer")
    private long Terminer;
    @Column(name = "classout")
    private String classout;
    @Column(name = "classint")
    private String classint;
    @Column(name = "t_attente")
    private String t_attente;
    @Column(name = "t_encour")
    private String t_encour;
    @Column(name = "t_terminier")
    private String t_terminier;
    @Column(name = "isdelete")
    private  boolean isdelete;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, String filteTypeOut,
                            long size, int iduser,Date datcreated) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.filteTypeOut = filteTypeOut;
        this.datecreate=datcreated;
        attente=datcreated.getTime();
        this.size = size;
        this.iduser = iduser;
        isdelete=false;
        Terminer=0;
        this.status= StaticVariables.ENCOURS;
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        Terminer=0;
    }

    public UploadFileResponse() {
    }

    public String getClassout() {
        return classout;
    }

    public void setClassout(String classout) {
        this.classout = classout;
    }

    public String getClassint() {
        return classint;
    }

    public void setClassint(String classint) {
        this.classint = classint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAttente() {
        return attente;
    }

    public void setAttente(long attente) {
        this.attente = attente;
    }

    public long getEncours() {
        return Encours;
    }

    public void setEncours(long encours) {
        Encours = encours;
    }

    public long getTerminer() {
        return Terminer;
    }

    public void setTerminer(long terminer) {
        Terminer = terminer;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFilteTypeOut() {
        return filteTypeOut;
    }

    public void setFilteTypeOut(String filteTypeOut) {
        this.filteTypeOut = filteTypeOut;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getT_attente() {
        t_attente=Encours==0?attente==0?"":""+ ((System.currentTimeMillis()-attente)/1000)+" s":
                ""+((Encours-attente)/1000)+" s";
        return t_attente;
    }
    public String getT_encour() {

        t_encour=Terminer==0?Encours==0?"":""+ ((System.currentTimeMillis()-Encours)/1000)+" s":""+((Terminer-Encours)/1000)+" s";
        return t_encour;
    }

    public String getT_terminier() {

        return t_terminier;
    }


    public void setT_terminier(String t_terminier) {
        this.t_terminier = t_terminier;
    }
}
