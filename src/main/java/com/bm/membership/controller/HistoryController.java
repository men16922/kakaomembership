package com.bm.membership.controller;

import com.bm.membership.aop.HttpLogging;
import com.bm.membership.dto.api.history.HistoryResponseDto;
import com.bm.membership.dto.response.UserApiResponse;
import com.bm.membership.service.impl.HistoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * packageName    : com.bm.membership.controller
 * fileName       : HistoryController
 * author         : men16
 * date           : 2022-06-21
 * description    : 사용 내역 조회 controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "사용 내역 조회")
@RequestMapping("api/v1/membership/point")
public class HistoryController {

    private final HistoryService historyService;

    @HttpLogging
    @GetMapping(path = "/history")
    @Operation(summary = "사용 내역 조회",
            description = "사용 내역 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "membership_barcode", value = "ABCDEFGHIJ", required = true),
            @ApiImplicitParam(name = "start_date", value = "20220625", required = true),
            @ApiImplicitParam(name = "end_date", value = "20220625", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "적립 내역 조회 성공", content = @Content(schema = @Schema(implementation = HistoryResponseDto.class)))
    })
    public UserApiResponse historyApi(@ApiIgnore @Parameter(description = "바코드", in = ParameterIn.QUERY) @RequestParam("membership_barcode") String membershipBarcode,
                                      @Parameter(description = "시작기간", in = ParameterIn.QUERY) @RequestParam("start_date") String startDate,
                                      @Parameter(description = "종료기간", in = ParameterIn.QUERY) @RequestParam("end_date") String endDate,
                                      @Parameter(description = "기타(필수X)", in = ParameterIn.QUERY) @RequestParam MultiValueMap<String, String> param) {

        Map<String, String> paramMap = param.toSingleValueMap();
        paramMap.put("membership_barcode", membershipBarcode);
        paramMap.put("start_date", startDate);
        paramMap.put("end_date", endDate);

        log.info("HISTORY CONTROLLER PARAM -{}", param);
        return historyService.executeApiService(historyService.paramToDto(paramMap));
    }

}

