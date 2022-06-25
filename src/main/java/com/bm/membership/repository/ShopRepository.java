package com.bm.membership.repository;

import com.bm.membership.domain.Shop;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.bm.membership.repository
 * fileName       : ShopRepository
 * author         : men16
 * date           : 2022-06-20
 * description    : 상점 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-20        men16       최초 생성
 */
public interface ShopRepository extends JpaRepository<Shop, String> {
    @Cacheable(value = "shop")
    Optional<Shop> findByPartnerId(String partnerId);
}
