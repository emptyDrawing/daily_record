package test.sskim.junit.study;

import java.util.Optional;

import test.sskim.junit.domain.Member;
import test.sskim.junit.domain.Study;
import test.sskim.junit.member.MemberService;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow( 
            () -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'") 
        ));

        Study newStudy = repository.save(study);
        memberService.notify(newStudy);

        return newStudy;
    }

    public Study openStudy(Study study) {
        
        study.open();
        Study openedStudy = repository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }
     


}
