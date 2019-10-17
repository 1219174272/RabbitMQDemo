package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rabbitmq.common.HttpResultMessage;
import com.rabbitmq.service.SendMessageService;

/**
 * 本文来自 http://blog.chriscs.com author：Chris
 *
 * 转自： https://www.jianshu.com/p/03c7799df0e9 author：Chrisdon
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public SendMessageService SendMessageService;

    @GetMapping("/sendMessage/{type}")
    public HttpResultMessage sendMQ(@RequestParam("message") String message, @PathVariable int type) {
        HttpResultMessage httpResultMessage = new HttpResultMessage();

        if (type == 1) {
            SendMessageService.sendWorkQueues(message);
			httpResultMessage.setHttpMessage("WorkQueues");
        } else if (type == 2) {
            SendMessageService.sendFanout(message);
			httpResultMessage.setHttpMessage("Fanout");
        } else if (type == 3) {
			SendMessageService.sendDirect(message);
			httpResultMessage.setHttpMessage("Routing");
        } else if (type == 4) {
            SendMessageService.sendTopics(message);
            httpResultMessage.setHttpMessage("Topics");
        }else{
            SendMessageService.sendHeaders(message);
            httpResultMessage.setHttpMessage("Headers");
        }

        return httpResultMessage;
    }

}
