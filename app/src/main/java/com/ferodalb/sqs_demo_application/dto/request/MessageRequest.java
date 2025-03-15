package com.ferodalb.sqs_demo_application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ferodalb.sqs_demo_application.dto.Rating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageRequest {

    @NotNull(message = "userId should not be null")
    private UUID userId;

    @NotEmpty(message = "webpage should not be empty")
    private String webPage;

    @NotNull(message = "date should not be null")
    private LocalDate date;

    @NotNull(message = "time should not be null")
    private LocalTime time;

    @NotNull(message = "rating should not be null")
    private Rating rating;

    @NotNull(message = "comment should not be null")
    private String comment;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
