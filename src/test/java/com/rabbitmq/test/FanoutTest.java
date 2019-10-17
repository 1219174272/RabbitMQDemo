package com.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabbitmq.service.SendMessageService;

/**
 * @author kangxz
 * @Description:
 * @date 2019/10/11 13:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {

    @Autowired
    SendMessageService SendMessageService;

    @Test
    public void test_01() {
        SendMessageService.sendWorkQueues("Junit4Runner");
    }

}