package com.demo.fileupload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@SpringBootTest
@AutoConfigureMockMvc
class FileUploadApplicationTests {

	private static final String DEFAULT_NAME = "sumit";
	private static final String DEFAULT_FILE_NAME = "MyFile.txt";

	@Autowired
	private MockMvc mockMvc;

	private MultiValueMap<String, String> getDefaultParams() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("name", DEFAULT_NAME);
		params.set("fileName", DEFAULT_FILE_NAME);
		return  params;
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Add Student with All Value")
	void addStudent() throws Exception {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "text.txt", "text/plain", "mytext".getBytes());
		MvcResult mvcResult = this.mockMvc.perform(
			MockMvcRequestBuilders
				.multipart("/api/students")
				.file(mockMultipartFile)
				.params(getDefaultParams())
				.contentType(MediaType.APPLICATION_JSON)
			).andReturn();
		System.out.println("mvcResult.getResponse().getStatus() = " + mvcResult.getResponse().getStatus());
	}
}
