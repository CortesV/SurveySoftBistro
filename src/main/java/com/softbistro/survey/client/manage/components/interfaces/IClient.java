package com.softbistro.survey.client.manage.components.interfaces;

import com.softbistro.survey.client.manage.components.entity.Client;

public interface IClient {

	/**
	 * Find client in database by id of client
	 * 
	 * @param id
	 *            id - id of client
	 * @return return - client's information
	 */

	public Client findClient(Integer id);

	/**
	 * Save client to database
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @return return - information about of client
	 */
	public void saveClient(Client client);

	/**
	 * Delete client from database by email of client
	 * 
	 * @param id
	 *            id - id of client
	 * @return return - information about of client
	 */
	public void deleteClient(Integer id);

	/**
	 * Update information of client
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @param id
	 *            id - id of client
	 * 
	 * @return return - information about of client
	 */
	public void updateClient(Client client, Integer id);

	/**
	 * Update client's password
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @param id
	 *            id - id of client
	 * 
	 * @return return - information about of client
	 */
	public void updatePassword(Client client, Integer id);

	/**
	 * Save information about client that authorized with help of social
	 * networks
	 * 
	 * @param client
	 * @return
	 */
	public Client saveSocialClient(Client client);
	

	/**
	 * Find client by email and client name
	 * 
	 * @param client
	 * @return
	 */
	public Client findClientByLoginAndEmail(Client client);

	/**
	 * Find client by email, facebookId or googleId
	 * 
	 * @param template
	 *            template - email, facebookId or googleId
	 * @param value
	 *            value - value of template
	 * @return return - information about of client
	 */
	public Client findByTemplate(String template, String value);

}
