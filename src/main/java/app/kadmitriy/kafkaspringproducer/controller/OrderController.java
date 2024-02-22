package app.kadmitriy.kafkaspringproducer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.kadmitriy.kafkaspringproducer.model.Order;
import app.kadmitriy.kafkaspringproducer.service.Producer;

@RestController
public class OrderController {

	private final Logger log = LoggerFactory.getLogger(OrderController.class);

	private final Producer producer;

	public OrderController(Producer producer) {
		super();
		this.producer = producer;
	}

	@PostMapping("/api/orders")
	public Order sendOrder() { //@RequestBody Order order
		log.info("Send order to kafka");
		
		Order order = new Order();
		order.setBarCode("001");
		order.setPrice(123123);
		order.setProductName("prod1");
		order.setQuantity(0);
		
		
		producer.sendOrderEvent(order);
		return order;
	}

}
