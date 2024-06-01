package com.example.jplferrarijobhunt.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "job_offer")
public class JobOffer {

        @jakarta.persistence.Id @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "description")
        private String description;
        @Column(name = "url")
        private String url;
        @Column(name = "added_date")
        private LocalDate addedDate;
        @Column(name = "applied_date")
        private LocalDate appliedDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private ApplicationStatus status;
        // Standardkonstruktor
        public JobOffer() {}

        // Konstruktor mit allen Feldern
        public JobOffer(String description, String url, LocalDate addedDate, LocalDate appliedDate, ApplicationStatus status) {
                this.description = description;
                this.url = url;
                this.addedDate = addedDate;
                this.appliedDate = appliedDate;
                this.status = status;
        }

        // Getter und Setter
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public LocalDate getAddedDate() {
                return addedDate;
        }

        public void setAddedDate(LocalDate addedDate) {
                this.addedDate = addedDate;
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
}