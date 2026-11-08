package com.example.closed_club.service;

import com.example.closed_club.model.Member;
import com.example.closed_club.model.QrCode;
import com.example.closed_club.repository.MemberRepository;
import com.example.closed_club.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final QrCodeRepository qrCodeRepository;
    private final MemberRepository memberRepository;

    public ClubEntryResponse verifyQrCode (UUID code){
        QrCode oldQrCode = qrCodeRepository.findByCode(code).
                orElseThrow(() -> new RuntimeException("Вход не разрешен: Qr-код не найден"));

        Member member = oldQrCode.getMember();

        qrCodeRepository.delete(oldQrCode);

        QrCode newQrCode = new QrCode();
        newQrCode.setCode(UUID.randomUUID());
        newQrCode.setMember(member);

        QrCode savedQrCode = qrCodeRepository.save(newQrCode);
        return new ClubEntryResponse(member.getName(), savedQrCode.getCode());
    }
}
