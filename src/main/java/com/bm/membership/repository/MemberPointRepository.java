package com.bm.membership.repository;

import com.bm.membership.domain.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * packageName    : com.bm.membership.repository
 * fileName       : MemberPointRepository
 * author         : men16
 * date           : 2022-06-21
 * description    : MEMBER_POINT Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        men16       최초 생성
 */
public interface MemberPointRepository extends JpaRepository<MemberPoint, String> {
    @Query(value = "SELECT M.MEMBER_POINT_SEQ, M.USER_ID, M.CATEGORY, M.CATEGORY_ID, M.TOTAL_POINT, M.VERSION FROM MEMBER_POINT M WHERE M.CATEGORY_ID =:category", nativeQuery = true)
    Optional<MemberPoint> findByShop(@Param("category") String category);
}

