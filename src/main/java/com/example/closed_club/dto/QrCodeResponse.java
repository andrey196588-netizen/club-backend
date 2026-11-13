package com.example.closed_club.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeResponse {
    private Long id;
    private UUID code;
    private Long memberId;
    private String memberName;
    private LocalDateTime createdAt;
}
