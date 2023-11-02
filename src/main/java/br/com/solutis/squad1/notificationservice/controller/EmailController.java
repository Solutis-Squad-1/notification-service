package br.com.solutis.squad1.notificationservice.controller;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.dto.EmailResponseDto;
import br.com.solutis.squad1.notificationservice.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping()
    public Page<EmailResponseDto> findAll(Pageable pageable) {
        return emailService.findAll(pageable);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmailResponseDto sendingEmail(
            @RequestBody @Valid EmailDto emailDto
    ) {
        return emailService.send(emailDto);
    }
}
