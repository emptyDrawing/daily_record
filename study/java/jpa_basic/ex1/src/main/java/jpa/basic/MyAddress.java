package jpa.basic;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class MyAddress {

    private String city;
	private String street;
	private String zipcode;
    
    /**
     * 
     */
    public MyAddress() {
    }

    /**
     * @param city
     * @param street
     * @param zipcode
     */
    public MyAddress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    
    /**
     * @return the city
     */
    public String getCity() {
        return this.city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the street
     */
    public String getStreet() {
        return this.street;
    }
    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return this.zipcode;
    }
    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
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
        if (!(obj instanceof MyAddress)) {
            return false;
        }
        MyAddress other = (MyAddress) obj;
        return Objects.equals(this.city, other.city) && Objects.equals(this.street, other.street)
                && Objects.equals(this.zipcode, other.zipcode);
    }

}
