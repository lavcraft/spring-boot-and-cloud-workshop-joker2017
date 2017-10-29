package ru.spring.ripper.examinator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author tolkv
 * @version 29/10/2017
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  Long id;
  String question;
  String answer;
}
