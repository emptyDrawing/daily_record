package test.sskim.junit.study;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import test.sskim.junit.member.MemberService;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

	@Mock
	MemberService memberService;


	@Test
	void create_study_service(@Mock	StudyRepository repository) {

		StudyService studyService = new StudyService(memberService, repository);
		assertNotNull(studyService);
	}

}
