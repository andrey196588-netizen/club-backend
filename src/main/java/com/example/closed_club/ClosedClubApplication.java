package com.example.closed_club;

import com.example.closed_club.model.Member;
import com.example.closed_club.model.QrCode;
import com.example.closed_club.repository.MemberRepository;
import com.example.closed_club.repository.QrCodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.UUID;

@SpringBootApplication
public class ClosedClubApplication {

    public static void main(String[] args) {
        System.setProperty("spring.datasource.url", "jdbc:h2:mem:closed_club_db;DB_CLOSE_DELAY=-1");
        System.setProperty("spring.datasource.username", "sa");
        System.setProperty("spring.datasource.password", "");
        System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
        System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");

        SpringApplication.run(ClosedClubApplication.class, args);
    }
    //Для теста, потом будет удалено
    @Bean
    public CommandLineRunner initDatabase(MemberRepository memberRepository, QrCodeRepository qrCodeRepository) {
        return args -> {
            Member member = new Member();
            member.setName("Andrey Luchko");
            Member savedMember = memberRepository.save(member);

            QrCode qrCode = new QrCode();
            qrCode.setCode(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
            qrCode.setMember(savedMember);
            qrCodeRepository.save(qrCode);

            System.out.println(">>> Тестовые данные для Andrey Luchko успешно загружены в базу H2! <<<");
        };
    }
}
