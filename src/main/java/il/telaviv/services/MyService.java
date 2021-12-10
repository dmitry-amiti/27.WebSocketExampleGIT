package il.telaviv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Service
@EnableScheduling
public class MyService {

    @Autowired
    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


//    @Scheduled(fixedRate = 1000, initialDelay = 5000)
//    public void publishMessage() {
//        double value = (double) ThreadLocalRandom.current().nextInt(90) / 10 + 20;
//        messagingTemplate.convertAndSend("/topic/public", value);
//        System.out.println(value);
//    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println();
        System.out.println("[Disconnect Header] " + headerAccessor);
        System.out.println("[Disconnect Stats] " + webSocketMessageBrokerStats.toString());
        messagingTemplate.convertAndSend("/topic/public", "user disconnected");
    }


}



