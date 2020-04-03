package kr.ac.cbnu.oj.app.repositories;

import kr.ac.cbnu.oj.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
