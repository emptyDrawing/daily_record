package test.sskim.junit.member;

import java.util.Optional;

import test.sskim.junit.domain.Member;
import test.sskim.junit.domain.Study;

public interface MemberService {

    Optional<Member> findById(Long memberId);
        
    void validate(Long memberId);

    void notify(Study newStudy);
}
