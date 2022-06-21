package com.bm.membership.repository;

import com.bm.membership.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.bm.membership.repository
 * fileName       : PointRepository
 * author         : men16
 * date           : 2022-06-20
 * description    : 포인트 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public interface PointRepository extends JpaRepository<Point, String> {
}

