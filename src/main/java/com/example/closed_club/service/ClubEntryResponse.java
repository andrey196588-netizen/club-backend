package com.example.closed_club.service;

import java.util.UUID;

public record ClubEntryResponse(
        String memberName,
        UUID newQrCode
) {}
