package com.softbistro.survey.imports.system.services;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbistro.survey.imports.system.importt.csv.CSVImport;
import com.softbistro.survey.imports.system.interfaces.IImportSurvey;

/**
 * Major distributor import file<br>
 * coordinate files to services, that can work with this file format
 * 
 * @author zviproject
 *
 */
@Service
public class ImportSurveyService {

	@Autowired
	private CSVImport csvImport;

	/**
	 * Select service for working with file by format
	 * 
	 * @param request
	 * @param clientId
	 */
	public void importFile(HttpServletRequest request, Integer clientId) {

		try {
			Part filePart = request.getPart("file");

			String fullFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String format = FilenameUtils.getExtension(fullFileName);

			IImportSurvey iImportSurvey = selectImportServiceRealization(format);

			iImportSurvey.fromFile(filePart, clientId);

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private IImportSurvey selectImportServiceRealization(String format) {

		switch (format.toUpperCase()) {
		case "CSV":
			return csvImport;

		default:
			return null;
		}
	}

}
