package kylo.spring.jwttutorial.jwt;

public interface JwtProperties {

    String SECRET = "4jhhaskjdfbkj131231anbfsadfsdfasadfr3243431314134asdfsdfaafsd";
    int EXPIRATION_TIME = 86400;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
