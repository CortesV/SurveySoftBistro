package com.softbistro.survey.question.components.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.softbistro.survey.question.components.entity.QuestionSection;

/**
 * Interface for question section entity
 * 
 * @author af150416
 *
 */
public interface IQuestionSection {

	/**
	 * Method for creating QuestionSection
	 * 
	 * @param questionSection
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> setQuestionSection(QuestionSection questionSection);

	/**
	 * Method for updating QuestionSection
	 * 
	 * @param questionSection
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> updateQuestionSection(QuestionSection questionSection, Integer questionSectionId);

	/**
	 * Method for deleting QuestionSection from db by id
	 * 
	 * @param questionSectionId
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> deleteQuestionSection(Integer questionSectionId);

	/**
	 * Method to getting QuestionSection from db by id
	 * 
	 * @param questionSectionId
	 * @return ResponseEntity
	 */
	public ResponseEntity<QuestionSection> getQuestionSectionById(Integer questionSectionId);

	/**
	 * Method to getting QuestionSection from db by clientId
	 * 
	 * @param clientId
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<QuestionSection>> getQuestionSectionByClientId(Integer clientId);
	
	/**
	 * Method to getting QuestionSection from db by surveyId
	 * 
	 * @param surveyId
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<QuestionSection>> getQuestionSectionBySurveyId(Integer surveyId);
	
	/**
	 * Method for adding QuestionSection to survey
	 * 
	 * @param questionSection id, survey id
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> addQuestionSectionToSurvey(Integer questionSectionId, Integer surveyId);
	

	/**
	 * Method for deleting QuestionSection from survey
	 * 
	 * @param questionSectionId, survey id
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> deleteQuestionSectionFromSurvey(Integer questionSectionId, Integer surveyId);
}
