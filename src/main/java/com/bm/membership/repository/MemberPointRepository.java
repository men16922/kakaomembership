package com.bm.membership.repository;

import com.bm.membership.domain.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;

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
}

