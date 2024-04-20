package com.jia.oss.entity;

public class FileInfo {

    private String fileName;

    //是否目录
    private Boolean directoryflag;

    private String etag;

    public Boolean getDirectoryflag() {
        return directoryflag;
    }

    public void setDirectoryflag(Boolean directoryflag) {
        this.directoryflag = directoryflag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
