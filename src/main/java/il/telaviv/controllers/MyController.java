package il.telaviv.controllers;

import il.telaviv.forms.NumberForm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MyController {

    @Autowired
    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    private Integer number = 30;


    @MessageMapping("/send")
    @SendTo("/topic/public")
    public String sendMessage(String message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("[Controller Header] " + headerAccessor);
        System.out.println("[Controller Stats] " + webSocketMessageBrokerStats.toString());

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name", toolForm.getName());
//        jsonObject.put("value", toolForm.getValue());

        System.out.println(message);
        return message;
    }


    @GetMapping("/")
    public String getPage() {
        return "ws_timer";
    }


    @GetMapping("/getdata")
    @ResponseBody
    public String getAllTools() {
        number = 44;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", this.number);
        System.out.println(this.number);
        return jsonObject.toString();
    }


    @PostMapping(value = "/getdata", consumes = "application/json")  // принимаем только json
    @ResponseBody
//        public String getAll(@RequestBody Map<String, Integer> map) {
    public String getAll(@RequestBody NumberForm numberForm) { //@RequestBody, чтобы прочитать JSON (для формы не нужно)
//        number = map.get("number");
        number = numberForm.getNumber();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", this.number);
        System.out.println(this.number);
        return jsonObject.toString();
    }


}
