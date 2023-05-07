package yong.hellospring.health.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yong.hellospring.global.config.FeignConfig;
import yong.hellospring.health.dto.HealthCheckResponseDto;

@FeignClient(url = "http://localhost:8080", name = "health-check", configuration = FeignConfig.class)
public interface HealthCheckFeignClient {
    @GetMapping(value = "/api/health/provide", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthCheckResponseDto> getServerStatus();
}
