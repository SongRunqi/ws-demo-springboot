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


# Steps
1. create a websocketConfig class which both enabled @EnableWebSocketMessageBroker and implements WebSocketMessageBrokerConfigurer
2. Override registerStompEndpoints method to set an endpoint for ws connecting
3. Override configureMessageBroker to create a message broker to broadcast messages using a specified prefix /groupChat, and also set the prefix for client send request with /ei.
4. Create MessageMapping for client request : broadcast.join, broadcast.chat, and with SendTo annotation send this message to the channel /group/all
5. client should connect to ws endpoint with the url: `/ei-ws` (default with current domain), subscribe to `/group/all`, when it connected, send a connected message '/ei/broadcast.join', 
6. client sends message with '/ei/broadcast.chat'