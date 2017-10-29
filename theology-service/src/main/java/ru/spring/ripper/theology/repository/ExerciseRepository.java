package ru.spring.ripper.theology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.ripper.examinator.domain.Exercise;

/**
 * @author tolkv
 * @version 29/10/2017
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
