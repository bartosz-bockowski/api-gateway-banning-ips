package com.example.carparkinggateway.repository;

import com.example.carparkinggateway.domain.BannedIP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannedIPRepository extends JpaRepository<BannedIP, Long> {

    @Transactional
    void deleteAllByIp(String ip);

    boolean existsByIp(String ip);

    Optional<BannedIP> findByIp(String ip);

}
