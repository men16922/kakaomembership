package com.bm.membership.controller;

import com.bm.membership.dto.response.ApiResponse;
import com.bm.membership.service.impl.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequiredArgsConstructor
@Tag(name = "사용 내역 조회")
@RequestMapping("api/v1/membership/point")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping(path = "/history")
    @Operation(summary = "사용 내역 조회",
            description = "사용 내역 조회")
    public ApiResponse historyApi(@RequestParam MultiValueMap<String, String> param) {
        return historyService.executeApiService(historyService.paramToDto(param));
    }

}

