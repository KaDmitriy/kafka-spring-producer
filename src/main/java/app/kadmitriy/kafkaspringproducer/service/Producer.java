package app.kadmitriy.kafkaspringproducer.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.kadmitriy.kafkaspringproducer.model.Order;
import app.kadmitriy.kafkaspringproducer.model.OrderSendEvent;

@Service
public class Producer {
	
	private final Logger log = LoggerFactory.getLogger(Producer.class);

	private final KafkaMessagingService kafkaMessagingService;
	private final ModelMapper modelMapper;

	@Autowired
    public Producer(KafkaMessagingService kafkaMessagingService, ModelMapper modelMapper) {
		this.kafkaMessagingService = kafkaMessagingService;
		this.modelMapper = modelMapper;
	}


	public Order sendOrderEvent(Order order) {
        kafkaMessagingService.sendOrder(modelMapper.map(order, OrderSendEvent.class));
        log.info("Send order from producer {}", order);
        return order;
    }
	
}
