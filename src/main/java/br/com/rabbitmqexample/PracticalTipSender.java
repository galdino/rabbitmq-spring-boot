package br.com.rabbitmqexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PracticalTipSender {
	
	private static final Logger log = LoggerFactory.getLogger(PracticalTipSender.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	public PracticalTipSender(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Scheduled(fixedDelay = 3000L)
	public void sendPracticalTip() {
		PracticalTipMessage practicalTipMessage = new PracticalTipMessage("Always use Immutable classes in Java", 1, false);
		this.rabbitTemplate.convertAndSend(RabbitmqExampleApplication.EXCHANGE_NAME, RabbitmqExampleApplication.ROUTING_KEY, practicalTipMessage);
		log.info("Practical tip sent");
	}

}
