package br.com.solutis.squad1.notificationservice.service;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.dto.EmailResponseDto;
import br.com.solutis.squad1.notificationservice.enums.StatusEmail;
import br.com.solutis.squad1.notificationservice.mapper.EmailMapper;
import br.com.solutis.squad1.notificationservice.model.entity.Email;
import br.com.solutis.squad1.notificationservice.model.repository.EmailRepository;
import br.com.solutis.squad1.notificationservice.model.repository.EmailRepositoryCustom;
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
    private final EmailRepositoryCustom emailRepositoryCustom;
    private final EmailMapper emailMapper;
    private final JavaMailSender emailSender;

    /**
     * Find all emails that are not deleted
     *
     * @param pageable
     * @return Page<EmailResponseDto>
     */
    public Page<EmailResponseDto> findAll(
            String owner,
            String emailTo,
            String emailFrom,
            StatusEmail status,
            Pageable pageable
    ) {
        return emailRepositoryCustom
                .findAllWithFilterAndDeletedFalse(owner, emailTo, emailFrom, status, pageable)
                .map(emailMapper::toResponseDto);
    }

    /**
     * Send email
     *
     * @param emailDto
     * @return EmailResponseDto
     */
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

    /**
     * Update email by id and that is not deleted
     *
     * @param id
     */
    private SimpleMailMessage getEmailMessage(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        return message;
    }
}
