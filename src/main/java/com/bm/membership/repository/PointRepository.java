package com.bm.membership.repository;

import com.bm.membership.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Query("SELECT p from Point p WHERE p.barcode = :barcode AND p.approvedAt BETWEEN :startdate AND :enddate")
    Optional<List<Point>> findByBarcodeAndDates(@Param("barcode") String barcode, @Param("startdate") LocalDateTime startdate, @Param("enddate") LocalDateTime enddate);
}

