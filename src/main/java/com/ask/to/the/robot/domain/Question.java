package com.ask.to.the.robot.domain;

public class Question {

	private String prompt;

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	@Override
	public String toString() {
		return "Question [prompt=" + prompt + "]";
	}

}
