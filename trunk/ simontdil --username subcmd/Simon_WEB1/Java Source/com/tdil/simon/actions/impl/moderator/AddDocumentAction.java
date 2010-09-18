package com.tdil.simon.actions.impl.moderator;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.DocumentValidation;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.ParagraphValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.actions.validations.VersionValidation;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
/**
 * @deprecated
 * @author mgodoy
 *
 */
public class AddDocumentAction extends AbstractAction implements TransactionalAction {

	private String title;
	private String introduction;
	private String versionName;
	private String upToCommentDate;
	private Date upToCommentDateDate;
	private String typeOne;
	private boolean typeOneBoolean;
	private String typeTwo;
	private boolean typeTwoBoolean;
	
	private String consolidated;
	private boolean consolidatedBoolean;
	private String consolidatedComment;
	
	private TreeMap<Integer, String> paragraphs = new TreeMap<Integer, String>();
	private TreeMap<Integer, Boolean> paragraphsDeleted = new TreeMap<Integer, Boolean>();
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		// validar que no exista el documento con el mismo titulo
		Document document = new Document();
		document.setTitle(this.title);
		document.setIntroduction(this.introduction);
		document.setDeleted(false);
		int docId = DocumentDAO.insertDocument(document);
		generateVersion(docId, 1);	
	}

	protected void generateVersion(int docId, int versionNumber) throws SQLException {
		Version version = new Version();
		version.setDocumentId(docId);
		version.setName(this.versionName);
		version.setNumber(versionNumber);
		if (this.consolidatedBoolean) {
			version.setStatus(Version.CONSOLIDATED);
			version.setDescription(this.consolidatedComment);
		} else {
			version.setStatus(Version.DRAFT);
			version.setDescription("");
		}
		version.setUpToCommentDate(upToCommentDateDate);
		version.setDeleted(false);
		int versionId = VersionDAO.insertVersion(version);
		for (Map.Entry<Integer, String> par : this.paragraphs.entrySet()) {
			Paragraph paragraph = new Paragraph();
			paragraph.setVersionId(versionId);
			paragraph.setParagraphNumber(par.getKey());
			paragraph.setParagraphText(par.getValue());
			paragraph.setDeleted(this.paragraphsDeleted.get(par.getKey()));
			ParagraphDAO.insertParagraph(paragraph);
		}
	}

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
		this.title = DocumentValidation.validateTitle(this.title, validation);
		this.introduction = DocumentValidation.validateIntroduction(this.introduction, validation);
		this.upToCommentDateDate = VersionValidation.validateUpToCommentDate(this.upToCommentDate, validation);
		this.typeOneBoolean = FieldValidation.validateBoolean(this.typeOne, validation);
		this.typeTwoBoolean = FieldValidation.validateBoolean(this.typeTwo, validation);
		if (!this.typeOneBoolean && !this.typeTwoBoolean) {
			validation.setFieldError("typeOne", ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
		}
		this.consolidatedBoolean = FieldValidation.validateBoolean(this.consolidated, validation);
		this.consolidatedComment = VersionValidation.validateConsolidatedComment(this.consolidatedComment, this.consolidatedBoolean, validation);
		ParagraphValidation.validateParagraphs(this.paragraphs, validation);
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.MODERATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.title = req.getParameter("title");
		this.introduction = req.getParameter("introduction");
		this.versionName = req.getParameter("versionName");
		this.upToCommentDate = req.getParameter("upToCommentDate");
		this.typeOne = req.getParameter("typeOne");
		this.typeTwo = req.getParameter("typeTwo");
		this.consolidated = req.getParameter("consolidated");
		this.consolidatedComment = req.getParameter("consolidatedComment");
		int paragraphIndex = 1;
		String text = null;
		while(!StringUtils.isEmptyOrWhitespaceOnly(text = req.getParameter("paragraph" + paragraphIndex))) {
			this.paragraphs.put(paragraphIndex, text);
			this.paragraphsDeleted.put(paragraphIndex, FieldValidation.validateBoolean(req.getParameter("paragraphDeleted" + paragraphIndex), new ValidationError()));
			paragraphIndex = paragraphIndex + 1;
		}
	}

}
