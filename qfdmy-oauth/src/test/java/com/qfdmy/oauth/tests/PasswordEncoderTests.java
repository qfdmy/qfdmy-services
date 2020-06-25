package com.qfdmy.oauth.tests;

import com.qfdmy.oauth.AuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class PasswordEncoderTests {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        System.out.println(passwordEncoder.encode("dashboard"));
        System.out.println(passwordEncoder.encode("portal"));
    }
}
