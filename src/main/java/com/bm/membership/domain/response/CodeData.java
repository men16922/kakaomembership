package com.bm.membership.domain.response;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * packageName    : com.bm.membership.domain.response
 * fileName       : CodeData
 * author         : men16
 * date           : 2022-06-20
 * description    : CODE_DATA 테이블
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CODE_DATA")
public class CodeData {

    @Id
    @Column(name = "ERROR_CODE", nullable = false) @NotNull
    private String errorCode;

    @Column(name = "ERROR_MSG", nullable = false) @NotNull
    private String errorMsg;

    @Column(name = "REASON_KEY", nullable = false) @NotNull
    private String reasonKey;

    @Column(name = "IN_DATE")
    private LocalDateTime inDate;
}

