package com.ask.to.the.robot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choices {
	private String text;
	private int index;
	private Object logprobs;
	@JsonProperty("finish_reason")
	private String finishReason;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Object getLogprobs() {
		return logprobs;
	}

	public void setLogprobs(Object logprobs) {
		this.logprobs = logprobs;
	}

	public String getFinishReason() {
		return finishReason;
	}

	public void setFinishReason(String finishReason) {
		this.finishReason = finishReason;
	}

}
