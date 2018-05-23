package au.com.momenton.hierarchytree.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import au.com.momenton.hierarchytree.model.Employee;

@Component
public class CSVParserUtil {
	
	public List<Employee> parseEmployeeCSVFile(String filename) {
		List<Employee> employees = new ArrayList<>();
		
		
		try {
			Path path = Paths.get(getClass().getClassLoader()
				      .getResource("employee.csv").toURI());
		    Stream<String> lines = Files.lines(path);
		    lines.forEach(line -> {
		    	
				Employee employee = new Employee();
				String[] empArray = StringUtils.trimArrayElements(line.split(","));
				
				int size = empArray.length;
				int index = 0;
				while(index < size) {
					switch (index) {
		            case 0:  
	            		employee.setName(empArray[index]);
	                    break;
		            case 1:  
		            	if(!StringUtils.isEmpty(empArray[index])) {
			            	employee.setId(Long.parseLong(empArray[index]));
		            	}
	                    break;
		            case 2: 
		            	if(!StringUtils.isEmpty(empArray[index]) && isNum(empArray[index])) {
			            	employee.setManagerId(Long.parseLong(empArray[index]));
		            	}
	                    break;
					}
					index++;
				}
				employees.add(employee);
		    });
		    lines.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}
	

	public static boolean isNum(String strNum) {
	    boolean ret = true;
	    try {
	        Long.parseLong(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}

}
