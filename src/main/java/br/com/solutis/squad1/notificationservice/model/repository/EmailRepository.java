package br.com.solutis.squad1.notificationservice.model.repository;

import br.com.solutis.squad1.notificationservice.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
