package com.example.carparkinggateway.controller;

import com.example.carparkinggateway.command.BannedIPCommand;
import com.example.carparkinggateway.domain.BannedIP;
import com.example.carparkinggateway.dto.BannedIPDTO;
import com.example.carparkinggateway.service.BannedIPService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BannedIPController {

    private final BannedIPService bannedIPService;

    private final ModelMapper modelMapper;

    @PostMapping("/ban")
    public ResponseEntity<BannedIPDTO> ban(@Valid @RequestBody BannedIPCommand bannedIPCommand) {
        return new ResponseEntity<>(modelMapper.map(bannedIPService.ban(
                        modelMapper.map(bannedIPCommand, BannedIP.class)),
                BannedIPDTO.class
        ), HttpStatus.OK);
    }

    @DeleteMapping("/unban")
    public HttpStatus unban(@Valid @RequestBody BannedIPCommand bannedIPCommand) {
        bannedIPService.unban(bannedIPCommand.getIp());
        return HttpStatus.OK;
    }

}
