package br.com.solutis.squad1.notificationservice.controller;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.dto.EmailResponseDto;
import br.com.solutis.squad1.notificationservice.enuns.StatusEmail;
import br.com.solutis.squad1.notificationservice.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping
    @PreAuthorize("hasAuthority('email:read')")
    public Page<EmailResponseDto> findAll(
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) String emailTo,
            @RequestParam(required = false) String emailFrom,
            @RequestParam(required = false) StatusEmail status,
            Pageable pageable
    ) {
        return emailService.findAll(owner, emailTo, emailFrom, status, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('email:send')")
    @ResponseStatus(HttpStatus.CREATED)
    public EmailResponseDto sendingEmail(
            @RequestBody @Valid EmailDto emailDto
    ) {
        return emailService.send(emailDto);
    }
}
