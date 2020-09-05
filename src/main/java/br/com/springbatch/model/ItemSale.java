package br.com.springbatch.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ItemSale extends GenericId{
	
	private int quatityItem;
	private BigDecimal price;
	private BigDecimal subTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public int getQuatityItem() {
		return quatityItem;
	}
	public void setQuatityItem(int quatityItem) {
		this.quatityItem = quatityItem;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSubTotal() {
		this.subTotal = new BigDecimal(this.quatityItem);
		this.subTotal = this.price.multiply(this.subTotal, MathContext.DECIMAL128).setScale(2,
				RoundingMode.HALF_EVEN);
		return subTotal;
	}
}
