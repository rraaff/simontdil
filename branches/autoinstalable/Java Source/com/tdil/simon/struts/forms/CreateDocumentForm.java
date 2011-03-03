package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
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
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.SystemConfig;

public class CreateDocumentForm extends ActionForm implements TransactionalActionWithValue {

	private static final long serialVersionUID = 8990859185854581118L;

	private String operation;
	private int step;
	
	// tmp
	private boolean isInNegotiation = false;
	private int temporaryVersionId;
	
	// live preview
	private boolean livePreview = false;

	// designer
	private boolean designer = false;
	
	// portugues
	private boolean portugues = false;
	private int introductoryParagraphs = 0;
	private int textParagraphs = 0;
	
	// Saved
	private int documentId; // If 0, create a document
	private int versionId; // if not 0, edit, if 0, create
	
	// Primer Paso
	private String title;
	private int version = 1;
	private String versionName;
	//private String limitObservationsDay;
	//private String limitObservationsMonth;
	//private String limitObservationsYear = "2010";
	
	// TODO mejora
	private String limitObservations;
	
	public String getLimitObservations() {
		return limitObservations;
	}

	public void setLimitObservations(String limitObservations) {
		this.limitObservations = limitObservations;
	}

	private boolean principal;
	private String documentType;
	
	// Segundo paso
	private String introduction;
	private String versionStatus = Version.DRAFT;
	
	// Parrafos
	private int paragraph = 0;
	private String paragraphTexts[] = new String[1000];
	private String paragraphDetails[] = new String[1000];
	private boolean paragraphStatus[] = new boolean[1000];
	private int paragraphIds[] = new int[1000];
	
	private String goToParagraph;
	
	// Consolidation
	private boolean consolidated = false;
	private String consolidateText;
	
	private String designerText;
	
	private static List<MonthOption> allMonths;
	private static List<DayOption> allDays;
	
	private String destination;
	private String detail;
	private String append;
	private String newParagraphText;
	
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
	
	public boolean getIntroductoryParagraph() {
		return this.getParagraph() < Paragraph.INTRODUCTION_LIMIT;
	}
	
	public void reset() {
		this.operation = null;
		this.step = 0;
		this.documentId = 0;
		this.versionId = 0;
		this.title = null;
		this.version = 1;
		this.versionName = null;
		//this.limitObservationsDay= null;
		//this.limitObservationsMonth= null;
		this.limitObservations = null;
		this.documentType = null;
		this.introduction= null;
		this.paragraph = 0;
		this.paragraphTexts = new String[1000];
		this.paragraphStatus = new boolean[1000];
		this.paragraphIds = new int[1000];
		this.consolidated = false;
		this.consolidateText= null;
		this.principal = false;
		this.versionStatus = Version.DRAFT;
		this.isInNegotiation = false;
		this.portugues = false;
	}
	
	public List<MonthOption> getMonths() {
		return allMonths;
	}
	
	public List<Paragraph> getAllParagraphNumbers() {
		List<Paragraph> result = new ArrayList<Paragraph>();
		int index = 0;	
		while(!StringUtils.isEmpty(paragraphTexts[index])) {
			Paragraph p = new Paragraph();
			index = index + 1;
			p.setParagraphNumber(index);
			result.add(p);
		}
		index = Paragraph.INTRODUCTION_LIMIT;
		while(!StringUtils.isEmpty(paragraphTexts[index])) {
			Paragraph p = new Paragraph();
			index = index + 1;
			p.setParagraphNumber(index);
			result.add(p);
		}
		return result;
	}
	
