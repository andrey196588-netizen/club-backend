package com.example.closed_club.service;

import com.example.closed_club.dto.ClubEntryResponse;
import com.example.closed_club.repository.QrCodeRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {
    private final QrCodeRepository qrCodeRepository;
    @Transactional(readOnly = true)
    public ClubEntryResponse verifyQrCode (UUID code){
        return qrCodeRepository.findByCode(code).map(qrCode -> new ClubEntryResponse(true, qrCode.getMember().getName(), "Доступ разрешён")).
                orElse(new ClubEntryResponse(false, null, "QR-код не найден или недействителен"));
    }
}
