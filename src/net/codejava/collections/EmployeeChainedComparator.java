package net.codejava.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeChainedComparator implements Comparator<Employee> {

	
	private List<Comparator<Employee>> listComparators;
	
	
	
	
	@SafeVarargs
	public EmployeeChainedComparator(Comparator<Employee>... comparators) {
		super();
		this.listComparators = Arrays.asList(comparators);
	}

	@Override
	public int compare(Employee o1, Employee o2) {

		for(Comparator<Employee> empComporator : listComparators) {
			int result = empComporator.compare(o1, o2);
			if(result!=0)
				return result;
		}
		
		return 0;
	}

}
