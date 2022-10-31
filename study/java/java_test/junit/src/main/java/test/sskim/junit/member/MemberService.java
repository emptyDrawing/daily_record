package test.sskim.junit.member;

import java.util.Optional;

import test.sskim.junit.domain.Member;

public interface MemberService {

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
    
}
