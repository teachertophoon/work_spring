package com.koitt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 * JAXB2 는 Date와 BigDecimal과 같은 복잡한 데이터 타입은
 * 자동으로 필드에 연결(mapping)시킬 수 없다.
 * 
 * 따라서, JAXB2가 Date 혹은 BigDecimal 변환을 할 수 있게 하려면
 * Adapter를 작성하여 수동으로 @XmlJavaTypeAdapter 애노테이션으로
 * Adapter와 연결시켜줘야 한다. 
 */

@XmlRootElement(name = "record")
public class Report {

	private Integer refId;
	private String name;
	private Integer age;
	private Date dob;
	private BigDecimal income;
	
	@XmlAttribute(name="refId")
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@XmlJavaTypeAdapter(JaxDateAdapter.class)
	@XmlElement
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@XmlJavaTypeAdapter(JaxBigDecimalAdapter.class)
	@XmlElement
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	
	@Override
	public String toString() {
		return "Report [refId=" + refId + ", name=" + name + ", age=" + age + ", dob=" + dob + ", income=" + income
				+ "]";
	}
	
	
}
