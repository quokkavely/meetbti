package com.springboot.member.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.controller.MemberController;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository repository;
    private final MemberController controller;

    public MemberService(MemberRepository repository, MemberController controller) {
        this.repository = repository;
        this.controller = controller;
    }

    public Member createMember(Member member) {

        return member;
    }

    public Member updateMember(Member member) {
        return member;
    }

    public Member findMember(Long memberId) {
       return findVerifiedMember(memberId);
    }

    public void deleteMember(Long memberId) {
        repository.delete(findVerifiedMember(memberId));
    }

    private Member findVerifiedMember(Long memberId) {
        Optional<Member> optionalMember = repository.findById(memberId);
        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    // 이메일 검증
    private void verifiedExistEmail(String email) {
        Optional<Member> optionalMember = repository.findByEmail(email);
        if(optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_ALREADY_EXIST);
        }

    }

    // 닉네임 검증
    private void verifiedExistNickname(String nickname) {
        Optional<Member> optionalMember = repository.findByNickname(nickname);
        if(optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NICKNAME_ALREADY_EXIST);
        }

    }


}
