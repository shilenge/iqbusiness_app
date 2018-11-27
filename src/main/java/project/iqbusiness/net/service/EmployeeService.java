package project.iqbusiness.net.service;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import project.iqbusiness.net.data.EmployeeRepository;
import project.iqbusiness.net.model.Employee;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EmployeeService {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	
	@Resource
    private UserTransaction tx;
	
	public void createEmp(Employee employee) throws Exception 
    {
    	try {
			tx.begin();
	        em.persist(employee);
	    	em.flush();
	        tx.commit();
		} catch (NullPointerException e) {
			tx.rollback();
		}
    }
	
	//update employee details.
    public void updateEmp(Employee employee) throws IllegalStateException, SecurityException, SystemException {
        
		try {
			tx.begin();
			
			em.merge(employee);
			em.flush();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
    }
}
