package br.com.solutis.squad1.notificationservice.consumer;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {
    private final EmailService emailService;

    @RabbitListener(queues = {"${spring.rabbitmq.queue.notification}"})
    public void consume(
            @Payload EmailDto emailDto
    ) {
        log.info("Sending email to {}", emailDto.emailTo());
        emailService.send(emailDto);
    }
}
