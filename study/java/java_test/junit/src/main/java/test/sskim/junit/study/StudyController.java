package test.sskim.junit.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.sskim.junit.domain.Study;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class StudyController {
    
    final StudyRepository repository;

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id){
        return repository.findById(id)
            .orElseThrow( ()-> new IllegalArgumentException("Study not found for id[" + id + "]") );
    }
    
    @PostMapping("/study")
    public Study postMethodName(@RequestBody Study study) {
        return repository.save(study);
    }

}
