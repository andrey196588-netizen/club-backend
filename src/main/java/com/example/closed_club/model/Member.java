package com.example.closed_club.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private String name;
}
