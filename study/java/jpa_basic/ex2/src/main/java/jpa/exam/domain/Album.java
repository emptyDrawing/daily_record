package jpa.exam.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
	
	private String artist;
	private String etc;
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}
	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}

	

}
