package ru.spring.ripper.examinator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tolkv
 * @version 02/11/2017
 */
@RestController
@RefreshScope
public class FooBarController {
  @Value("${foo.bar}")
  String test;

  @GetMapping("/foo")
  public String returnFoo() {
    return test;
  }
}
