package net.codejava.collections;

import java.util.Comparator;

public class EmployeeJobTitleComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {

		return o1.getJobTitle().compareTo(o2.getJobTitle());
	}

}
