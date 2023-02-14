package com.ask.to.the.robot.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ask.to.the.robot.domain.OpenAiResponse;
import com.ask.to.the.robot.domain.Question;
import com.ask.to.the.robot.service.QuestionService;

@Controller
public class HomeController {

	@Autowired
	QuestionService service;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@PostMapping("/ask")
	public ResponseEntity<?> askToTheRobot(Question question) throws IOException {
		try {
			OpenAiResponse response = service.Ask(question);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(429).build();
		}
	}
}
