package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.DocumentValidation;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.actions.validations.VersionValidation;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;

public class CreateDocumentForm extends ActionForm implements TransactionalActionWithValue {

	private static final long serialVersionUID = 8990859185854581118L;

	private String operation;
	private int step;
	
	// tmp
	private int temporaryVersionId;
	
	// Saved
	private int documentId; // If 0, create a document
	private int versionId; // if not 0, edit, if 0, create
	
	// Primer Paso
	private String title;
	private int version = 1;
	private String versionName;
	private String limitObservationsDay;
	private String limitObservationsMonth;
	private String limitObservationsYear = "2010";
	private boolean principal;
	private String documentType;
	
	// Segundo paso
	private String introduction;
	
	// Parrafos
	private int paragraph = 0;
	private String paragraphTexts[] = new String[100];
	private boolean paragraphStatus[] = new boolean[100];
	private int paragraphIds[] = new int[100];
	
	// Consolidation
	private boolean consolidated = false;
	private String consolidateText;
	
	public void reset() {
		this.operation = null;
		this.step = 0;
		this.documentId = 0;
		this.versionId = 0;
		this.title = null;
		this.version = 1;
		this.versionName = null;
		this.limitObservationsDay= null;
		this.limitObservationsMonth= null;
		this.limitObservationsYear= null;
		this.documentType = null;
		this.introduction= null;
		this.paragraph = 0;
		this.paragraphTexts = new String[100];
		this.paragraphStatus = new boolean[100];
		this.paragraphIds = new int[100];
		this.consolidated = false;
		this.consolidateText= null;
		this.principal = false;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getLimitObservationsDay() {
		return limitObservationsDay;
	}
	public void setLimitObservationsDay(String limitObservationsDay) {
		this.limitObservationsDay = limitObservationsDay;
	}
	public String getLimitObservationsMonth() {
		return limitObservationsMonth;
	}
	public void setLimitObservationsMonth(String limitObservationsMonth) {
		this.limitObservationsMonth = limitObservationsMonth;
	}
	public String getLimitObservationsYear() {
		return limitObservationsYear;
	}
	public void setLimitObservationsYear(String limitObservationsYear) {
		this.limitObservationsYear = limitObservationsYear;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public boolean getParagraphHidden() {
		return paragraphStatus[paragraph];
	}
	public void setParagraphHidden(boolean hidden) {
		this.paragraphStatus[paragraph] = hidden;
	}
	
	public String getParagraphText() {
		return paragraphTexts[paragraph];
	}
	public void setParagraphText(String paragraphText) {
		this.paragraphTexts[paragraph] = paragraphText;
	}
	
	public int getParagraphForDisplay() {
		return paragraph + 1;
	}
	
	public int getParagraph() {
		return paragraph;
	}
	public void setParagraph(int paragraph) {
		this.paragraph = paragraph;
	}
	public String[] getParagraphTexts() {
		return paragraphTexts;
	}
	public void setParagraphTexts(String[] paragraphTexts) {
		this.paragraphTexts = paragraphTexts;
	}
	public boolean[] getParagraphStatus() {
		return paragraphStatus;
	}
	public void setParagraphStatus(boolean[] paragraphStatus) {
		this.paragraphStatus = paragraphStatus;
	}
	
	public String getBackDisabled() {
		if (this.paragraph == 0) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getNextDisabled() {
		if (StringUtils.isEmptyOrWhitespaceOnly(this.paragraphTexts[this.paragraph + 1])) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getLast() {
		if (StringUtils.isEmptyOrWhitespaceOnly(this.paragraphTexts[this.paragraph + 1])) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getAddOrSave() {
		if (StringUtils.isEmptyOrWhitespaceOnly(this.paragraphTexts[this.paragraph ])) {
			return ApplicationResources.getMessage("createDocument.paragraphs.add");
		} else {
			return ApplicationResources.getMessage("createDocument.paragraphs.save");
		}
	}
	
	public String getHideOrUnhide() {
		if (this.getParagraphHidden()) {
			return ApplicationResources.getMessage("createDocument.paragraphs.unhide");
		} else {
			return ApplicationResources.getMessage("createDocument.paragraphs.hide");
		}
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
	public List getPreviewParagraphs() {
		List result = new ArrayList();
		int index = 0;
		String paragraphText = this.paragraphTexts[index];
		while (!StringUtils.isEmptyOrWhitespaceOnly(paragraphText)) {
			result.add(String.valueOf(index + 1) + ". " + paragraphText);
			index = index + 1;
			paragraphText = this.paragraphTexts[index];
		}
		return result;
	}
	
	public String getVersionNumber() {
		return String.valueOf(this.version) + ".0";
	}
	
	public Object save() throws SQLException, ValidationException {
		return TransactionProvider.executeInTransaction(this, this);
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		DocumentDAO.markNotPrincipal(this.isDocumentTypeOne(), this.isDocumentTypeTwo());
		if (this.getDocumentId() == 0) {
			Document document = new Document();
			fillDocument(document);
			this.setDocumentId(DocumentDAO.insertDocument(document));
		} else {
			Document document = DocumentDAO.getDocument(this.getDocumentId());
			fillDocument(document);
			DocumentDAO.updateDocument(document);
		}
		Version version;
		if (this.getVersionId() == 0) {
			version = new Version();
//			int maxVersionForDocument = VersionDAO.getMaxVersionFor(this.getDocumentId());
//			version.setNumber(maxVersionForDocument + 1);
			version.setNumber(this.version);
			version.setStatus(Version.DRAFT);
			fillVersion(version);
			version.setNumber(this.version);
			this.setVersionId(VersionDAO.insertVersion(version));
			insertParagraphs();
		} else {
			version = VersionDAO.getVersion(this.getVersionId());
			fillVersion(version);
			version.setNumber(this.version);
			VersionDAO.updateVersion(version);
			updateParagraphs();
		}
		if (this.version > 1) {
			Version prev = VersionDAO.getVersionForDocumentAndNumber(this.documentId, this.version - 1);
			if (prev != null) {
				prev.setHasDraft(true);
				VersionDAO.updateVersion(prev);
			}
		}
		return null;
	}
	private void updateParagraphs() throws SQLException {
		List paragraphs = ParagraphDAO.selectAllParagraphsFor(this.getVersionId());
		int index = 0;
		for (Object object : paragraphs) {
			Paragraph paragraph = (Paragraph)object;
			paragraph.setParagraphText(this.paragraphTexts[index]);
			paragraph.setDeleted(this.paragraphStatus[index]);
			index = index + 1;
			ParagraphDAO.updateParagraph(paragraph);
		}
		String paragraphText = this.paragraphTexts[index];
		while (!StringUtils.isEmptyOrWhitespaceOnly(paragraphText)) {
			Paragraph paragraph = new Paragraph();
			paragraph.setVersionId(this.getVersionId());
			paragraph.setParagraphNumber(index + 1);
			paragraph.setParagraphText(paragraphText);
			paragraph.setDeleted(this.paragraphStatus[index]);
			this.paragraphIds[index] = ParagraphDAO.insertParagraph(paragraph);
			index = index + 1;
			paragraphText = this.paragraphTexts[index];
		}
	}
	private void insertParagraphs() throws SQLException {
		int index = 0;
		String paragraphText = this.paragraphTexts[index];
		while (!StringUtils.isEmptyOrWhitespaceOnly(paragraphText)) {
			Paragraph paragraph = new Paragraph();
			paragraph.setVersionId(this.getVersionId());
			paragraph.setParagraphNumber(index + 1);
			paragraph.setParagraphText(paragraphText);
			paragraph.setDeleted(this.paragraphStatus[index]);
			this.paragraphIds[index] = ParagraphDAO.insertParagraph(paragraph);
			index = index + 1;
			paragraphText = this.paragraphTexts[index];
		}
		
	}
	private void fillVersion(Version version) throws SQLException {
		version.setDocumentId(this.getDocumentId());
		version.setName(this.getVersionName());
		version.setUpToCommentDate(null);
		if (this.consolidated) {
			version.setStatus(Version.CONSOLIDATED);
			version.setDescription(this.getConsolidateText());
		}
		version.setDeleted(false);
	}
	private void fillDocument(Document document) {
		ValidationError validation = new ValidationError();
		document.setTitle(this.title);
		document.setIntroduction(this.introduction);
		document.setPrincipal(this.isPrincipal());
		document.setTypeOne(this.isDocumentTypeOne());
		document.setTypeTwo(this.isDocumentTypeTwo());
		document.setDeleted(false);
		// TODO lanzar errores
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//		if (this.getStep() == 1) {
//			ValidationError validation = new ValidationError();
//			DocumentValidation.validateTitle(this.title, validation);
//			VersionValidation.validateName(this.versionName, validation);
//			DocumentValidation.validateIntroduction(this.introduction, validation);
//			//this.upToCommentDateDate = VersionValidation.validateUpToCommentDate(this.upToCommentDate, validation);
//			boolean typeOneBoolean = FieldValidation.validateBoolean(this.typeOne, validation);
//			boolean typeTwoBoolean = FieldValidation.validateBoolean(this.typeTwo, validation);
//			if (!typeOneBoolean && !typeTwoBoolean) {
//				validation.setFieldError("typeOne", ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
//			}
//			return validation.asActionsErrors();
//		}
		return null;
	}
	
	public ActionErrors validateStep1() {
			ValidationError validation = new ValidationError();
			DocumentValidation.validateTitle(this.title, validation);
			VersionValidation.validateName(this.versionName, validation);
			DocumentValidation.validateIntroduction(this.introduction, validation);
			//this.upToCommentDateDate = VersionValidation.validateUpToCommentDate(this.upToCommentDate, validation);
			return validation.asActionsErrors();
		}
	public String getConsolidateText() {
		return consolidateText;
	}
	public void setConsolidateText(String consolidateText) {
		this.consolidateText = consolidateText;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public boolean isConsolidated() {
		return consolidated;
	}
	public void setConsolidated(boolean consolidated) {
		this.consolidated = consolidated;
	}
	
	public boolean isDocumentTypeOne() {
		return "typeOne".equals(this.getDocumentType());
	}
	
	public boolean isDocumentTypeTwo() {
		return "typeTwo".equals(this.getDocumentType());
	}

	public void initWith(int versionID) throws SQLException {
		Version version = VersionDAO.getVersion(versionID);
		Document document = DocumentDAO.getDocument(version.getDocumentId());
		this.documentId = document.getId();
		this.setPrincipal(document.isPrincipal());
		if (Version.DRAFT.equals(version.getStatus())) {
			this.versionId = versionID;
			this.versionName = version.getName();
			this.version = version.getNumber();
		} else {
			this.versionId = 0;
			this.versionName = null;
			this.version = version.getNumber() + 1;
		}
		this.title = document.getTitle();
		this.limitObservationsDay= null;
		this.limitObservationsMonth= null;
		this.limitObservationsYear= null;
		this.documentType = document.isTypeOne() ? "typeOne" : "typeTwo";
		this.introduction= document.getIntroduction();
		this.paragraph = 0;
		this.paragraphTexts = new String[100];
		this.paragraphStatus = new boolean[100];
		this.paragraphIds = new int[100];
		List<Paragraph> paragraphs = ParagraphDAO.selectAllParagraphsFor(versionID);
		for (Paragraph p : paragraphs) {
			paragraphTexts[p.getParagraphNumber() - 1] = p.getParagraphText();
			paragraphStatus[p.getParagraphNumber() - 1] = p.isDeleted();
			paragraphIds[p.getParagraphNumber() - 1] = p.getId();
		}
		this.consolidated = false;
		this.consolidateText= null;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public int getTemporaryVersionId() {
		return temporaryVersionId;
	}

	public void setTemporaryVersionId(int temporaryVersionId) {
		this.temporaryVersionId = temporaryVersionId;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}
