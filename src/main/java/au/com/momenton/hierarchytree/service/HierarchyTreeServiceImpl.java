package au.com.momenton.hierarchytree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.momenton.hierarchytree.model.Employee;
import au.com.momenton.hierarchytree.util.CSVParserUtil;

@Service
public class HierarchyTreeServiceImpl implements HierarchyTreeService {

	private Employee ceoEmployee;
    private Map<Long, Employee> employees;
    
	@Autowired
	private CSVParserUtil parser;
    
	public Employee getEmployeeTree(String filename) {
		List<Employee> employeeList = parser.parseEmployeeCSVFile(filename);
		employeeList.forEach(employee -> {
			if(employee != null) {
				if(employees == null) {
					employees = new HashMap<>();
				}
				employees.put(employee.getId(), employee);
				if(employee.getManagerId() == 0){
					ceoEmployee = employee;
				}
			}
		});
		
		buildHierarchyTree(ceoEmployee);
		
		return ceoEmployee;
    }
    
	private void buildHierarchyTree(Employee employee) {
        List<Employee> employeeList = findAllEmployeesByMgrId(employee.getId());
        employee.setSubordinates(employeeList);
        if (employeeList.size() == 0) {
            return;
        }

        for (Employee e : employeeList) {
        	buildHierarchyTree(e);
        }
    }
	
	
    public List<Employee> findAllEmployeesByMgrId(Long managerId) {
        List<Employee> subordinates = new ArrayList<Employee>();
        for (Employee e : employees.values()) {
            if (e.getManagerId() == managerId) {
            	subordinates.add(e);
            }
        }
        return subordinates;
    }

}
