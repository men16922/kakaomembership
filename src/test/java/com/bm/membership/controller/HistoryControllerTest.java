package com.bm.membership.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.bm.membership.controller
 * fileName       : HistoryControllerTest
 * author         : men16
 * date           : 2022-06-25
 * description    : 사용 내역 조회 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HistoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("사용 내역 조회 테스트(파라미터 길이)")
    void failHistory1() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/v1/membership/point/history?membership_barcode=ABCDEFGH&start_date=20220625&end_date=20220625")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(HistoryController.class))
                .andExpect(handler().methodName("historyApi"))
                .andExpect(jsonPath("$.returncode", is("00001")))
        ;
    }

    @Test
    @DisplayName("사용 내역 조회 테스트(날짜형식)")
    void failHistory2() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/v1/membership/point/history?membership_barcode=ABCDEFGHIJ&start_date=2022-06-25&end_date=2022-06-25")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(HistoryController.class))
                .andExpect(handler().methodName("historyApi"))
                .andExpect(jsonPath("$.returncode", is("00001")))
        ;
    }

    @Test
    @DisplayName("사용 내역 조회 테스트(성공)")
    void successHistory() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/v1/membership/point/history?membership_barcode=ABCDEFGHIJ&start_date=20220625&end_date=20220625")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(HistoryController.class))
                .andExpect(handler().methodName("historyApi"))
                .andExpect(jsonPath("$.returncode", is("00000")))
        ;
    }

}