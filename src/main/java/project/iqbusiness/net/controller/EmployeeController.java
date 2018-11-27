package project.iqbusiness.net.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import project.iqbusiness.net.data.EmployeeRepository;
import project.iqbusiness.net.model.Employee;
import project.iqbusiness.net.service.EmployeeService;

@Model
public class EmployeeController {
	
	@Inject
    private EmployeeService employeeService;
	
	//@Inject
	FacesContext facesContext;
	
	@Produces
    @Named
    private Employee newEmployee;

	@Inject
	private EmployeeRepository employeeRepository;
	
	@Produces
    @Named
    private Employee updateEmployee;
	
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	String contextPath = request.getContextPath();
	
	@PostConstruct
    public void initNewEmployee() {
		newEmployee = new Employee();
    }
	
	
	public void registerEmployee() throws Exception {
		try {
			
			employeeService.createEmp(newEmployee);
			
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, null,
					"Congratulations, employee was succesfully " + "created.");
			FacesContext.getCurrentInstance().addMessage(null, m);

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath+"/employee/add/home.jsf");
			
			initNewEmployee();
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Could not add employee");
			FacesContext.getCurrentInstance().addMessage(null, m);
		}
	}
	
	public void updateEmployee() throws Exception {

		try {
			
			HttpSession httpSession = request.getSession(true);           
			Employee emp = (Employee) httpSession.getAttribute("emp");
			
			employeeService.updateEmp(emp);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Employee details have been updated.", "");
			FacesContext.getCurrentInstance().addMessage(null, m);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath+"/employee/update/home.jsf");

		} catch (Exception e) {

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					"Employee details could not be updated, please try again", "");
			FacesContext.getCurrentInstance().addMessage(null, m);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void action(Employee employee) throws IOException {
		try {
			
			Employee emp = employeeRepository.findById(employee.getEmployeeId());
	
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("emp", emp); 
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath+"/employee/update/home.jsf");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
