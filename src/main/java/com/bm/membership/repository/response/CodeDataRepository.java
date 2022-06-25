package com.bm.membership.repository.response;

import com.bm.membership.domain.response.CodeData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.bm.membership.repository.response
 * fileName       : CodeDataRepository
 * author         : men16
 * date           : 2022-06-20
 * description    : CODE_DATA Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public interface CodeDataRepository extends JpaRepository<CodeData, String> {
    @Cacheable(value = "codeData")
    Optional<CodeData> findByReasonKey(String reasonKey);
}
