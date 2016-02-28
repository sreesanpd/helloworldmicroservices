package fr.mickaelbaron.helloworldrestmicroservice.event.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.inject.Singleton;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Mickael BARON
 */
@Singleton
public class RabbitMQFactory {

	private static final String RABBIT_MQ_HOST_ENV_KEY = "RABBIT_MQ_HOST";

	public static final String EXCHANGE_NAME = "helloworld";

	private Channel currentChanel;

	public RabbitMQFactory() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(getRabbitMQHost());
		Connection connection = factory.newConnection();
		currentChanel = connection.createChannel();

		currentChanel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	}

	public Channel getChannel() {
		return currentChanel;
	}

	private String getRabbitMQHost() {
		String redisHost = System.getenv(RABBIT_MQ_HOST_ENV_KEY);

		if (redisHost == null || redisHost.isEmpty()) {
			return "localhost";
		} else {
			return redisHost;
		}
	}
}
