package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Minhaz on 10/03/2020.
 */
@Entity
@Table(name = "FILE_STORAGE")
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "APPLICANTION_SN")
    private String applicationSn;

    @NotNull
    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "REF_ID")
    private Long refId;

    @NotNull
    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "CREATED_ON")
    @CreatedDate
    private Instant createdOn;

    @Column(name = "UPDATED_ON")
    @LastModifiedDate
    private Instant updatedOn;

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                ", applicationSn='" + applicationSn + '\'' +
                ", fileType='" + fileType + '\'' +
                ", refId=" + refId +
                ", filePath='" + filePath + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationSn() {
        return applicationSn;
    }

    public void setApplicationSn(String applicationSn) {
        this.applicationSn = applicationSn;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }
}
