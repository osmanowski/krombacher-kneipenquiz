package com.groupfive.krombacherkneipenquiz.Handler;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    // Custom class for storing principal

    public Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        // Generate principal with UUID as name
        return new StompPrincipal(UUID.randomUUID().toString());
    }


}