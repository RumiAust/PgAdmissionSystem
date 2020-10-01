package aust.iums.pg.admission.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by Rumi on 9/16/2020.
 */
public class WorkExperienceList {
    private String organizationName;
    private String designation;
    private String jobResponsibility;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String toDate;
    private MultipartFile experienceFile;
    private Boolean currentlyWorking;
    private int visbility;

    private String experienceFileError;
    private String organizationNameError;
    private String jobResponsibilityError;
    private String designationError;
    private String toDateError;
    private String fromDateError;


    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(Boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
    }

    public String getOrganizationNameError() {
        return organizationNameError;
    }

    public void setOrganizationNameError(String organizationNameError) {
        this.organizationNameError = organizationNameError;
    }

    public String getJobResponsibilityError() {
        return jobResponsibilityError;
    }

    public void setJobResponsibilityError(String jobResponsibilityError) {
        this.jobResponsibilityError = jobResponsibilityError;
    }

    public String getDesignationError() {
        return designationError;
    }

    public void setDesignationError(String designationError) {
        this.designationError = designationError;
    }

    public String getToDateError() {
        return toDateError;
    }

    public void setToDateError(String toDateError) {
        this.toDateError = toDateError;
    }

    public String getFromDateError() {
        return fromDateError;
    }

    public void setFromDateError(String fromDateError) {
        this.fromDateError = fromDateError;
    }

    public int getVisbility() {
        return visbility;
    }

    public void setVisbility(int visbility) {
        this.visbility = visbility;
    }


    public String getExperienceFileError() {
        return experienceFileError;
    }

    public void setExperienceFileError(String experienceFileError) {
        this.experienceFileError = experienceFileError;
    }

    public MultipartFile getExperienceFile() {
        return experienceFile;
    }

    public void setExperienceFile(MultipartFile experienceFile) {
        this.experienceFile = experienceFile;
    }


    public WorkExperienceList(String pOrganizationName, String pDesignation, String pJobResponsibility, String pFromDate, String pToDate) {
        organizationName = pOrganizationName;
        designation = pDesignation;
        jobResponsibility = pJobResponsibility;
        fromDate = pFromDate;
        toDate = pToDate;
    }

    public WorkExperienceList() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String pOrganizationName) {
        organizationName = pOrganizationName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String pDesignation) {
        designation = pDesignation;
    }

    public String getJobResponsibility() {
        return jobResponsibility;
    }

    public void setJobResponsibility(String pJobResponsibility) {
        jobResponsibility = pJobResponsibility;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String pFromDate) {
        fromDate = pFromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String pToDate) {
        toDate = pToDate;
    }

    @Override
    public String toString() {
        return "WorkExperienceList{" +
                "organizationName='" + organizationName + '\'' +
                ", designation='" + designation + '\'' +
                ", jobResponsibility='" + jobResponsibility + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
