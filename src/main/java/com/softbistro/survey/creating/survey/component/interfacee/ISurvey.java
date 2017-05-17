package com.softbistro.survey.creating.survey.component.interfacee;

import java.util.List;

import com.softbistro.survey.creating.survey.component.entity.Group;
import com.softbistro.survey.creating.survey.component.entity.Survey;
import com.softbistro.survey.response.Response;

public interface ISurvey {

	/**
	 * Writing new survey into database.
	 * 
	 * @param survey
	 *            - parsed JSON with information about survey.
	 * @return Response
	 */
	public Response createSurvey(Survey survey);

	/**
	 * Update name of survey in database
	 * 
	 * @param newNameOfSurve
	 * @param surveyId
	 *            - id of survey that will be changed
	 */
	public Response updateOfSurvey(Survey survey);

	/**
	 * Get all surveys from one client
	 * 
	 * @param clientId
	 * @return
	 */
	public Response getAllSurveysOfClient(Integer clientId);

	/**
	 * Get all groups that has client
	 * 
	 * @param clientId
	 * @return
	 */
	public Response getGroupsClient(Integer clientId);

	/**
	 * Add groups of participant that will be in survey
	 * 
	 * @param groups
	 * @return
	 */
	public Response addGroupsToSurvey(List<Group> groups);

	/**
	 * Get all groups that has survey
	 * 
	 * @param surveyId
	 */
	public Response getGroupsSurvey(Integer surveyId);

	/**
	 * 
	 * Delete survey from database
	 * 
	 * @param surveyId
	 */
	public Response deleteSurvey(Integer surveyId);

}