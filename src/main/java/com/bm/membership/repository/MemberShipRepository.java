package com.bm.membership.repository;

import com.bm.membership.domain.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.bm.membership.repository
 * fileName       : MemberShipRepository
 * author         : men16
 * date           : 2022-06-20
 * description    : 멤버십 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public interface MemberShipRepository extends JpaRepository<MemberShip, String> {
}
