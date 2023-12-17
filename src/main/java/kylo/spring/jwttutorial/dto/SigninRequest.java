package kylo.spring.jwttutorial.dto;

import lombok.Data;

@Data
public class SigninRequest {

    private String email;
    private String password;
}
