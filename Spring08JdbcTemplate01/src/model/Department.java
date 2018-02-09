package model;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 1. 필드
	private Integer deptno;
	private String dname;
	private String loc;
	private List<Employee> employeeList;	// 한 부서는 여러 직원을 가질 수 있는 관계
	
	// 2. 기본생성자
	public Department() {}
	
	// 3. 모든 필드 초기화 생성자
	public Department(Integer deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	// 4. getter, setter
	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	// 5. hashCode, equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((dname == null) ? 0 : dname.hashCode());
		result = prime * result + ((employeeList == null) ? 0 : employeeList.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Department)) {
			return false;
		}
		
		Department other = (Department) obj;
		if (this.deptno.equals(other.deptno)) {
			return true;
		}
		
		return false;
	}

	// 6. toString()
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [deptno=");
		builder.append(deptno);
		builder.append(", dname=");
		builder.append(dname);
		builder.append(", loc=");
		builder.append(loc);
		builder.append(", employeeList=");
		builder.append(employeeList);
		builder.append("]");
		return builder.toString();
	}	
}
