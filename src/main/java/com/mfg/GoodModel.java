package com.mfg;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


//@Document(collection="good")
public class GoodModel {

	@Indexed
	@NotNull
	@Size(max=50)
	private String name;
	@Min(value=0)
	private Integer age;
	private Date productionDate;
	@Id
	private String _id;
	public GoodModel() {
	}

	public GoodModel(String name, Integer age, Date productionDate) {
		 
		this.name = name;
		this.age = age;
		this.productionDate = productionDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String get_id() {
		return _id;
	}
	
	public String toString() {
		return String.format("Goods[name='%s', age='%s', productionDate='%s']", 
				name, age, productionDate);
	}

}
