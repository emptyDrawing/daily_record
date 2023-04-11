package jpa.basic;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member extends BaseEntity{

	public Member() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	// Preiod
	@Embedded
	private MyPeriod period;

	// 주소
	@Embedded
	private MyAddress homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name= "work_city")),
		@AttributeOverride(name = "street", column = @Column(name= "work_street")),
		@AttributeOverride(name = "zipcode", column = @Column(name= "work_zipcode"))
	})
	private MyAddress workAddress;

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the period
	 */
	public MyPeriod getPeriod() {
		return this.period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(MyPeriod period) {
		this.period = period;
	}

	/**
	 * @return the homeAddress
	 */
	public MyAddress getHomeAddress() {
		return this.homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(MyAddress homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the workAddress
	 */
	public MyAddress getWorkAddress() {
		return this.workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(MyAddress workAddress) {
		this.workAddress = workAddress;
	}

	
}
