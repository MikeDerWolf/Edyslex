package org.edyslex.models;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "schoolClass")
    private Integer schoolClass;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "medicalDiagnosis", nullable = false)
    private String medicalDiagnosis;

    @Column(name = "speechDiagnosis", nullable = false)
    private String speechDiagnosis;

    @Column(name = "dateOfEntry", nullable = false)
    private LocalDate dateOfEntry;

    @Column(name = "dateOfExit")
    private LocalDate dateOfExit;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Report> reports;

    public Student(){}

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Integer schoolClass, String gender,
                   String medicalDiagnosis, String speechDiagnosis, LocalDate dateOfEntry, LocalDate dateOfExit) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.schoolClass = schoolClass;
        this.gender = gender;
        this.medicalDiagnosis = medicalDiagnosis;
        this.speechDiagnosis = speechDiagnosis;
        this.dateOfEntry = dateOfEntry;
        this.dateOfExit = dateOfExit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Integer schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getMedicalDiagnosis() {
        return medicalDiagnosis;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public String getSpeechDiagnosis() {
        return speechDiagnosis;
    }

    public void setSpeechDiagnosis(String speechDiagnosis) {
        this.speechDiagnosis = speechDiagnosis;
    }

    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public LocalDate getDateOfExit() {
        return dateOfExit;
    }

    public void setDateOfExit(LocalDate dateOfExit) {
        this.dateOfExit = dateOfExit;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", schoolClass=" + schoolClass +
                ", gender='" + gender + '\'' +
                ", medicalDiagnosis='" + medicalDiagnosis + '\'' +
                ", speechDiagnosis='" + speechDiagnosis + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                ", dateOfExit=" + dateOfExit +
                '}';
    }
}
