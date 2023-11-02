package br.com.solutis.squad1.notificationservice.model.repository;

import br.com.solutis.squad1.notificationservice.model.entity.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    Page<Email> findAllByDeletedFalse(Pageable pageable);
}
