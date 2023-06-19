package jpa.exam.domain;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    
    private String city;
	private String street;
	private String zipcode;
    /**
     * @return the city
     */
    public String getCity() {
        return this.city;
    }
    /**
     * @return the street
     */
    public String getStreet() {
        return this.street;
    }
    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return this.zipcode;
    }
    /**
     * 
     */
    public Address() {
    }
    /**
     * @param city
     * @param street
     * @param zipcode
     */
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.city, this.street, this.zipcode);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(this.city, other.city) && Objects.equals(this.street, other.street)
                && Objects.equals(this.zipcode, other.zipcode);
    }

    

}
