package kylo.spring.jwttutorial.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String token;
}
