package io.madhu.realtimeNotifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SseEmitter
 * SseEmitter is used in Spring MVC (non-reactive) applications to handle server-sent events. It is part of the traditional Spring Web framework.
 * Developers need to manually handle the lifecycle of the SseEmitter and send events using the send() method.
 *
 *
 * ServerSentEvent
 * ServerSentEvent is a class used in Spring WebFlux (reactive programming) to represent a server-sent event.
 * It is typically used when working with reactive streams and the Flux type.
 * Spring WebFlux provides built-in support for ServerSentEvent via Flux<ServerSentEvent<?>>.
 * Spring WebFlux takes care of serializing these events and sending them to the client.
 */
@SpringBootApplication
public class ServerSentRealtimeNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSentRealtimeNotificationsApplication.class, args);
	}
}
