package kr.ac.cbnu.oj.app.messaging;

import kr.ac.cbnu.oj.app.domain.Employee;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JudgeProducer{

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private RabbitMessagingTemplate rabbitMessagingTemplate;
    private MappingJackson2MessageConverter mappingJackson2MessageConverter;

    public JudgeProducer(RabbitMessagingTemplate rabbitMessagingTemplate, MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        this.rabbitMessagingTemplate = rabbitMessagingTemplate;
        this.mappingJackson2MessageConverter = mappingJackson2MessageConverter;
    }

    public void sendToRabbitmq(Employee employee, String extraContent) {
        this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);

        Map<String, Object> header = new HashMap<>();
        header.put("extraContent", extraContent);

        this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.messages", employee, header);
    }
}
