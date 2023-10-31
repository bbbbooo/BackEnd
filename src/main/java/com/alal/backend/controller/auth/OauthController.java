package com.alal.backend.controller.auth;


import com.alal.backend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class OauthController {

    private final AuthService authService;

//    @GetMapping
//    @ResponseBody
//    public ResponseEntity<JwtTokenResponse> oauthLoginSuccess(HttpServletRequest request) {
//        return ResponseEntity.ok(authService.getAccessTokenAndRefreshTokenAfterOauthLogin(request.getCookies()));
//    }

    @GetMapping
    public String oauthLoginSuccess() {
        return "main/loginSuccess";
    }
}
