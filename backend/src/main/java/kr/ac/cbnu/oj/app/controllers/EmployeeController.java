package kr.ac.cbnu.oj.app.controllers;

import kr.ac.cbnu.oj.app.domain.Employee;
import kr.ac.cbnu.oj.app.messaging.JudgeProducer;
import kr.ac.cbnu.oj.app.messaging.Receiver;
import kr.ac.cbnu.oj.app.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class EmployeeController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private EmployeeService employeeService;
    private JudgeProducer producer;
    private Receiver receiver;

    public EmployeeController(EmployeeService employeeService, JudgeProducer producer, Receiver receiver) {
        this.employeeService = employeeService;
        this.producer = producer;
        this.receiver = receiver;
    }

    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, consumes = { "application/json",
            "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee, HttpServletRequest request,
                               HttpServletResponse response) {
        log.info("Creating an Employee {" + employee + "}");
        Employee createdEmployee = this.employeeService.createEmployee(employee);
        producer.sendToRabbitmq(createdEmployee, "Employee has been published to RabbitMQ");
        response.setHeader("Location", request.getRequestURL().append("/").append(createdEmployee.getId()).toString());
    }
}
