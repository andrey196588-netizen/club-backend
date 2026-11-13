package com.example.closed_club.controller;
import com.example.closed_club.dto.ClubEntryResponse;
import com.example.closed_club.dto.ClubVerifyRequest;
import com.example.closed_club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/verify")
    public ResponseEntity<ClubEntryResponse> verify(@RequestBody ClubVerifyRequest request) {
        ClubEntryResponse response = clubService.verifyQrCode(request.getCode());
        return ResponseEntity.ok(response);
    }
}
