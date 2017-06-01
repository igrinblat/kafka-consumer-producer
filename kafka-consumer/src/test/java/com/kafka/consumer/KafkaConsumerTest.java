package com.kafka.consumer;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConsumerTest {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerTest.class);

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(3, true, 2, "topic-example");


	@Test
	public void contextLoads() {
		Message message = new Message();
		message.setContent("Some content");
		message.setId(UUID.randomUUID().toString());
		kafkaTemplate.send("topic-example", message.getId(), message);
		logger.info("hi");
	}

	/*@TestConfiguration
	private static class KafkaEmbeddedConf{
		public KafkaEmbedded getKafkaEmbedded(@Value("${kafka.consumer.topic}") String topic){
			return new KafkaEmbedded(3, true, 3, topic);
		}
	}*/

}
