package test.sskim.junit.study;

import org.springframework.data.jpa.repository.JpaRepository;

import test.sskim.junit.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long>{    
}
