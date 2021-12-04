package mn.mo.backbone.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import mn.mo.backbone.domain.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info = @Info(title = "token API", version = "1.0", description = "This microservice will generate token that will be used for authentication"))
public class TokenController {

    @RequestMapping("token")
    public ResponseEntity<TokenResponse> getToken(String username,String password){
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token will be returned");
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @RequestMapping ("adminToken")
    public String getAdminToken(){
        return "admin token will be returned";
    }
}
