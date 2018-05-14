package rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/*
 * 功能概要：消费接收
 * rabbitMQ是一款基于AMQP协议的消息中间件，它能够在应用之间提供可靠的消息传输。
 *
 * produce--->exchange--->queue--->consumer
 * RabbitMQ 消息模型
RabbitMQ消息发送时，生产者是不知道消息是否发送到某个队列中去了，生产者仅仅只能将消息发送给某个交换器。
Broker
RabbbitMQ消息队列代理服务器实体。
Producer
发送消息的应用程序。
Consumer
接收消息的用户程序。
Exchange
交换器，生产者直接将消息发送给交换器。交换器将消息分发给指定的队列。它指定消息按什么规则，路由到哪个队列。
Binding
绑定，指的是交换器和队列之间的关系。它的作用就是把exchange和queue按照路由规则绑定起来。
Routing Key
路由关键字，exchange根据这个关键字进行消息投递。
vhost
虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离。
Channel
消息通道，包含了大量的API可用于编程。在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
Connection
在客户创建一个到某个虚拟主机的连接。
ConnectionFactory
连接工厂类。可以创建一个连接。
 */
public class MessageConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Override
    public void onMessage(Message message) {
        logger.info("receive message:{}", message);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("message.toString(): " + new String(message.getBody()));
    }

}
