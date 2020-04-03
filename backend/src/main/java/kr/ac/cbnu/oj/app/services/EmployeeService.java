package kr.ac.cbnu.oj.app.services;

import kr.ac.cbnu.oj.app.domain.Employee;
import kr.ac.cbnu.oj.app.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        log.info("Service : Creating Employee: {}", employee);
        return employeeRepository.save(employee);
    }
}
