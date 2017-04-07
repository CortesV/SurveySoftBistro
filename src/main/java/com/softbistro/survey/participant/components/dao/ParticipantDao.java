package com.softbistro.survey.participant.components.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softbistro.survey.participant.components.entity.Participant;
import com.softbistro.survey.participant.components.interfaces.IParticipant;

/**
 * Data access object for participant entity
 * 
 * @author af150416
 *
 */
@Repository
public class ParticipantDao implements IParticipant {

	private static final Logger LOGGER = Logger.getLogger(ParticipantDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_FOR_SETTING_PARTICIPANT = "INSERT INTO participant (client_id, first_name, "
			+ "last_name, email) VALUES (?, ?, ?, ?)";
	private static final String SQL_FOR_UPDATING_PARTICIPANT = "UPDATE participant AS p "
			+ "SET p.first_name= ?, p.last_name= ?, p.email = ? WHERE p.id= ?";
	private static final String SQL_FOR_DELETING_PARTICIPANT = "UPDATE participant AS p SET p.delete = 1 WHERE p.id = ?";
	private static final String SQL_FOR_GETTING_PARTICIPANT_BY_ID = "SELECT * FROM participant WHERE participant.id= ? "
			+ "AND participant.delete = 0";
	private static final String SQL_FOR_GETTING_PARTICIPANT_BY_ATTRIBUTE_VALUE = "SELECT * FROM participant AS p "
			+ "LEFT JOIN attribute_values AS av ON av.participant_id=p.id LEFT JOIN attributes AS at "
			+ "ON at.id=av.attribute_id WHERE at.id = ? AND av.attribute_value = ? AND p.delete = 0 AND av.delete = 0 AND at.delete = 0";
	private static final String SQL_FOR_GETTING_PARTICIPANT_BY_EMAIL_AND_CLIENT_ID = "SELECT * FROM participant AS p "
			+ "LEFT JOIN connect_group_participant AS c ON c.participant_id=p.id LEFT JOIN group AS g ON g.id=c.group_id "
			+ "WHERE p.email= ? AND g.client_id = ? AND p.delete = 0";
	private static final String SELECT_CLIENT_ALL_PARTICIPANTS = "SELECT * FROM participant WHERE client_id = ? "
			+ "AND participant.delete = 0";

	/**
	 * Method for creating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@Override
	public void setParticipant(Participant participant) {

		try {

			jdbcTemplate.update(SQL_FOR_SETTING_PARTICIPANT, participant.getClientId(), participant.getFirstName(), participant.getLastName(),
					participant.geteMail());
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
		}
	}

	/**
	 * Method for updating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@Override
	public void updateParticipant(Participant participant, Integer id) {

		try {

			jdbcTemplate.update(SQL_FOR_UPDATING_PARTICIPANT, participant.getFirstName(), participant.getLastName(),
					participant.geteMail(), id);
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
		}
	}

	/**
	 * Method for deleting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@Override
	public void deleteParticipantById(Integer participantId) {

		try {

			jdbcTemplate.update(SQL_FOR_DELETING_PARTICIPANT, participantId);
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
		}
	}

	/**
	 * Method to getting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@Override
	public Participant getParticipantById(Integer participantId) {

		try {

			List<Participant> list = jdbcTemplate.query(SQL_FOR_GETTING_PARTICIPANT_BY_ID,
					new BeanPropertyRowMapper<>(Participant.class), participantId);

			return list.isEmpty() ? null : list.get(0);
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * Method to getting participant from db by email and client id
	 * 
	 * @param email,
	 *            clientId
	 * @return ResponseEntity
	 */
	@Override
	public List<Participant> getParticipantByEmailAndClientId(String email, Integer clientId) {

		try {

			List<Participant> list = jdbcTemplate.query(SQL_FOR_GETTING_PARTICIPANT_BY_EMAIL_AND_CLIENT_ID,
					new BeanPropertyRowMapper<>(Participant.class), email, clientId);

			return list.isEmpty() ? null : list;
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * Method to getting participant from db by attribute value
	 * 
	 * @param attributeId,
	 *            attributeValue
	 * @return ResponseEntity
	 */
	@Override
	public List<Participant> getParticipantByAttributeValue(Integer attributeId, String attributeValue) {

		try {

			List<Participant> list = jdbcTemplate.query(SQL_FOR_GETTING_PARTICIPANT_BY_ATTRIBUTE_VALUE,
					new BeanPropertyRowMapper<>(Participant.class), attributeId, attributeValue);

			return list.isEmpty() ? null : list;
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * Method to getting participant from database by client id
	 * 
	 * @param clientId
	 * @return 
	 */
	@Override
	public List<Participant> selectClientAllParticipants(Integer cliectId) {
		
		List<Participant> participantList = new ArrayList<>();
		try {

			participantList = jdbcTemplate.query(SELECT_CLIENT_ALL_PARTICIPANTS,
					new BeanPropertyRowMapper<>(Participant.class), cliectId);

			return participantList.isEmpty() ? null : participantList;
		}

		catch (Exception e) {

			LOGGER.debug(e.getMessage());
			return participantList;
		}
	}
}
