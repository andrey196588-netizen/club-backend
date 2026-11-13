package com.example.closed_club.service;

import com.example.closed_club.dto.MemberRequest;
import com.example.closed_club.model.Member;
import com.example.closed_club.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member create(MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member getById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Участник с ID " + id + " не найден"));
    }

    @Transactional
    public Member update(Long id, MemberRequest request) {
        Member member = getById(id);
        member.setName(request.getName());
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Участник с ID " + id + " не найден");
        }
        memberRepository.deleteById(id);
    }
}
