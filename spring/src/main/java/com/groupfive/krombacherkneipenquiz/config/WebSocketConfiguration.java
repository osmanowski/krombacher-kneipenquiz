package com.groupfive.krombacherkneipenquiz.config;

import com.groupfive.krombacherkneipenquiz.Handler.CustomHandshakeHandler;
import com.groupfive.krombacherkneipenquiz.Handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.util.concurrent.Executors;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {



    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/socket").setAllowedOrigins("http://localhost:4200").setHandshakeHandler(new CustomHandshakeHandler()).withSockJS();

    }
}
