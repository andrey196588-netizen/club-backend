package com.example.closed_club.controller;

import com.example.closed_club.service.ClubEntryResponse;
import com.example.closed_club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor

public class ClubController  {
    private  final ClubService clubService;

    @PostMapping("/verify")
    public ClubEntryResponse verify(@RequestBody UUID code){
        return clubService.verifyQrCode(code);
    }
}
