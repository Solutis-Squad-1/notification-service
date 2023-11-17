package br.com.solutis.squad1.notificationservice.dto;

import br.com.solutis.squad1.notificationservice.enums.StatusEmail;

/**
 * EmailResponseDto
 */
public record EmailResponseDto(
        Long id,
        String owner,
        String emailFrom,
        String emailTo,
        String subject,
        String text,
        StatusEmail status
) {
}
