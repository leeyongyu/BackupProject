package yong.hellospring.health.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthCheckResponseDto {

    private Boolean status;
    private String health;

    @Builder
    public HealthCheckResponseDto(Boolean status, String health){
        this.status = status;
        this.health = health;
    }
}