	public List<String> getAllParagraphs() {
		List<String> result = new ArrayList<String>();
		int index = 0;	
		while(!StringUtils.isEmpty(paragraphTexts[index])) {
			Paragraph p = new Paragraph();
			result.add(String.valueOf(index + 1));
			result.add(Paragraph.GetParagraphNumberForDisplay(index));
			index = index + 1;
		}
		index = Paragraph.INTRODUCTION_LIMIT;
		while(!StringUtils.isEmpty(paragraphTexts[index])) {
			Paragraph p = new Paragraph();
			result.add(String.valueOf(index + 1));
			result.add(Paragraph.GetParagraphNumberForDisplay(index));
			index = index + 1;
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
//	public String getLimitObservationsDay() {
//		return limitObservationsDay;
//	}
//	public void setLimitObservationsDay(String limitObservationsDay) {
//		this.limitObservationsDay = limitObservationsDay;
//	}
//	public String getLimitObservationsMonth() {
//		return limitObservationsMonth;
//	}
//	public void setLimitObservationsMonth(String limitObservationsMonth) {
//		this.limitObservationsMonth = limitObservationsMonth;
//	}
//	public String getLimitObservationsYear() {
//		return limitObservationsYear;
//	}
//	public void setLimitObservationsYear(String limitObservationsYear) {
//		this.limitObservationsYear = limitObservationsYear;
//	}
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
	
	public void setCurrentDetail(String detail) {
		this.paragraphDetails[paragraph] = detail;
	}
	public String getCurrentDetail() {
		return this.paragraphDetails[paragraph];
	}
	
	public String getParagraphForDisplay() {
		return Paragraph.GetParagraphNumberForDisplay(paragraph);
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
		if (this.paragraph == 0 || this.paragraph == Paragraph.INTRODUCTION_LIMIT) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getLast() {
		if (StringUtils.isEmpty(this.paragraphTexts[this.paragraph + 1])) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public String getAddOrSave() {
		if (StringUtils.isEmpty(this.paragraphTexts[this.paragraph ])) {
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
	
	public List getPreviewIntroduction() {
		List result = new ArrayList();
		int index = 0;
		String paragraphText = this.paragraphTexts[index];
		while (!StringUtils.isEmpty(paragraphText)) {
			if (StringUtils.isEmpty(this.paragraphDetails[index])) {
				result.add(Paragraph.GetParagraphNumberForDisplay(index) + ". " + paragraphText);
			} else {
				result.add(Paragraph.GetParagraphNumberForDisplay(index) + " (" + this.paragraphDetails[index]+ "). " + paragraphText);
			}
			index = index + 1;
			paragraphText = this.paragraphTexts[index];
		}
		return result;
	}
	
	public List getPreviewParagraphs() {
		List result = new ArrayList();
		int index = Paragraph.INTRODUCTION_LIMIT;
		String paragraphText = this.paragraphTexts[index];
		while (!StringUtils.isEmpty(paragraphText)) {
			if (StringUtils.isEmpty(this.paragraphDetails[index])) {
				result.add(Paragraph.GetParagraphNumberForDisplay(index) + ". " + paragraphText);
			} else {
				result.add(Paragraph.GetParagraphNumberForDisplay(index) + " (" + this.paragraphDetails[index]+ "). " + paragraphText);
			}
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
		if (!this.isPortugues() && this.isPrincipal()) {
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
			version.setSpanishVersion(!this.isPortugues());
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
		for (int i = 0; i < 1000; i++) {
			String text = this.paragraphTexts[i];
			if (!StringUtils.isEmpty(text)) {
				insertOrUpdateParagrap(i, text, this.paragraphStatus[i], this.paragraphDetails[i], paragraphs, i);
			}
		}
		
//		int index = 0;
//		for (Object object : paragraphs) {
//			Paragraph paragraph = (Paragraph)object;
//			paragraph.setParagraphText(this.paragraphTexts[index]);
//			paragraph.setDeleted(this.paragraphStatus[index]);
//			index = index + 1;
//			ParagraphDAO.updateParagraph(paragraph);
//		}
//		String paragraphText = this.paragraphTexts[index];
//		while (!StringUtils.isEmpty(paragraphText)) {
//			Paragraph paragraph = new Paragraph();
//			paragraph.setVersionId(this.getVersionId());
//			paragraph.setParagraphNumber(index + 1);
//			paragraph.setParagraphText(paragraphText);
//			paragraph.setDeleted(this.paragraphStatus[index]);
//			this.paragraphIds[index] = ParagraphDAO.insertParagraph(paragraph);
//			index = index + 1;
//			paragraphText = this.paragraphTexts[index];
//		}
	}
	
	private void insertOrUpdateParagrap(int paragraphNumber, String text, boolean deleted, String detail, List paragraphs, int position) throws SQLException {
		// TODO Auto-generated method stub
		Paragraph paragraph = getParagraphForPosition(paragraphs, position);
		if (paragraph != null) {
			String pText = paragraph.getParagraphText(); 
			paragraph.setParagraphText(text);
			paragraph.setDeleted(deleted);
			paragraph.setParagraphNumber(paragraphNumber + 1);
			paragraph.setNumberDetail(detail);
			ParagraphDAO.updateParagraph(paragraph);
//			TODO esta cosa no anda asi, no va
//			if (!text.equals(pText)) {
//				TrackChange trackChange = TrackChangeDAO.getLastVersionForDocument(paragraph.getId());
//				try {
//					String diff = ParagraphComparator.compare(RemoveChangesComments.clean(trackChange.getChangeText()), text);
//					trackChange.setChangeText(RemoveChangesComments.removeHtml(diff));
//					TrackChangeDAO.updateTrackChange(trackChange);
//				} catch (TransformerConfigurationException e) {
//					getLog().error(e.getMessage(), e);
//				} catch (IOException e) {
//					getLog().error(e.getMessage(), e);
//				} catch (SAXException e) {
//					getLog().error(e.getMessage(), e);
//				}
//			}
		} else {
			paragraph = new Paragraph();
			paragraph.setVersionId(this.getVersionId());
			paragraph.setParagraphNumber(paragraphNumber + 1);
			paragraph.setParagraphText(text);
			paragraph.setDeleted(deleted);
			paragraph.setNumberDetail(detail);
			int parId = ParagraphDAO.insertParagraph(paragraph);
			this.paragraphIds[paragraphNumber] = parId;
//			TODO esta cosa asi no anda, hay que buscarle otra solucion
//			TrackChange trackChange = new TrackChange();
//			trackChange.setChangeText(text);
//			trackChange.setParagraphId(parId);
//			TrackChangeDAO.insertTrackChange(trackChange);
		}
	}

	private Paragraph getParagraphForPosition(List paragraphs, int position) {
		int id = paragraphIds[position];
		if (id == 0) {
			return null;
		} else {
			for (Object o : paragraphs) {
				Paragraph p = (Paragraph)o;
				if (p.getId() == id) {
					return p;
				}
			}
			return null;
		}
	}

	private Paragraph getParagraphForNumber(List paragraphs, int paragraphNumber) {
		for (Object o : paragraphs) {
			Paragraph p = (Paragraph)o;
			if (p.getParagraphNumber() - 1 == paragraphNumber) {
				return p;
			}
		}
		return null;
	}
	
	public String getLimitObservationsString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = dateFormat.parse(this.getLimitObservations());
			return SystemConfig.getDateFormat().format(date);
		} catch (ParseException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}

	private void insertParagraphs() throws SQLException {
		updateParagraphs();
//		int index = 0;
//		String paragraphText = this.paragraphTexts[index];
//		while (!StringUtils.isEmpty(paragraphText)) {
//			Paragraph paragraph = new Paragraph();
//			paragraph.setVersionId(this.getVersionId());
//			paragraph.setParagraphNumber(index + 1);
//			paragraph.setParagraphText(paragraphText);
//			paragraph.setDeleted(this.paragraphStatus[index]);
//			this.paragraphIds[index] = ParagraphDAO.insertParagraph(paragraph);
//			index = index + 1;
//			paragraphText = this.paragraphTexts[index];
//		}
		
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(CreateDocumentForm.class);
	}
	
	private void fillVersion(Version version) throws SQLException {
		version.setDocumentId(this.getDocumentId());
		version.setName(this.getVersionName());
		version.setUpToCommentDate(null);
		version.setDesignerText(this.getDesignerText());
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
		if (Version.FINAL.equals(this.versionStatus)) {
			version.setStatus(Version.FINAL);
			VersionDAO.updateVersion(version);
			Site site = SiteDAO.getSite(Site.DELEGATE);
			site.setStatus(Site.NORMAL);
			site.setDataId(0);
			SiteDAO.updateSite(site);
			
			Site publicSite = SiteDAO.getSite(Site.PUBLIC);
			publicSite.setDataId(version.getId());
			if (!site.NORMAL.equals(publicSite.getStatus())) {
				publicSite.setStatus(Site.SIGN_SHOW);
			}
			SiteDAO.updateSite(publicSite);
		}
		try {
			// TODO MARCOS version.setUpToCommentDate(dateFormat.parse(this.getLimitObservations()));
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			version.setUpToCommentDate(dateFormat.parse(this.getLimitObservations()));
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
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
	
	public ValidationError validateStep1(ActionMapping mapping, HttpServletRequest request) throws SQLException {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateTitle(this.title, validation);
		VersionValidation.validateName(this.versionName, validation);
		//DocumentValidation.validateIntroduction(this.introduction, validation);
		VersionValidation.validateUpToCommentDate(this.limitObservations, validation);
		boolean typeOneBoolean = this.isDocumentTypeOne();
		boolean typeTwoBoolean = this.isDocumentTypeTwo();
		if (!typeOneBoolean && !typeTwoBoolean) {
			validation.setFieldError("typeOne", "typeOne" + "." + ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
		}
		if (this.isPrincipal()) {
			if (!Site.NORMAL.equals(Site.getDELEGATE_SITE().getStatus())) {
				Document doc = DocumentDAO.getDocumentUnderWork();
				if (doc != null) {
					Version version = VersionDAO.getVersionUnderWork();
					if (version.getId() != this.versionId) {
						if (doc.isTypeOne() && this.isDocumentTypeOne()) {
							validation.setFieldError("principal", "principal" + "." + ValidationErrors.DOCUMENT_IN_NEGOTIATION);
						}
						if (doc.isTypeTwo() && this.isDocumentTypeTwo()) {
							validation.setFieldError("principal", "principal" + "." + ValidationErrors.DOCUMENT_IN_NEGOTIATION);
						}
					}
				}
			}
		}
		return validation;
	}
	public ValidationError validateStep2(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		DocumentValidation.validateIntroduction(this.introduction, validation);
		return validation;
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
		setPortugues(false);
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
		this.designerText = version.getDesignerText();
		this.title = document.getTitle();
		Calendar cal = Calendar.getInstance();
		cal.setTime(version.getUpToCommentDate());
		//this.limitObservationsDay= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		//this.limitObservationsMonth= String.valueOf(cal.get(Calendar.MONTH) + 1);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.limitObservations = dateFormat.format(cal.getTime());
		this.documentType = document.isTypeOne() ? "typeOne" : "typeTwo";
		this.introduction= document.getIntroduction();
		this.paragraph = 0;
		this.paragraphTexts = new String[1000];
		this.paragraphStatus = new boolean[1000];
		this.paragraphIds = new int[1000];
		List<Paragraph> paragraphs = ParagraphDAO.selectAllParagraphsFor(versionID);
		for (Paragraph p : paragraphs) {
			paragraphTexts[p.getParagraphNumber() - 1] = p.getParagraphText();
			paragraphStatus[p.getParagraphNumber() - 1] = p.isDeleted();
			paragraphIds[p.getParagraphNumber() - 1] = p.getId();
			paragraphDetails[p.getParagraphNumber() - 1] = p.getNumberDetail();
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
		return isInNegotiation && !this.isPortugues() && !this.isDesigner();
	}

	public void setInNegotiation(boolean isInNegotiation) {
		this.isInNegotiation = isInNegotiation;
	}
	
	public ValidationError validateCurrentParagraphForLength(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		ParagraphValidation.validateParagraphTextForLength(this.getParagraphText(), "paragraphText", validation);
		ParagraphValidation.validateParagraphDetail(this.getCurrentDetail() == null ? "" : this.getCurrentDetail(), "paragraphDetail", validation);
		return validation;
	} 

	public ValidationError validateCurrentParagraph(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		ParagraphValidation.validateParagraphText(this.getParagraphText(), "paragraphText", validation);
		ParagraphValidation.validateParagraphDetail(this.getCurrentDetail() == null ? "" : this.getCurrentDetail(), "paragraphDetail", validation);
		return validation;
	}
	
	public ValidationError validateCurrentParagraphForBack(ActionMapping mapping, HttpServletRequest request) {
		ValidationError validation = new ValidationError();
		if (StringUtils.isEmpty(this.paragraphTexts[this.paragraph + 1])) {
			if (StringUtils.isEmpty(this.paragraphTexts[this.paragraph])) {
				return validation;
			}
		}
		ParagraphValidation.validateParagraphText(this.getParagraphText(), "paragraphText", validation);
		ParagraphValidation.validateParagraphDetail(this.getCurrentDetail() == null ? "" : this.getCurrentDetail(), "paragraphDetail", validation);
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
	
	public void deleteCurrent() throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				CreateDocumentForm createDocumentForm = CreateDocumentForm.this;
				// No fue grabado, muevo el resto y salvo
				
				if (createDocumentForm.paragraphIds[createDocumentForm.getParagraph()] != 0) {
					ParagraphDAO.deleteParagraph(createDocumentForm.paragraphIds[createDocumentForm.getParagraph()]);
				}
				int i = createDocumentForm.getParagraph();
				for (; !StringUtils.isEmpty(createDocumentForm.paragraphTexts[i]) && i < 1000; i = i + 1) {
					createDocumentForm.paragraphIds[i] = createDocumentForm.paragraphIds[i + 1];
					createDocumentForm.paragraphTexts[i] = createDocumentForm.paragraphTexts[i + 1];
					createDocumentForm.paragraphStatus[i] = createDocumentForm.paragraphStatus[i + 1];
					createDocumentForm.paragraphDetails[i] = createDocumentForm.paragraphDetails[i + 1];
					Paragraph toModify = ParagraphDAO.getParagraph(createDocumentForm.paragraphIds[i]);
					if (toModify != null) {
						toModify.setParagraphNumber(toModify.getParagraphNumber() - 1);
						ParagraphDAO.updateParagraph(toModify);
					}
				}
				if (StringUtils.isEmpty(createDocumentForm.paragraphTexts[createDocumentForm.getParagraph()])) {
					if (createDocumentForm.getParagraph() > 500) {
						createDocumentForm.setParagraph(createDocumentForm.getParagraph() - 1);
					} else {
						if (createDocumentForm.getParagraph() < 500 && createDocumentForm.getParagraph() > 0) {
							createDocumentForm.setParagraph(createDocumentForm.getParagraph() - 1);
						}
					}
				}
			}
		});
	}

	public void addBefore() {
		ArrayList<String> move = new ArrayList<String>();
		ArrayList<Boolean> status = new ArrayList<Boolean>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<String> details = new ArrayList<String>();
		int i = this.getParagraph();
		while (!StringUtils.isEmpty(paragraphTexts[i])) {
			move.add(paragraphTexts[i]);
			status.add(paragraphStatus[i]);
			ids.add(paragraphIds[i]);
			details.add(paragraphDetails[i]);
			i = i + 1;
		}
		int dest = this.getParagraph() + 1;
		for (int index = 0; index < move.size(); index = index + 1) {
			String text = move.get(index);
			Boolean stat = status.get(index);
			Integer id = ids.get(index);
			String detail = details.get(index);
			paragraphTexts[dest] = text;
			paragraphStatus[dest] = stat;
			paragraphIds[dest] = id;
			paragraphDetails[dest] = detail;
			dest = dest + 1;
		}
		paragraphTexts[this.getParagraph()] = "";
		paragraphStatus[this.getParagraph()] = false;
		paragraphIds[this.getParagraph()] = 0;
		paragraphDetails[this.getParagraph()] = "";
	}

	public void addAfter() {
		ArrayList<String> move = new ArrayList<String>();
		ArrayList<Boolean> status = new ArrayList<Boolean>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<String> details = new ArrayList<String>();
		int i = this.getParagraph() + 1;
		while (!StringUtils.isEmpty(paragraphTexts[i])) {
			move.add(paragraphTexts[i]);
			status.add(paragraphStatus[i]);
			ids.add(paragraphIds[i]);
			details.add(paragraphDetails[i]);
			i = i + 1;
		}
		int dest = this.getParagraph() + 2;
		for (int index = 0; index < move.size(); index = index + 1) {
			String text = move.get(index);
			Boolean stat = status.get(index);
			Integer id = ids.get(index);
			String detail = details.get(index);
			paragraphTexts[dest] = text;
			paragraphStatus[dest] = stat;
			paragraphIds[dest] = id;
			paragraphDetails[dest] = detail;
			dest = dest + 1;
		}
		this.setParagraph(this.getParagraph() + 1);
		paragraphTexts[this.getParagraph()] = "";
		paragraphStatus[this.getParagraph()] = false;
		paragraphIds[this.getParagraph()] = 0;
		paragraphDetails[this.getParagraph()] = "";
	}

	public void initForPortuguesWith(int versionID) throws SQLException {
		setPortugues(true);
		setDesigner(false);
		// con esta saco datos base
		Version version = VersionDAO.getVersion(versionID);
		this.paragraphStatus = new boolean[1000];
		this.paragraphTexts = new String[1000];
		String initial = ApplicationResources.getMessage("createDocument.paragraphs.portuguesInit");
		{
			List<Paragraph> paragraphs = ParagraphDAO.selectAllParagraphsFor(versionID);
			for (Paragraph p : paragraphs) {
				paragraphTexts[p.getParagraphNumber() - 1] = initial;
				paragraphStatus[p.getParagraphNumber() - 1] = p.isDeleted();
				if (p.getParagraphNumber() < Paragraph.INTRODUCTION_LIMIT) {
					this.introductoryParagraphs = this.introductoryParagraphs + 1;
				} else {
					this.textParagraphs = this.textParagraphs + 1;
				}
			}
		}
		// Con esta hago la edicion si existe
		Version portuguesVersion = VersionDAO.getPortuguesVersionAnyStatus(versionID);
		// TODO mantendra el mismo doc? hablar con pablo
		Document document = DocumentDAO.getDocument(version.getDocumentId());
		this.documentId = document.getId();
		this.setPrincipal(document.isPrincipal());
		if (portuguesVersion != null) {
			this.versionId = portuguesVersion.getId();
			this.versionName = portuguesVersion.getName();
			this.designerText = portuguesVersion.getDesignerText();
		} else {
			this.designerText = "";
			this.versionId = 0;
			this.versionName = "";
		}
		this.version = version.getNumber();
		this.versionStatus = version.getStatus();
		this.title = document.getTitle();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(version.getUpToCommentDate());
		//this.limitObservationsDay= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		//this.limitObservationsMonth= String.valueOf(cal.get(Calendar.MONTH) + 1);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.limitObservations = dateFormat.format(cal.getTime());
		this.documentType = document.isTypeOne() ? "typeOne" : "typeTwo";
		this.introduction= document.getIntroduction();
		this.paragraph = 0;
		this.paragraphIds = new int[1000];
		if (portuguesVersion != null) {
			List<Paragraph> paragraphs = ParagraphDAO.selectAllParagraphsFor(portuguesVersion.getId());
			for (Paragraph p : paragraphs) {
				paragraphTexts[p.getParagraphNumber() - 1] = p.getParagraphText();
				// Esto lo maneja la version en castellano paragraphStatus[p.getParagraphNumber() - 1] = p.isDeleted();
				paragraphIds[p.getParagraphNumber() - 1] = p.getId();
			}
		}
		this.consolidated = false;
		this.consolidateText= null;
	}
	
	public boolean getCanAddParagraph() {
		return !this.isPortugues() && !this.isDesigner();
	}
	
	public boolean getCanDeleteParagraph() {
		return !this.isPortugues() && !this.isDesigner();
	}
	
	public boolean getTypeReadOnly() {
		return this.isPortugues() || this.isDesigner();
	}
	
	public boolean getDateLimitReadOnly() {
		return this.isPortugues() || this.isDesigner();
	}
	
	public boolean getPrincipalReadOnly() {
		return this.isPortugues() || this.isDesigner();
	}

	public boolean isPortugues() {
		return portugues;
	}
	
	public boolean isPortuguesOrDesigner() {
		return this.isPortugues() || this.isDesigner();
	}

	public void setPortugues(boolean portugues) {
		this.portugues = portugues;
	}

	public int getIntroductoryParagraphs() {
		return introductoryParagraphs;
	}

	public void setIntroductoryParagraphs(int introductoryParagraphs) {
		this.introductoryParagraphs = introductoryParagraphs;
	}

	public int getTextParagraphs() {
		return textParagraphs;
	}

	public void setTextParagraphs(int textParagraphs) {
		this.textParagraphs = textParagraphs;
	}

	public void initForDesignWith(int versionID) throws SQLException {
		setPortugues(false);
		setDesigner(true);
		Version version = VersionDAO.getVersion(versionID);
		Document document = DocumentDAO.getDocument(version.getDocumentId());
		this.documentId = document.getId();
		this.setPrincipal(document.isPrincipal());
		this.versionId = version.getId();
		this.version = version.getNumber();
		this.versionName = version.getName();
		this.versionStatus = version.getStatus();
		this.designerText = version.getDesignerText();
		this.title = document.getTitle();
		Calendar cal = Calendar.getInstance();
		cal.setTime(version.getUpToCommentDate());
		//this.limitObservationsDay= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		//this.limitObservationsMonth= String.valueOf(cal.get(Calendar.MONTH) + 1);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.limitObservations = dateFormat.format(cal.getTime());
		this.documentType = document.isTypeOne() ? "typeOne" : "typeTwo";
		this.introduction= document.getIntroduction();
		this.paragraph = 0;
		this.paragraphTexts = new String[1000];
		this.paragraphStatus = new boolean[1000];
		this.paragraphIds = new int[1000];
		List<Paragraph> paragraphs = ParagraphDAO.selectAllParagraphsFor(versionID);
		for (Paragraph p : paragraphs) {
			paragraphTexts[p.getParagraphNumber() - 1] = p.getParagraphText();
			paragraphStatus[p.getParagraphNumber() - 1] = p.isDeleted();
			paragraphIds[p.getParagraphNumber() - 1] = p.getId();
			paragraphDetails[p.getParagraphNumber() - 1] = p.getNumberDetail();
		}
		this.consolidated = false;
		this.consolidateText= null;
	}

	public boolean isDesigner() {
		return designer;
	}

	public void setDesigner(boolean designer) {
		this.designer = designer;
	}

	public String getDesignerText() {
		return designerText;
	}

	public void setDesignerText(String designerText) {
		this.designerText = designerText;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAppend() {
		return append;
	}

	public void setAppend(String append) {
		this.append = append;
	}

	public String getNewParagraphText() {
		return newParagraphText;
	}

	public void setNewParagraphText(String newParagraphText) {
		this.newParagraphText = newParagraphText;
	}

	public String performMove() throws SQLException, ValidationException {
		String result = Paragraph.GetParagraphNumberForDisplay(this.getParagraph());
		int dest = Paragraph.extractParagraphNumberFromDisplay(this.destination);
		// if -1 not recognized destination
		boolean append = Boolean.valueOf(this.append);
		if (dest < this.getParagraph() && !append) {
			result = Paragraph.GetParagraphNumberForDisplay(this.getParagraph() + 1);
			setParagraph(this.getParagraph() + 1);
		}
		if (append) {
			this.paragraphTexts[dest] = this.paragraphTexts[dest] + this.getNewParagraphText();
			this.paragraphDetails[dest] = this.getDetail();
		} else {
			int upperLimit = getLastParagraph(dest);
			for (; upperLimit >= dest; upperLimit = upperLimit - 1) {
				this.paragraphTexts[upperLimit + 1] = this.paragraphTexts[upperLimit];
				this.paragraphStatus[upperLimit + 1] = this.paragraphStatus[upperLimit];
				this.paragraphIds[upperLimit + 1] = this.paragraphIds[upperLimit];
				this.paragraphDetails[upperLimit + 1] = this.paragraphDetails[upperLimit];
			}
			this.paragraphTexts[dest] = this.getNewParagraphText();
			this.paragraphStatus[dest] = false;
			this.paragraphIds[dest] = 0;
			this.paragraphDetails[dest] = this.getDetail();
		}
		executeInTransaction(this);
		
		return result;
	}

	private int getLastParagraph(int dest) {
		int i = dest; 
		for (; i < Paragraph.PARAGRAPH_LIMIT && !StringUtils.isEmpty(paragraphTexts[i]); i = i + 1) {
			
		}
		if (i == Paragraph.PARAGRAPH_LIMIT) {
			return -1;
		} else {
			return i;
		}
	}

	public String validateMove() {
		int dest = Paragraph.extractParagraphNumberFromDisplay(this.destination);
		if (dest == -1) {
			return ApplicationResources.getMessage("createDocument.paragraphs.move." + ValidationErrors.INVALID_DESTINATION_PARAGRAPH);
		}
		boolean append = Boolean.valueOf(this.append);
		if (dest == this.getParagraph()) { // TODO ver si este chequeo esta bien
			return ApplicationResources.getMessage("createDocument.paragraphs.move." + ValidationErrors.DESTINATION_CANNOT_BE_ORIGIN);
		}
		if (dest > this.getLastParagraph(dest >= Paragraph.INTRODUCTION_LIMIT? Paragraph.INTRODUCTION_LIMIT : 0)) {
			return ApplicationResources.getMessage("createDocument.paragraphs.move." + ValidationErrors.DESTINATION_UPPER_LIMIT_ERROR);
		}
		if (this.getDetail().length() > 100) {
			return ApplicationResources.getMessage("createDocument.paragraphs.move." + ValidationErrors.DETAIL_TOO_LONG_ERROR);
		}
		if (append && StringUtils.isEmpty(this.paragraphTexts[dest])) {
			return ApplicationResources.getMessage("createDocument.paragraphs.move." + ValidationErrors.EMPTY_DESTINATION);
		}
		return null;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String[] getParagraphDetails() {
		return paragraphDetails;
	}

	public void setParagraphDetails(String[] paragraphDetails) {
		this.paragraphDetails = paragraphDetails;
	}
	
	public boolean isLivePreview() {
		return livePreview;
	}

	public void setLivePreview(boolean livePreview) {
		this.livePreview = livePreview;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.livePreview = false;
	}
}
