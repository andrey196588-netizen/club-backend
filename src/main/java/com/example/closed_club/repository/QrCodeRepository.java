package com.example.closed_club.repository;

import com.example.closed_club.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository <QrCode, Long> {
    Optional<QrCode> findByCode(UUID code);
}
