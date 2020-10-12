package nsi.contractManagement.utils;

import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.DTO.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenUtilTest {
//    @Autowired
//    JwtTokenUtil jwtTokenUtil;

    @Test
    void getUserNameFromToken() {
    }

    @Test
    void validateToken() {
    }

    @Test
    void TestGenerateToken() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        Map<String, Object> claims = new HashMap<>();
        claims.put("CLAIM_KEY_USERNAME", "123");
        claims.put("CLAIM_KEY_CREATED", new Date());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(UserDO.builder().email("1@1.1").password("123").name("张三").build());
        System.out.println(jwtTokenUtil.generateToken(loginUser));
    }

    @Test
    void refreshHeadToken() {
    }

    @Test
    void tesTGenerateExpirationDate() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        System.out.println(jwtTokenUtil.generateExpirationDate());
    }
}