package com.ask.to.the.robot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiRequestBody {

	private String model;
	private String prompt;
	private float temperature;
	@JsonProperty(value = "max_tokens")
	private int maxTokens;

	public OpenAiRequestBody(String prompt) {
		this.prompt = prompt;
		this.model = "text-davinci-003";
		this.maxTokens = 1024; // 100 tokens are 75 words
		this.temperature = 0;// et between 0 and 1, with higher values indicating more random and creative
								// output and lower values indicating more predictable and conservative output.
	}

	public OpenAiRequestBody() {

	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getMaxTokens() {
		return maxTokens;
	}

	public void setMaxTokens(int maxTokens) {
		this.maxTokens = maxTokens;
	}

}
