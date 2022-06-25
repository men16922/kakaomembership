package com.bm.membership.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.bm.membership.controller
 * fileName       : PublishControllerTest
 * author         : men16
 * date           : 2022-06-25
 * description    : 통합 바코드 발급 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PublishControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @Order(1)
    @DisplayName("바코드 발급 테스트(user_id 길이 실패)")
    void publishFailTest() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":1234567890,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PublishController.class))
                .andExpect(handler().methodName("publishApi"))
                .andExpect(jsonPath("$.returncode", is("00001")))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("바코드 발급 테스트(성공)")
    void publishSuccessTest() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":123456789,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PublishController.class))
                .andExpect(handler().methodName("publishApi"))
                .andExpect(jsonPath("$.returncode", is("00000")))
        ;
    }

    @Test
    @Order(3)
    @DisplayName("바코드 중복 발급 테스트")
    void duplictatePublishTest() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":200000000,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PublishController.class))
                .andExpect(handler().methodName("publishApi"))
                .andExpect(jsonPath("$.returncode", is("00002")))
                .andExpect(jsonPath("$.membership_barcode", is("ABCDEFGHIJ")))
        ;
    }

}