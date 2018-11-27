package project.iqbusiness.net.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import project.iqbusiness.net.model.Employee;


@ApplicationScoped
public class EmployeeRepository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	public Employee findById(int id) {
        return em.find(Employee.class, id);
    }
	
	public List<Employee> findAllEmployeesOrderedById() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        criteria.select(employee).orderBy(cb.asc(employee.get("employeeId")));
        return em.createQuery(criteria).getResultList();
    }

}
