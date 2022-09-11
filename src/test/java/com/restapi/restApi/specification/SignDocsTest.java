package com.restapi.restApi.specification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.restApi.api.controller.RestDocs.RestDocsController;
import com.restapi.restApi.api.controller.RestDocs.RestDocsService;
import com.restapi.restApi.api.controller.RestDocs.UserRequest;
import com.restapi.restApi.api.controller.RestDocs.UserResponse;
import com.restapi.restApi.config.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = RestDocsController.class,
//        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class), })
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class SignDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestDocsService service;

    @Test
    @DisplayName("Rest Docs Test")
    public void test() throws Exception {
        //given
        UserResponse response = UserResponse.builder().name("test").build();

        given(service.convert(any(UserRequest.class))).willReturn(response);

        //when
        UserRequest request = UserRequest.builder().name("test").age(20).build();

        ResultActions result = this.mockMvc.perform(post("/user").content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-list"));
    }
}
