package com.demo.fileupload.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentResourceTest {

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

    @Test
    @DisplayName("Add Studet with Multpart data: type 2")
    public void addStudent1() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "text.txt", "text/plain", "mytext".getBytes());
        MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/api/students");
        requestBuilder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("POST");
                request.setParameter("name", DEFAULT_NAME);
                request.setParameter("fileName", mockMultipartFile.getOriginalFilename());
                return request;
            }
        });
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder.file(mockMultipartFile).contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println("Result => " + mvcResult.getResponse().getStatus());
    }
}
