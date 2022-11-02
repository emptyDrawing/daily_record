package test.sskim.junit.member;

import java.util.Optional;

import test.sskim.junit.domain.Member;
import test.sskim.junit.domain.Study;

public class DefaultMemberService implements MemberService {

    // StudyService studyService;

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.empty();
    }

    @Override
    public void validate(Long memberId) {
        // studyService.hi();
    }

    @Override
    public void notify(Study newStudy) {
        
    }
}
