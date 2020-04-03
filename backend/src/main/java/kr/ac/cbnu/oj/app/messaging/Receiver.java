package kr.ac.cbnu.oj.app.messaging;

import kr.ac.cbnu.oj.app.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "queue.messages")
    public void receiveMessage(Employee employee) {
        log.info("employee: {}", employee);
    }
}