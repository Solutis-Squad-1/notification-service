package br.com.solutis.squad1.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailDto(
        @NotBlank
        String owner,
        @NotBlank
        @jakarta.validation.constraints.Email
        String emailFrom,
        @NotBlank
        @jakarta.validation.constraints.Email
        String emailTo,
        @NotBlank
        String subject,
        @NotBlank
        String text
) {
}
