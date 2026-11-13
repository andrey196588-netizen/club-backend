package com.example.closed_club.controller;

import com.example.closed_club.dto.QrCodeCreateRequest;
import com.example.closed_club.dto.QrCodeResponse;
import com.example.closed_club.service.QrCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qr-codes")
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<QrCodeResponse> create(@RequestBody QrCodeCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<QrCodeResponse>> getAll() {
        return ResponseEntity.ok(qrCodeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QrCodeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(qrCodeService.getById(id));
    }

    @PutMapping("/{id}/regenerate")
    public ResponseEntity<QrCodeResponse> regenerate(@PathVariable Long id) {
        return ResponseEntity.ok(qrCodeService.regenerate(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qrCodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
