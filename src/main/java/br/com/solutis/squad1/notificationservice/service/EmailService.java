package br.com.solutis.squad1.notificationservice.service;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.dto.EmailResponseDto;
import br.com.solutis.squad1.notificationservice.enuns.StatusEmail;
import br.com.solutis.squad1.notificationservice.mapper.EmailMapper;
import br.com.solutis.squad1.notificationservice.model.entity.Email;
import br.com.solutis.squad1.notificationservice.model.repository.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    private final JavaMailSender emailSender;

    public Page<EmailResponseDto> findAll(Pageable pageable) {
        return emailRepository.findAllByDeletedFalse(pageable).map(emailMapper::toResponseDto);
    }

    public EmailResponseDto send(EmailDto emailDto) {
        log.info("Sending email to {}", emailDto.emailTo());

        Email email = emailMapper.dtoToEntity(emailDto);

        try {
            SimpleMailMessage message = getEmailMessage(email);
            emailSender.send(message);

            email.setStatus(StatusEmail.SENT);
        } catch (Exception e) {
            email.setStatus(StatusEmail.ERROR);
        }

        return emailMapper.toResponseDto(emailRepository.save(email));
    }

    private SimpleMailMessage getEmailMessage(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        return message;
    }
}
