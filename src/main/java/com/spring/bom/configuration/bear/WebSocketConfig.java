package com.spring.bom.configuration.bear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.spring.bom.handler.bear.SocketHandler;


@Configuration
@EnableWebSocket
//@EntityScan(basePackages = {"xxx.xxx.xx.aaa.domain", "xxx.xxx.xx.bbb.domain"})
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	SocketHandler socketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		System.out.println("WebSocketConfig registerWebSocketHandlers start.....");
		registry.addHandler(socketHandler, "/chating/{roomNumber}");
	}

}
