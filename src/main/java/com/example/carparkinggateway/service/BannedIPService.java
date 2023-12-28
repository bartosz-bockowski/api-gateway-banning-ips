package com.example.carparkinggateway.service;

import com.example.carparkinggateway.domain.BannedIP;
import com.example.carparkinggateway.repository.BannedIPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannedIPService {

    private final BannedIPRepository bannedIPRepository;

    public BannedIP ban(BannedIP bannedIP) {
        return findByIp(bannedIP.getIp())
                .orElseGet(() -> bannedIPRepository.save(bannedIP));
    }

    public void unban(String ip) {
        bannedIPRepository.deleteAllByIp(ip);
    }

    public boolean existsByIp(String ip) {
        return bannedIPRepository.existsByIp(ip);
    }

    public Optional<BannedIP> findByIp(String ip) {
        return bannedIPRepository.findByIp(ip);
    }

}
