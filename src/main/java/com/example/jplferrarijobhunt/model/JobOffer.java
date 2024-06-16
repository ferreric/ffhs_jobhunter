package com.example.jplferrarijobhunt.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_offer")
public class JobOffer {

        @jakarta.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "description")
        private String description;

        @Column(name = "url")
        private String url;

        @Column(name = "added_date", updatable = false, insertable = false)
        private LocalDate addedDate;

        @Column(name = "applied_date")
        private LocalDate appliedDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private ApplicationStatus status;

        // Standardkonstruktor
        public JobOffer() {}

        // Konstruktor mit allen editierbaren Feldern
        public JobOffer(String description, String url, LocalDate appliedDate, ApplicationStatus status) {
                this.description = description;
                this.url = url;
                this.appliedDate = appliedDate;
                this.status = status;
        }

        // Getter und Setter
        public Integer getId() {
                return id;
        }

        public String getDescription() {
                return description;
        }

        // Setter für description
        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getAddedDate() {
                return addedDate;
        }

        public LocalDate getAppliedDate() {
                return appliedDate;
        }

        public void setAppliedDate(LocalDate appliedDate) {
                this.appliedDate = appliedDate;
        }

        public ApplicationStatus getStatus() {
                return status;
        }

        public void setStatus(ApplicationStatus status) {
                this.status = status;
        }

        public String getUrl() {return url;}

        // Setter für url
        public void setUrl(String url) {
                this.url = url;
        }

        public String toString() {
                return "JobOffer{" +
                        "id=" + id +
                        ", description='" + description + '\'' +
                        ", url='" + url + '\'' +
                        ", addedDate=" + addedDate +
                        ", appliedDate=" + appliedDate +
                        ", status=" + status +
                        '}';
        }
}