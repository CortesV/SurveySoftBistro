package com.softbistro.survey.participant.components.entity;

import java.io.Serializable;

/**
 * Class that represent participant entity
 * 
 * @author af150416
 *
 */
public class Participant implements Serializable {

	/**
	 * standard value for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * participant id
	 */
	private Integer id;

	/**
	 * participant first name
	 */
	private String firstName;

	/**
	 * participant last name
	 */
	private String lastName;

	/**
	 * participant e-mail
	 */
	private String eMail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}