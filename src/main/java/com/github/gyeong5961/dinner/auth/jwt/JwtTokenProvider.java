package com.github.gyeong5961.dinner.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    //
    @Value("${JWT.ISSUER}")
    private String issuer;

    @Value("${JWT.SECRET-KEY}")
    private String secretKey;

    private Algorithm algorithm;
    
    private final MemberService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 생성
    public JwtResponse createToken(Member member) {
        String memberId = member.getMemberId();
        String accessToken = JWT.create().
                withIssuer(issuer)
                .withJWTId(memberId)
                .withExpiresAt(expiresAt(0))
                .sign(algorithm);
        String refreshToken = JWT.create().
                withIssuer(issuer)
                .withJWTId(memberId)
                .withExpiresAt(expiresAt(1))
                .sign(algorithm);
        return new JwtResponse(accessToken, refreshToken);
    }

    private Date expiresAt(int token) {
        Calendar cal = Calendar.getInstance();
        int tokenValidMinute = 30;
        cal.setTime(new Date());
        if (token == 0) {
            cal.add(Calendar.MINUTE, tokenValidMinute);
        }
        if (token == 1) {
            cal.add(Calendar.MINUTE, tokenValidMinute * 4);
        }
        System.out.println(cal.getTime());
        return cal.getTime();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        String memberId = jwtVerifier.verify(token).getId();
        UserDetails user = userDetailsService.loadUserByUsername(memberId);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    //
    // 토큰에서 회원 정보 추출
    public Member getMember(String token) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        String memberId = jwtVerifier.verify(token).getId();
        return userDetailsService.findMember(memberId);
    }

    //
    public JwtResponse resolveToken(HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken = request.getHeader("refreshToken");

        if (accessToken == null|| !validateToken(accessToken)) {
            if(refreshToken != null && validateToken(refreshToken)){
                return createToken(getMember(refreshToken));
            }
        }

        return new JwtResponse(accessToken,refreshToken);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) throws NullPointerException{
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        Date exp = jwtVerifier.verify(jwtToken).getExpiresAt();
        return new Date().before(exp);
    }
}
