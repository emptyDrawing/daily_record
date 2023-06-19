package jpa.exam.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERANME")
	private String username;
	
	@Embedded
	private Address homeAddress; 

	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@Column(name="FOOD_NAME")
	private Set<String> favorivateFooos = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	private List<Address> addressHistory = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

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
	 * @return the homeAddress
	 */
	public Address getHomeAddress() {
		return this.homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the favorivateFooos
	 */
	public Set<String> getFavorivateFooos() {
		return this.favorivateFooos;
	}

	/**
	 * @param favorivateFooos the favorivateFooos to set
	 */
	public void setFavorivateFooos(Set<String> favorivateFooos) {
		this.favorivateFooos = favorivateFooos;
	}

	/**
	 * @return the addressHistory
	 */
	public List<Address> getAddressHistory() {
		return this.addressHistory;
	}

	/**
	 * @param addressHistory the addressHistory to set
	 */
	public void setAddressHistory(List<Address> addressHistory) {
		this.addressHistory = addressHistory;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return this.orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}
