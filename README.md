# INTRODUCTION
This is a quick start project for people who want to get start with SpringBoot + websocket.

# DETAILS
This project use stomp(simple text oriented messaging protocol) protocol as a top level protocol 
to send messages easily.

- @EnableWebSocketMessageBroker: enable websocket message handling with a message broker. 

The `WebSocketConfig` implements `WebSocketMessageBrokerConfigurer` which provides methods 
to customize Websocket configuration.

`registerStompEndpoints()`
```java
    registry.addEndpoint("/ei-ws").setAllowedOriginPatterns("*").withSockJS();
```
- Creates a WebSocket endpoint at /ei-ws where clients connect
- setAllowedOriginPatterns("*") - Allows connections from any domain (use cautiously in production)
- withSockJS() - Adds SockJS fallback for browsers that don't support WebSockets

`configureMessageBroker()`
```java
registry.enableSimpleBroker("/groupChat");
registry.setApplicationDestinationPrefixes("/ei");
```
- enableSimpleBroker("/groupChat") - Creates a simple in-memory message broker that 
  handles messages sent to destinations starting with /groupChat
- setApplicationDestinationPrefixes("/ei") - Sets the prefix for messages sent from 
  client to server (app-bound messages)