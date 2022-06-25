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
 * fileName       : PointControllerTest
 * author         : men16
 * date           : 2022-06-25
 * description    : 포인트 사용/적립 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-25        men16       최초 생성
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PointControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @Order(1)
    @DisplayName("포인트 적립 실패 테스트(바코드 길이 실패)")
    void earnFailTest1() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"2317077\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("earnApi"))
                .andExpect(jsonPath("$.returncode", is("00001")))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("포인트 적립 실패 테스트(상점 ID 존재 X)")
    void earnFailTest2() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics4\",\"membership_barcode\":\"2317077123\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("earnApi"))
                .andExpect(jsonPath("$.returncode", is("00003")))
        ;
    }

    @Test
    @Order(3)
    @DisplayName("포인트 적립 실패 테스트(바코드 존재 X)")
    void earnFailTest3() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"2317077123\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("earnApi"))
                .andExpect(jsonPath("$.returncode", is("00004")))
        ;
    }

    @Test
    @Order(4)
    @DisplayName("포인트 적립 성공 테스트")
    void earnSuccessTest() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"ABCDEFGHIJ\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("earnApi"))
                .andExpect(jsonPath("$.returncode", is("00000")))
        ;
    }

    @Test
    @Order(5)
    @DisplayName("포인트 사용 실패 테스트(바코드 길이 실패)")
    void useFailTest1() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"2317077\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00001")))
        ;
    }

    @Test
    @Order(6)
    @DisplayName("포인트 사용 실패 테스트(상점 ID 존재 X)")
    void useFailTest2() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics4\",\"membership_barcode\":\"2317077123\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00003")))
        ;
    }

    @Test
    @Order(7)
    @DisplayName("포인트 사용 실패 테스트(바코드 존재 X)")
    void useFailTest3() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"2317077123\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00004")))
        ;
    }

    @Test
    @Order(8)
    @DisplayName("포인트 사용 실패 테스트(적립 내역 없음)")
    void useFailTest4() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"cosmetics1\",\"membership_barcode\":\"ABCDEFGHIJ\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00005")))
        ;
    }

    @Test
    @Order(9)
    @DisplayName("포인트 사용 실패 테스트(적립 금액 초과)")
    void useFailTest5() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"testshop\",\"membership_barcode\":\"ABCDEFGHIJ\",\"point\":1100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00005")))
        ;
    }

    @Test
    @Order(10)
    @DisplayName("포인트 사용 성공 테스트")
    void useSuccessTest() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/v1/membership/point/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"partner_id\":\"testshop\",\"membership_barcode\":\"ABCDEFGHIJ\",\"point\":100,\"order_id\":\"order_id\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(PointController.class))
                .andExpect(handler().methodName("useApi"))
                .andExpect(jsonPath("$.returncode", is("00000")))
        ;
    }



}