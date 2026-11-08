package com.example.closed_club.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Qr_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private UUID code;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
