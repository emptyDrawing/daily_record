package test.sskim.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import test.sskim.junit.domain.Study;
import test.sskim.junit.study.StudyStatus;


// @ExtendWith(FindSlowTestExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest {


	// // field 에 정의한다.
	// @RegisterExtension
	// static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1005L);


	// @DisplayName("조건별 테스트[느림]")
	// @SlowTestTag
	// @Order(1)
	// void conditionalSlowTest() throws InterruptedException {
	// 	// import static org.junit.jupiter.api.Assumptions.*;
	// 	// 그런데 환경변수는 vscode 가 들고가고 있어서 잘 안됨.
	// 	System.out.println(System.getenv("TEST_ENV"));
	// 	Thread.sleep(1005L);
	// 	assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
	// 	// 위조건이 만족해야지 나머지 조건이 도는 구조이다.
		
	// 	assertTimeoutPreemptively(Duration.ofMillis(1000),() -> {
	// 		new Study(0);
	// 	});
	// }

	// @DisplayName("반복학습")
	// @RepeatedTest(value = 10, name ="{displayName} : {currentRepetition} / {totalRepetitions}" )
	// @Order(4)
	// void repeatedTest(RepetitionInfo repetitionInfo) {
	// 	System.out.println("Repeat Count :" + repetitionInfo.getCurrentRepetition() 
	// 	+ " / " + repetitionInfo.getTotalRepetitions());
	// }


	// @DisplayName("반복학습")
	// @ParameterizedTest(name ="{displayName} {0} {index}교시" )
	// @ValueSource(strings = {"수학","과학","사회","국어"})
	// @Order(3)
	// void parameterRepeatedTest(@ConvertWith(StudyConverter.class) Study study) {
	// 	System.out.println("["+study.getName()+"] Repeat");
	// }

	// static class StudyConverter extends SimpleArgumentConverter {
	// 	@Override
    //     public Object convert(Object source, Class<?> targetType) {
	// 		assertEquals(Study.class, targetType, "Can only convert to Study");
	// 		Study object = new Study(0); 
	// 		object.setName(String.valueOf(source));
    //         return object;
    //     }
	// }

	// @DisplayName("반복학습 2")
	// @ParameterizedTest(name ="{displayName} {0} {index}교시" )
	// @CsvSource({"1, 수학","3, 과학","4, 사회","1, 국어"})
	// @Order(1)

	// void parameterRepeatedTest2(Integer limit, String name ) {
	// 	System.out.println(new Study(limit, name));
	// }

	
	// @DisplayName("반복학습 3")
	// @ParameterizedTest(name ="{displayName} {0} {index}교시" )
	// @CsvSource({"10, 수학","13, 과학","43, 사회","12, 국어"})
	// void parameterRepeatedTest3(ArgumentsAccessor accessor) {
	// 	System.out.println(
	// 		new Study( 
	// 			accessor.getInteger(0)
	// 			, accessor.getString(1)
	// 		));
	// }


	// @DisplayName("반복학습 4")
	// @ParameterizedTest(name ="{displayName} {0} {index}교시" )
	// @CsvSource({"100, Java","100, Spring","100, boot","0, JPA "})
	// void parameterRepeatedTest3(@AggregateWith(StudyAggregator.class) Study study) {
	// 	System.out.println(study);
	// }

	// static class StudyAggregator implements ArgumentsAggregator {

	// 	@Override
	// 	public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
	// 			throws ArgumentsAggregationException {
	// 		return	new Study( 
	// 			accessor.getInteger(0), accessor.getString(1)
	// 		);
	// 	}
	// }

	// // 5 버젼부터는 굳이 public 을 안달아도 됩니다.
	// @Test
	// @DisplayName("스터디 만들기")
	// @EnabledOnOs({OS.LINUX,OS.MAC})
	// @EnabledOnJre(JRE.JAVA_11)
	// @Tag("fail")
	// void create_study() {
	// 	Study study = new Study(0);

	// 	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> new Study(-10) ); 
	// 	assertEquals("limit 는 0보다 커야된다.", ex.getMessage());

	// 	// all 로 묶으면 묶인 거를 독립적-병렬적? 으로 실행 가능함.
	// 	// 이렇게 하면 2,3번이 한번에 틀린걸 알 수 있음음
	// 	assertAll(
	// 		() -> { assertNotNull(study);} 
	// 		,() -> { assertTrue( study.getLimit() >= 1 , () -> "스터디 최대인원은 1명 이상이다. "); }
	// 		// 기대값 먼저, 실제 나오는 값, 메세지
	// 		,() -> { assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 Status 는 DRAFT"); }

	// 	);

	// };

	// @Test
	// @Tag("slow")
	// void create_new_sysout() {
	// 	System.out.println("create1");
	// };

	// @Test
	// @Disabled // @Test에 @Disabled 이 있으면 실행되지 않음
	// void crash() {
	// 	System.out.println("crash");
	// }


	// // //All은 private 안되며 void 이외를 리턴하면 안됨, 반드시 static 형이어야함.
	// @BeforeAll
	// void beforeAll() {
	// 	System.out.println("beforeAll");
	// }

	// @AfterAll
	// @Disabled
	// static void afterAll() {
	// 	System.out.println("afterAll");
	// }

	// // Each 는 굳이 static 일필요는 없음음 
	// @BeforeEach
	// @Disabled
	// void beforeEach() {
	// 	System.out.println("beforeEach");
	// }

	// @AfterEach
	// @Disabled
	// void afterEach() {
	// 	System.out.println("afterEach");
	// }




}
