package com.survey.softbistro.startapp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softbistro.survey.question.components.entity.QuestionSection;
import com.softbistro.survey.question.components.service.QuestionSectionDao;
import com.softbistro.survey.startapp.SurveySoftBistroApplication;

/**
 * Integration test for question section dao
 * 
 * @author cortes
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SurveySoftBistroApplication.class)
@WebAppConfiguration
@Transactional
@Rollback
public class QuestionSectionIntegrationTest {

	@Autowired
	private QuestionSectionDao questionSectionService;

	private QuestionSection questionSectionTest;

	private Integer surveyId;

	@Before
	public void setUp() {

		surveyId = Integer.MAX_VALUE;

		questionSectionTest = new QuestionSection();
		questionSectionTest.setClientId(Integer.MAX_VALUE);
		questionSectionTest.setSectionName("sectionName");
		questionSectionTest.setDescriptionShort("descriptionShort");
		questionSectionTest.setDescriptionLong("descrioptionLong");
	}

	/**
	 * Test of save question section to database
	 */
	@Test
	public void saveQuestionSectionTest() {

		Integer id = questionSectionService.setQuestionSection(questionSectionTest);
		assertEquals(questionSectionService.getQuestionSectionById(id).getClientId(),
				questionSectionTest.getClientId());
	}

	/**
	 * Test of update question section
	 */
	@Test
	public void updateQuestionSectionTest() {

		Integer id = questionSectionService.setQuestionSection(questionSectionTest);

		questionSectionTest.setClientId(100);
		questionSectionService.updateQuestionSection(questionSectionTest, id);

		assertEquals(questionSectionService.getQuestionSectionById(id).getClientId(),
				questionSectionTest.getClientId());
	}

	/**
	 * Test of delete question section
	 */
	@Test
	public void deleteQuestionSectionTest() {

		Integer id = questionSectionService.setQuestionSection(questionSectionTest);
		questionSectionService.deleteQuestionSection(id);
		assertEquals(questionSectionService.getQuestionSectionById(id), null);
	}

	/**
	 * Test of get question sections by client id
	 */
	@Test
	public void getByClientIdQuestionSectionTest() {

		questionSectionService.setQuestionSection(questionSectionTest);
		assertEquals(questionSectionService.getQuestionSectionByClientId(questionSectionTest.getClientId()).size(), 1);
	}

	/**
	 * Test of add record to table "connect_question_section_survey" of database
	 */
	@Test
	public void addQuestionSectionToSurveyTest() {

		Integer id = questionSectionService.setQuestionSection(questionSectionTest);
		questionSectionService.addQuestionSectionToSurvey(id, surveyId);
		assertEquals(questionSectionService.getQuestionSectionBySurveyId(surveyId).size(), 1);
	}

	/**
	 * Test delete record from table "connect_question_section_survey"
	 */
	@Test
	public void deleteQuestionSectionFromSurveyTest() {

		Integer id = questionSectionService.setQuestionSection(questionSectionTest);
		questionSectionService.addQuestionSectionToSurvey(id, surveyId);
		assertEquals(questionSectionService.getQuestionSectionBySurveyId(surveyId).size(), 1);
		questionSectionService.deleteQuestionSectionFromSurvey(id, surveyId);
		assertEquals(questionSectionService.getQuestionSectionBySurveyId(surveyId).size(), 0);
	}
}