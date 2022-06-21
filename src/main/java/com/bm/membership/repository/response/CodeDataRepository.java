package com.bm.membership.repository.response;

import com.bm.membership.domain.response.CodeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.bm.membership.repository.response
 * fileName       : CodeDataRepository
 * author         : men16
 * date           : 2022-06-20
 * description    : CODE_DATA 리포지토리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public interface CodeDataRepository extends JpaRepository<CodeData, String> {
    Optional<CodeData> findByReasonKey(String reasonKey);
}
