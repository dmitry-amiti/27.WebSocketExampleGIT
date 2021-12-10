package il.telaviv.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
public class WebSocketSecConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer  {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
//                .simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.SUBSCRIBE,
//                        SimpMessageType.DISCONNECT).hasAnyRole("USER", "ADMIN")
//                .simpDestMatchers("/app/**").hasRole("ADMIN")
//                .anyMessage().denyAll();
                .anyMessage().permitAll();

    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

}
