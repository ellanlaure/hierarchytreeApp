package au.com.momenton.hierarchytree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.momenton.hierarchytree.model.Employee;
import au.com.momenton.hierarchytree.service.HierarchyTreeService;
import au.com.momenton.hierarchytree.util.CSVParserUtil;

@Controller
public class MainController {
	
	@Autowired
	private CSVParserUtil parser;
	@Autowired
	private HierarchyTreeService hierarchyTreeService;

	public CSVParserUtil getParser() {
		return parser;
	}

	public void setParser(CSVParserUtil parser) {
		this.parser = parser;
	}

	public HierarchyTreeService getHierarchyTreeService() {
		return hierarchyTreeService;
	}

	public void setHierarchyTreeService(HierarchyTreeService hierarchyTreeService) {
		this.hierarchyTreeService = hierarchyTreeService;
	}

	private static final String FILENAME = "employee.csv";

	
	@RequestMapping("/hierarchytreeApp")
	public String mainPage() {
		return "index";
	}
	
	@RequestMapping("/employeetree")
	@ResponseBody
	public ResponseEntity<Employee> hierarchytree() {
		return new ResponseEntity<>(hierarchyTreeService.getEmployeeTree(FILENAME), HttpStatus.OK);
	}
}
