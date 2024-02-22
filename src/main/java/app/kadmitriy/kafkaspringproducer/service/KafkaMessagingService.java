package app.kadmitriy.kafkaspringproducer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import app.kadmitriy.kafkaspringproducer.model.OrderSendEvent;

@Service
public class KafkaMessagingService {
    
	@Value("${topic.send-order}")
    private String sendClientTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

	public KafkaMessagingService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}    
    
    public void sendOrder(OrderSendEvent orderSendEvent) {
       kafkaTemplate.send(sendClientTopic, orderSendEvent.getBarCode(), orderSendEvent);
    }

}
