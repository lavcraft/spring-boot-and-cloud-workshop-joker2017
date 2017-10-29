package ru.spring.ripper.examinator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author tolkv
 * @version 29/10/2017
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckedExam {
  @Delegate
  @JsonIgnore
  Examine examine = new Examine();

  int mark;
}
