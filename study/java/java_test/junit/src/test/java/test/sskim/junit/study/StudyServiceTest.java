package test.sskim.junit.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;
import test.sskim.junit.domain.Member;
import test.sskim.junit.domain.Study;
import test.sskim.junit.domain.StudyStatus;
import test.sskim.junit.member.MemberService;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = StudyServiceTest.ContainerPropertyInitializer.class)
public class StudyServiceTest {

	@Mock MemberService memberService;

	@Autowired StudyRepository repository;

	@Autowired Environment environment;

	@Value("${container.port}") int port;

	// @Container static PostgreSQLContainer container = new PostgreSQLContainer().withDatabaseName("studytest");
	@Container
	static DockerComposeContainer composeContainer = 
	new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yml"))
		.withExposedService("study-test-db", 5432
		, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)));


	@BeforeAll 
	static void beforeAll(){
		Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
		composeContainer.withLogConsumer("study-test-db", logConsumer);
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("===============================================");
		System.out.println(environment.getProperty("container.port"));
		System.out.println(environment.getProperty("container.port").equals(String.valueOf(port)));
		repository.deleteAll();
	}

	static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext context) {
			// 여러개는 , 로 붙여서
			TestPropertyValues.of(
				"container.port="+composeContainer.getServicePort("study-test-db", 5432))
				.applyTo(context.getEnvironment());
		}
	}


	@Test
	void create_study_service() {

		StudyService studyService = new StudyService(memberService, repository);
		assertNotNull(studyService);

		Member member = new Member(1L, "sskim@test.com");
		//import static org.mockito.ArgumentMatchers.any;
		Mockito.when(memberService.findById(any())).thenReturn(Optional.of(member));

		assertEquals("sskim@test.com", memberService.findById(1L).get().getEmail());
		assertEquals(1L, memberService.findById(2L).get().getId());

		// void method - exception stubbing
		doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
		assertThrows(IllegalArgumentException.class, () -> {
			memberService.validate(1L);
		});

		// 1L 이 아닌 void 메소드 호출은 잘 돌아감.
		memberService.validate(2L);

		Study study = new Study(1, "Mockito");
		studyService.createNewStudy(1L, study);

	}

	@DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
	@Test
	void openStudy() {
		// Given
		StudyService studyService = new StudyService(memberService, repository);
		Study study = new Study(10, "더 자바, 테스트");
		
		Member member = new Member(1L, "sskim@test.com");
		// 목일때만 유요한 설정
		// BDDMockito.given(repository.save(study)).willReturn(study);
		
		// When
		studyService.openStudy(study);
		// Then
		assertEquals(StudyStatus.OPEN, study.getStatus());
		assertNotNull(study.getOpenedDateTime());
		BDDMockito.then(memberService).should().notify(study);
	}
		
	@Disabled("수업끝")
	@Test
	void stubbing_test() {

		// Given
		Study study = new Study(10, "Mockito Test");
		StudyService studyService = new StudyService(memberService, repository);
		Member member = new Member(1L, "sskim@test.com");


		// when(memberService.findById(1L)).thenReturn(Optional.of(member));
		// when(repository.save(study)).thenReturn(study);
		BDDMockito.given(memberService.findById(1L)).willReturn(Optional.of(member));
		BDDMockito.given(repository.save(study)).willReturn(study);

		// When
		studyService.createNewStudy(1L, study);

		// Then
		assertNotNull(study.getOwnerId());
		assertEquals(member.getId(), study.getOwnerId());

		// verify(memberService, times(1)).notify(study);
		// verifyNoMoreInteractions(memberService);
		BDDMockito.then(memberService).should(times(1)).notify(study);
		BDDMockito.then(memberService).shouldHaveNoMoreInteractions();

		// 만약 순서도 중요하다면
		// InOrder inOrder = inOrder(memberService);
		// inOrder.verify(memberService).notify(study);
		// verifyNoMoreInteractions(memberService);
		//inOrder.verify(memberService).notify(member);
	}

}
