package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="goods")

public class Good {
	@Id
	@Column(name="goods_id")
	private String articeId;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="price")
	private Integer price;
	
	public Good() {
		// TODO Auto-generated constructor stub
	}

	public Good(String articeId, String name, int price) {
		this.articeId = articeId;
		this.name = name;
		this.price = price;
	}
	
	

}
