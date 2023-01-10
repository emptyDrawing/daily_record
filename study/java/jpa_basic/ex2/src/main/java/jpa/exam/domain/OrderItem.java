package jpa.exam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ORDER_ITEM_ID")
	private Long id;
	
	@Column(name ="ORDER_ID")
	private Long orderId;
	@Column(name ="ITEM_ID")
	private Long itemId;

	private int orderPrice;
	private int count;

	


	public OrderItem() {
	}




	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}




	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}




	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}




	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}




	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}




	/**
	 * @return the orderPrice
	 */
	public int getOrderPrice() {
		return orderPrice;
	}




	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}




	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}




	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
}
