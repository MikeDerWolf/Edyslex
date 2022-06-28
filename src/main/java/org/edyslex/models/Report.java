package org.edyslex.models;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "report")
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dateOfReport", nullable = false)
    private LocalDate dateOfReport;

    @Column(name = "priorities", nullable = false)
    private String priorities;

    @Column(name = "difficulties", nullable = false)
    private String difficulties;

    @Column(name = "progress", nullable = false)
    private String progress;

    @Column(name = "observations")
    private String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false, foreignKey = @ForeignKey(name="FK_studentId",value = ConstraintMode.CONSTRAINT))
    private Student student;

    public Report(){}

    public Report(LocalDate dateOfReport, String priorities, String difficulties, String progress, String observations)
    {
        this.dateOfReport = dateOfReport;
        this.priorities = priorities;
        this.difficulties = difficulties;
        this.progress = progress;
        this.observations = observations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(LocalDate dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public String getPriorities() {
        return priorities;
    }

    public void setPriorities(String priorities) {
        this.priorities = priorities;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(String difficulties) {
        this.difficulties = difficulties;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
