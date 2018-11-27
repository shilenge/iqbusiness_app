package project.iqbusiness.net.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import project.iqbusiness.net.model.Employee;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class EmployeeView implements Serializable {
	
	@Inject
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> employeeList(){
		return employeeRepository.findAllEmployeesOrderedById();
	}

}
