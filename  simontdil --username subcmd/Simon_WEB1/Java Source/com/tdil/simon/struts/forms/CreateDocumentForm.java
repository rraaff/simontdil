package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.DocumentValidation;
import com.tdil.simon.actions.validations.ParagraphValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.actions.validations.VersionValidation;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;

public class CreateDocumentForm extends ActionForm implements TransactionalActionWithValue {

	private static final long serialVersionUID = 8990859185854581118L;

	private String operation;
	private int step;
	
	// tmp
	private boolean isInNegotiation = false;
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
	private String versionStatus = Version.DRAFT;
	
	// Parrafos
	private int paragraph = 0;
	private String paragraphTexts[] = new String[100];
	private boolean paragraphStatus[] = new boolean[100];
	private int paragraphIds[] = new int[100];
	
	private String goToParagraph;
	
	// Consolidation
	private boolean consolidated = false;
	private String consolidateText;
	
	private static List<MonthOption> allMonths;
	private static List<DayOption> allDays;
	
	static {
		allMonths = new ArrayList<MonthOption>();
		allMonths.add(new MonthOption("1", "Enero"));
		allMonths.add(new MonthOption("2", "Febrero"));
		allMonths.add(new MonthOption("3", "Marzo"));
		allMonths.add(new MonthOption("4", "Abril"));
		allMonths.add(new MonthOption("5", "Mayo"));
		allMonths.add(new MonthOption("6", "Junio"));
		allMonths.add(new MonthOption("7", "Julio"));
		allMonths.add(new MonthOption("8", "Agosto"));
		allMonths.add(new MonthOption("9", "Septiembre"));
		allMonths.add(new MonthOption("10", "Octubre"));
		allMonths.add(new MonthOption("11", "Noviembre"));
		allMonths.add(new MonthOption("12", "Diciembre"));
		
		allDays = new ArrayList<DayOption>(31);
		for (int i = 1; i <= 31; i++) {
			allDays.add(new DayOption(String.valueOf(i)));
		}
	}
	
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
		this.documentType = null;
		this.introduction= null;
		this.paragraph = 0;
		this.paragraphTexts = new String[100];
		this.paragraphStatus = new boolean[100];
		this.paragraphIds = new int[100];
		this.consolidated = false;
		this.consolidateText= null;
		this.principal = false;
		this.versionStatus = Version.DRAFT;
		this.isInNegotiation = false;
	}
	
	public List<MonthOption> getMonths() {
		return allMonths;
	}
	
	public List<String> getAllParagraphNumbers() {
		List<String> result = new ArrayList<String>();
		int index = 0;
		while(!StringUtils.isEmptyOrWhitespaceOnly(paragraphTexts[index])) {
			index = index + 1;
			result.add(String.valueOf(index));
		}
		return result;
	}
	
	public List<DayOption> getDays() {
		return allDays;
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
	
	public String getLimitedIntro() {
		if (this.introduction.length() > 100) {
			return this.getIntroduction().substring(0, 100) + "...";
		} else {
			return this.getIntroduction();
		}
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
		if (this.isPrincipal()) {
			DocumentDAO.markNotPrincipal(this.isDocumentTypeOne(), this.isDocumentTypeTwo());
		}
		if (this.getDocumentId() == 0) {
			Document document = new Document();
			fillDocument(document);
			this.setDocumentId(DocumentDAO.insertDocument(document));
		} else {
			Document document = DocumentDAO.getDocument(this.getDocumentId());
			fillDocument(document);
			DocumentDAO.updateDocument(document);
			if (this.isInNegotiation && this.getVersionId() == 0) {
				VersionDAO.updateVersionUnderNegotiationToConsolidatedForDocument(this.getDocumentId());
			}
		}
		Version version;
		if (this.getVersionId() == 0) {
			version = new Version();
			version.setNumber(this.version);
			version.setStatus(this.getVersionStatus());
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
		if (Version.IN_SIGN.equals(this.versionStatus)) {
			version.setStatus(Version.IN_SIGN);
			Site site = Site.getDELEGATE_SITE();
			site.setStatus(Site.IN_SIGN);
			SiteDAO.updateSite(site);
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			version.setUpToCommentDate(dateFormat.parse(this.getLimitObservationsDay() + "/" + this.getLimitObservationsMonth() + "/" + this.getLimitObservationsYear()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public ValidationError validateStep1(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateTitle(this.title, validation);
		VersionValidation.validateName(this.versionName, validation);
		//DocumentValidation.validateIntroduction(this.introduction, validation);
		//this.upToCommentDateDate = VersionValidation.validateUpToCommentDate(this.upToCommentDate, validation);
		boolean typeOneBoolean = this.isDocumentTypeOne();
		boolean typeTwoBoolean = this.isDocumentTypeTwo();
		if (!typeOneBoolean && !typeTwoBoolean) {
			validation.setFieldError("typeOne", "typeOne" + "." + ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
		}
		return validation;
	}
	public ValidationError validateStep2(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateIntroduction(this.introduction, validation);
		return validation;
	}
	
	public ActionMessages validateStep1() {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateTitle(this.title, validation);
		VersionValidation.validateName(this.versionName, validation);
		DocumentValidation.validateIntroduction(this.introduction, validation);
		//this.upToCommentDateDate = VersionValidation.validateUpToCommentDate(this.upToCommentDate, validation);
		boolean typeOneBoolean = this.isDocumentTypeOne();
		boolean typeTwoBoolean = this.isDocumentTypeTwo();
		if (!typeOneBoolean && !typeTwoBoolean) {
			validation.setFieldError("typeOne", ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
		}
		return validation.asMessages();
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
			this.isInNegotiation = Version.IN_NEGOTIATION.equals(version.getStatus());
			if (this.isInNegotiation) {
				this.versionId = version.getId();
				this.version = version.getNumber();
				this.versionName = version.getName();
			} else {
				this.versionId = 0;
				this.version = version.getNumber() + 1;
				this.versionName = null;
			}
		}
		this.versionStatus = version.getStatus();
		this.title = document.getTitle();
		Calendar cal = Calendar.getInstance();
		cal.setTime(version.getUpToCommentDate());
		this.limitObservationsDay= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		this.limitObservationsMonth= String.valueOf(cal.get(Calendar.MONTH) + 1);;
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

	public int getCurrentParagraphId() {
		return this.paragraphIds[this.paragraph];
	}

	public String getVersionStatus() {
		return versionStatus;
	}

	public void setVersionStatus(String versionStatus) {
		this.versionStatus = versionStatus;
	}
	
	public boolean getVersionNegotiated() {
		return Version.IN_NEGOTIATION.equals(this.getVersionStatus());
	}

	public boolean isInNegotiation() {
		return isInNegotiation;
	}
	
	public boolean getIsInNegotiation() {
		return isInNegotiation;
	}

	public void setInNegotiation(boolean isInNegotiation) {
		this.isInNegotiation = isInNegotiation;
	}

	public ValidationError validateCurrentParagraph(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		ParagraphValidation.validateParagraphText(this.getParagraphText(), "paragraphText", validation);
		return validation;
	}
	
	public ValidationError validateCurrentParagraphForBack(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		if (StringUtils.isEmptyOrWhitespaceOnly(this.paragraphTexts[this.paragraph + 1])) {
			if (StringUtils.isEmptyOrWhitespaceOnly(this.paragraphTexts[this.paragraph])) {
				return validation;
			}
		}
		ParagraphValidation.validateParagraphText(this.getParagraphText(), "paragraphText", validation);
		return validation;
	}

	public ValidationError validateConsolidation(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateConsolidation(this.getConsolidateText(), validation);
		return validation;
	}

	public String getGoToParagraph() {
		return goToParagraph;
	}

	public void setGoToParagraph(String goToParagraph) {
		this.goToParagraph = goToParagraph;
	}
}
