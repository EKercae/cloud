package com.at.springcloud.service.impl;

import com.at.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author user
 * @create 2021-05-28 16:48
 */
@EnableBinding(Source.class)    //定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    /**
     *消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("serial:-------------->"+serial);
        return null;
    }
}
