package com.example.jplferrarijobhunt.model;

import java.time.LocalDate;

public record JobOffer(
        Integer id,
        String description,
        String url,
        LocalDate addedDate,
        LocalDate appliedDate,
        ApplicationStatus status
) {
}
