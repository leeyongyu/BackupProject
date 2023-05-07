package yong.hellospring.health.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yong.hellospring.health.client.HealthCheckFeignClient;
import yong.hellospring.health.dto.HealthCheckResponseDto;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckFeignClient healthCheckFeignClient;
    @GetMapping("/api/health/provide")
    public ResponseEntity<HealthCheckResponseDto> provideHealth(){
        return new ResponseEntity<>(
                HealthCheckResponseDto.builder()
                        .status(true)
                        .health("ok")
                        .build(),
                HttpStatus.OK
        );
    }
    @GetMapping("/api/health")
    public ResponseEntity<HealthCheckResponseDto> checkHealth(){
        return healthCheckFeignClient.getServerStatus();
    }
}
