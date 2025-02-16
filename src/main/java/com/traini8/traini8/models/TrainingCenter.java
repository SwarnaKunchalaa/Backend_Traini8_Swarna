package com.traini8.traini8.models;

import com.traini8.traini8.dtos.AddressDto;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class TrainingCenter{

    @Id
    private String centerCode;
    private String centerName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private Integer  studentCapacity;
    @ElementCollection
    @CollectionTable(name="Courses", joinColumns = @JoinColumn(name="centerCode"))
    private List<String> coursesOffered;
    private long createdOn;
    private String contactEmail;
    private String contactPhone;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
}
