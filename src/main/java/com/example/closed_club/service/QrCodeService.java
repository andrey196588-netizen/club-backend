package com.example.closed_club.service;

import com.example.closed_club.dto.QrCodeCreateRequest;
import com.example.closed_club.dto.QrCodeResponse;
import com.example.closed_club.model.Member;
import com.example.closed_club.model.QrCode;
import com.example.closed_club.repository.MemberRepository;
import com.example.closed_club.repository.QrCodeRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public QrCodeResponse create(QrCodeCreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Участник с ID " + request.getMemberId() + " не найден"));

        QrCode qrCode = new QrCode();
        qrCode.setCode(UUID.randomUUID());
        qrCode.setMember(member);

        QrCode saved = qrCodeRepository.save(qrCode);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<QrCodeResponse> getAll() {
        return qrCodeRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public QrCodeResponse getById(Long id) {
        QrCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR-код с ID " + id + " не найден"));
        return mapToResponse(qrCode);
    }

    @Transactional
    public QrCodeResponse regenerate(Long id) {
        QrCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR-код с ID " + id + " не найден"));

        qrCode.setCode(UUID.randomUUID());
        QrCode updated = qrCodeRepository.save(qrCode);
        return mapToResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!qrCodeRepository.existsById(id)) {
            throw new RuntimeException("QR-код с ID " + id + " не найден");
        }
        qrCodeRepository.deleteById(id);
    }

    private QrCodeResponse mapToResponse(QrCode qrCode) {
        return new QrCodeResponse(
                qrCode.getId(),
                qrCode.getCode(),
                qrCode.getMember().getId(),
                qrCode.getMember().getName(),
                qrCode.getCreatedAt()
        );
    }
}
