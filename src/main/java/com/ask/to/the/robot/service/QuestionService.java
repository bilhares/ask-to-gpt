package com.ask.to.the.robot.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ask.to.the.robot.domain.OpenAiRequestBody;
import com.ask.to.the.robot.domain.OpenAiResponse;
import com.ask.to.the.robot.domain.Question;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class QuestionService {

	private static final MediaType MEDIA_TYPE = MediaType.get("application/json");

	@Value("${OPEN_API_URL}")
	private String URL;

	@Value("${OPEN_API_KEY}")
	private String API_KEY;

	private static Logger log = LoggerFactory.getLogger(QuestionService.class);

	public OpenAiResponse Ask(Question question) throws IOException {
		log.info("Your question is: " + question.toString());

		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(new OpenAiRequestBody(question.getPrompt()));

		RequestBody body = RequestBody.create(json, MEDIA_TYPE);

		Request request = new Request.Builder().url(URL).addHeader("Authorization", "Bearer " + API_KEY).post(body)
				.build();

		Response response = client.newCall(request).execute();

		if (!response.isSuccessful()) {
			log.error("Error " + response);
			throw new IOException("Unexpected code " + response);
		}

		try (ResponseBody responseBody = response.body()) {

			String responseString = responseBody.string();
			JSONObject Jobject = new JSONObject(responseString);
			OpenAiResponse data = mapper.readValue(responseString, OpenAiResponse.class);

			log.info("Your response is: " + responseString);
			return data;
		}
	}

}
