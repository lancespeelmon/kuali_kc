/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelper;
import org.kuali.kra.protocol.notification.ProtocolNotificationContext;
import org.kuali.kra.protocol.permission.PermissionsHelper;
import org.kuali.kra.protocol.personnel.PersonnelHelper;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelper;
import org.kuali.kra.questionnaire.QuestionableFormInterface;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.service.DataDictionaryService;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public abstract class ProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable, QuestionableFormInterface {
    
    private static final long serialVersionUID = 4646326030098259702L;
// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    /**
//     * When true, the online review header will not be displayed when it is disabled.
//     */
//    private static final boolean HIDE_ONLINE_REVIEW_WHEN_DISABLED = true;
//    private static final String ONLINE_REVIEW_NAV_TO = "onlineReview";
//    private static final String CUSTOM_DATA_NAV_TO = "customData";
    
    
    private ProtocolHelper protocolHelper;
    private PermissionsHelper permissionsHelper;
    private PersonnelHelper personnelHelper;
    private ProtocolCustomDataHelper protocolCustomDataHelper;
    private ProtocolSpecialReviewHelper protocolSpecialReviewHelper;

    private NotificationHelper<ProtocolNotificationContext> protocolNotificationHelper;

// TODO *********uncomment the code below in increments as needed during refactoring*********     

//    private ActionHelper actionHelper;
//    private OnlineReviewsActionHelper onlineReviewsActionHelper;
//    private QuestionnaireHelper questionnaireHelper;
//    //transient so that the helper and its members don't have to be serializable or transient
//    //reinitialized in the getter
//    private transient NotesAttachmentsHelper notesAttachmentsHelper;
    
    
      private boolean auditActivated;

      
// TODO *********uncomment the code below in increments as needed during refactoring*********      
//    private ProtocolReferenceBean newProtocolReferenceBean;
//    
    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
//    
//    private boolean javaScriptEnabled = true;
//    
//    private String detailId;
//    // temp field : set in presave and then referenced in postsave
//    private transient List<ProtocolFundingSource> deletedProtocolFundingSources;
 
    
    public ProtocolForm() throws Exception {
        super();
        initialize();
        this.registerEditableProperty("methodToCall");
    }
    

    /**
     *
     * This method initialize all form variables
     * @throws Exception 
     */
    public void initialize() throws Exception {
        // hook invocation 
        setProtocolHelper(createNewProtocolHelperInstanceHook(this));
        setPermissionsHelper(createNewPermissionsHelperInstanceHook(this));
        setPersonnelHelper(createNewPersonnelHelperInstanceHook(this));

// TODO *********uncomment the code below in increments as needed during refactoring*********         
//        setActionHelper(new ActionHelper(this));
//        setQuestionnaireHelper(new QuestionnaireHelper(this));
//        setNotesAttachmentsHelper(new NotesAttachmentsHelper(this));
//        this.notesAttachmentsHelper.prepareView();
//        setNewProtocolReferenceBean(new ProtocolReferenceBean());
//        setOnlineReviewsActionHelper(new OnlineReviewsActionHelper(this));
    }

    protected abstract ProtocolHelper createNewProtocolHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract PermissionsHelper createNewPermissionsHelperInstanceHook(ProtocolForm protocolForm);
    protected abstract PersonnelHelper createNewPersonnelHelperInstanceHook(ProtocolForm protocolForm);

    
    
// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    /**
//     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
//     * 
//     * We only enable the Online Review tab if the protocol is in a state to be reviewed and
//     * the user has the IRB Admin role or the user has an Online Review. 
//     * 
//     * If HIDE_ONLINE_REVIEW_WHEN_DISABLED is true, then the tab will be removed from the tabs 
//     * List if it is disabled.
//     * 
//     */
//    @Override
//    public HeaderNavigation[] getHeaderNavigationTabs() {
//        
//        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();
//        
//        ProtocolOnlineReviewService onlineReviewService = getProtocolOnlineReviewService();
//        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
//        boolean onlineReviewTabEnabled = false;
//
//        if (getProtocolDocument() != null && getProtocolDocument().getProtocol() != null) {
//            String principalId = GlobalVariables.getUserSession().getPrincipalId();
//            ProtocolSubmission submission = getProtocolDocument().getProtocol().getProtocolSubmission();
//            boolean isUserOnlineReviewer = onlineReviewService.isProtocolReviewer(principalId, false, submission);
//            boolean isUserIrbAdmin = getKraAuthorizationService().hasRole(GlobalVariables.getUserSession().getPrincipalId(), "KC-UNT", "IRB Administrator"); 
//            onlineReviewTabEnabled = (isUserOnlineReviewer || isUserIrbAdmin) 
//                    && onlineReviewService.isProtocolInStateToBeReviewed(getProtocolDocument().getProtocol());
//        }
//        
//            //We have to copy the HeaderNavigation elements into a new collection as the 
//            //List returned by DD is it's cached copy of the header navigation list.
//        for (HeaderNavigation nav : navigation) {
//            if (StringUtils.equals(nav.getHeaderTabNavigateTo(),ONLINE_REVIEW_NAV_TO)) {
//                nav.setDisabled(!onlineReviewTabEnabled);
//                if (onlineReviewTabEnabled || ((!onlineReviewTabEnabled) && (!HIDE_ONLINE_REVIEW_WHEN_DISABLED))) {
//                    resultList.add(nav);
//                }
//            } else if (StringUtils.equals(nav.getHeaderTabNavigateTo(),CUSTOM_DATA_NAV_TO)) {
//                boolean displayTab = this.getCustomDataHelper().canDisplayCustomDataTab();
//                nav.setDisabled(!displayTab);
//                if (displayTab) {
//                    resultList.add(nav);
//                }
//            } else {
//                resultList.add(nav);
//            }
//        }
//        
//        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
//        resultList.toArray(result);
//        return result;
//    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    /**
//     * 
//     * This method is a wrapper method for getting ProtocolOnlineReviewerService service.
//     * @return
//     */
//    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
//    }
//
//    
//    
//    @Override
//    public void populate(HttpServletRequest request) { 
//        initAnswerList(request);
//        super.populate(request);
//        
//        // Temporary hack for KRACOEUS-489
//        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
//            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
//        }
//    }
//    
//    /*
//     * For submission questionnaire, it is a popup and not a session document.
//     * so, it has to be retrieved, then populate with the new data.
//     */
//    private void initAnswerList(HttpServletRequest request) {
//        
//        String protocolNumber = request.getParameter("questionnaireHelper.protocolNumber");
//        String submissionNumber = request.getParameter("questionnaireHelper.submissionNumber");
//        if (StringUtils.isNotBlank(protocolNumber) && protocolNumber.endsWith("T")) {
//            ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber, CoeusSubModule.PROTOCOL_SUBMISSION, submissionNumber, false);
//            this.getQuestionnaireHelper().setAnswerHeaders(
//                    getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
//        }
//    }
//
//    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
//        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
//}
//
//    @Override
//    public void populateHeaderFields(WorkflowDocument workflowDocument) {
//        super.populateHeaderFields(workflowDocument);
//        ProtocolDocument pd = getProtocolDocument();
//        
//        HeaderField documentNumber = getDocInfo().get(0);
//        documentNumber.setDdAttributeEntryName("DataDictionary.ProtocolDocument.attributes.documentNumber");
//        
//        ProtocolStatus protocolStatus = (pd == null) ? null : pd.getProtocol().getProtocolStatus();
//        HeaderField docStatus = new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.workflowDocumentStatus", protocolStatus == null? "" : protocolStatus.getDescription());
//        getDocInfo().set(1, docStatus);
//        
//        String lastUpdatedDateStr = null;
//        if(pd != null && pd.getUpdateTimestamp() != null) {
//            lastUpdatedDateStr = CoreApiServiceLocator.getDateTimeService().toString(pd.getUpdateTimestamp(), "hh:mm a MM/dd/yyyy");
//        }
//        
//        if(getDocInfo().size() > 2) {
//            HeaderField initiatorField = getDocInfo().get(2);
//            String modifiedInitiatorFieldStr = initiatorField.getDisplayValue();
//            if(StringUtils.isNotBlank(lastUpdatedDateStr)) {
//                modifiedInitiatorFieldStr = modifiedInitiatorFieldStr + " : " + lastUpdatedDateStr;
//            }
//            getDocInfo().set(2, new HeaderField("DataDictionary.Protocol.attributes.initiatorLastUpdated", modifiedInitiatorFieldStr));
//        }
//        
//        String protocolSubmissionStatusStr = null;
//        if(pd != null && pd.getProtocol() != null && pd.getProtocol().getProtocolSubmission() != null) {
//            pd.getProtocol().getProtocolSubmission().refreshReferenceObject("submissionStatus");
//            protocolSubmissionStatusStr = pd.getProtocol().getProtocolSubmission().getSubmissionStatus().getDescription();
//        }
//        HeaderField protocolSubmissionStatus = new HeaderField("DataDictionary.Protocol.attributes.protocolSubmissionStatus", protocolSubmissionStatusStr);
//        getDocInfo().set(3, protocolSubmissionStatus);
//        
//        getDocInfo().add(new HeaderField("DataDictionary.Protocol.attributes.protocolNumber", (pd == null) ? null : pd.getProtocol().getProtocolNumber()));
//
//        String expirationDateStr = null;
//        if(pd != null && pd.getProtocol().getExpirationDate() != null) {
//            expirationDateStr = CoreApiServiceLocator.getDateTimeService().toString(pd.getProtocol().getExpirationDate(), "MM/dd/yyyy");
//        }
//        
//        HeaderField expirationDate = new HeaderField("DataDictionary.Protocol.attributes.expirationDate", expirationDateStr);
//        getDocInfo().add(expirationDate);
//    }
//
    /* Reset method
     * @param mapping
     * @param request
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
        //onlineReviewsActionHelper.init(true);
    }
    
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }
    

    public void setProtocolHelper(ProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }
    
    private void setPersonnelHelper(PersonnelHelper personnelHelper) {
        this.personnelHelper = personnelHelper;
    }
    
    public PersonnelHelper getPersonnelHelper() {
        return personnelHelper;
    }
    
    private void setPermissionsHelper(PermissionsHelper permissionsHelper) {
        this.permissionsHelper = permissionsHelper;
    }
    
    public PermissionsHelper getPermissionsHelper() {
          return permissionsHelper;
    }
    
//    
//    public void setNewProtocolReferenceBean(ProtocolReferenceBean newProtocolReferenceBean) {
//        this.newProtocolReferenceBean = newProtocolReferenceBean;
//    }
//
//    public ProtocolReferenceBean getNewProtocolReferenceBean() {
//        return newProtocolReferenceBean;
//    }
    
    @Override
    protected void setSaveDocumentControl(Map editMode) {
      
    }
   

    public CustomDataHelperBase getCustomDataHelper() {
        return protocolCustomDataHelper;
    }
    
    public ProtocolCustomDataHelper getProtocolCustomDataHelper() {
        return protocolCustomDataHelper;
    }

    public void setProtocolCustomDataHelper(ProtocolCustomDataHelper customDataHelper) {
        this.protocolCustomDataHelper = customDataHelper;
    }
    
    
    /** {@inheritDoc} */
    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    /** {@inheritDoc} */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public ProtocolSpecialReviewHelper getProtocolSpecialReviewHelper() {
        return protocolSpecialReviewHelper;
    }

    // following method is provided to allow compatibility with common Special Review tag files
    public ProtocolSpecialReviewHelper getSpecialReviewHelper() {
        return protocolSpecialReviewHelper;
    }

    public void setProtocolSpecialReviewHelper(ProtocolSpecialReviewHelper specialReviewHelper) {
        this.protocolSpecialReviewHelper = specialReviewHelper;
    }

//    /**
//     * Gets the Notes & Attachments Helper.
//     * @return Notes & Attachments Helper
//     */
//    public NotesAttachmentsHelper getNotesAttachmentsHelper() {
//        if (notesAttachmentsHelper == null) {
//            notesAttachmentsHelper = new NotesAttachmentsHelper(this);
//        }
//        
//        return notesAttachmentsHelper;
//    }
//
//    /**
//     * Sets the Notes & Attachments Helper.
//     * @param notesAttachmentsHelper the Notes & Attachments Helper
//     */
//    public void setNotesAttachmentsHelper(NotesAttachmentsHelper notesAttachmentsHelper) {
//        this.notesAttachmentsHelper = notesAttachmentsHelper;
//    }
//    
//    public ActionHelper getActionHelper() {
//        return actionHelper;
//    }
//    
//    private void setActionHelper(ActionHelper actionHelper) {
//        this.actionHelper = actionHelper;
//    }
//
//    public boolean isJavaScriptEnabled() {
//        return javaScriptEnabled;
//    }
//
//    public void setJavaScriptEnabled(boolean javaScriptEnabled) {
//        this.javaScriptEnabled = javaScriptEnabled;
//    }

    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }


//    public QuestionnaireHelper getQuestionnaireHelper() {
//        return questionnaireHelper;
//    }
//
//    public void setQuestionnaireHelper(QuestionnaireHelper questionnaireHelper) {
//        this.questionnaireHelper = questionnaireHelper;
//    }
//
//    public void setOnlineReviewsActionHelper(OnlineReviewsActionHelper onlineReviewActionHelper) {
//        this.onlineReviewsActionHelper = onlineReviewActionHelper;
//    }
//
//    public OnlineReviewsActionHelper getOnlineReviewsActionHelper() {
//        return onlineReviewsActionHelper;
//    }

    public NotificationHelper<ProtocolNotificationContext> getNotificationHelper() {
        return protocolNotificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<ProtocolNotificationContext> notificationHelper) {
        this.protocolNotificationHelper = notificationHelper;
    }

//    @Override
//    public boolean isPropertyEditable(String propertyName) {
//        if (propertyName.startsWith("actionHelper.protocolSubmitAction.reviewer") ||
//                propertyName.startsWith("methodToCall.printSubmissionQuestionnaireAnswer.line")
//                || propertyName.startsWith("methodToCall.saveCorrespondence")
//                || propertyName.startsWith("methodToCall.closeCorrespondence")) {
//            return true;
//        } else {
//            return super.isPropertyEditable(propertyName);
//        }
//    }
    
    public KraAuthorizationService getKraAuthorizationService() {
        return KraServiceLocator.getService(KraAuthorizationService.class);
    }
    
    /**
     * 
     * This method returns true if the risk level panel should be displayed.
     * @return
     */
    public boolean getDisplayRiskLevelPanel() {
        return true;
// TODO *********commented the code below during IACUC refactoring*********         
//        return this.getProtocolDocument().getProtocol().getProtocolRiskLevels() != null 
//            && this.getProtocolDocument().getProtocol().getProtocolRiskLevels().size() > 0;
        
    }
//    
//    public List<ExtraButton> getExtraActionsButtons() {
//        // clear out the extra buttons array
//        extraButtons.clear();
//
//        String externalImageURL = Constants.KR_EXTERNALIZABLE_IMAGES_URI_KEY;
//        ConfigurationService configurationService = KRADServiceLocator.getKualiConfigurationService();
//        
//        boolean suppressRoutingControls = getActionHelper().getCanApproveFull() || !getActionHelper().getCanApproveOther();
//        if (suppressRoutingControls && getDocumentActions().get(KRADConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS) != null) {
//            String sendAdHocRequestsImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_sendadhocreq.gif";
//            addExtraButton("methodToCall.sendAdHocRequests", sendAdHocRequestsImage, "Send AdHoc Requests");
//        }
//        externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
//        
//        String sendNotificationImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_send_notification.gif";
//        addExtraButton("methodToCall.sendNotification", sendNotificationImage, "Send Notification");
//        
//        return extraButtons;
//    }
//     
      public abstract String getModuleCode();
//    public String getModuleCode() {
//        return CoeusModule.IRB_MODULE_CODE;
//    }
//
//    public String getDetailId() {
//        return detailId;
//    }
//
//    public void setDetailId(String detailId) {
//        this.detailId = detailId;
//    }
//
//    public List<ProtocolFundingSource> getDeletedProtocolFundingSources() {
//        return deletedProtocolFundingSources;
//    }
//
//    public void setDeletedProtocolFundingSources(List<ProtocolFundingSource> deletedProtocolFundingSources) {
//        this.deletedProtocolFundingSources = deletedProtocolFundingSources;
//    }
    
    public String getQuestionnaireFieldStarter() {
        return "questionnaireHelper.answerHeaders[";
    }
    
    public String getQuestionnaireFieldMiddle() {
        return DEFAULT_MIDDLE;
    }
    
    public String getQuestionnaireFieldEnd() {
        return DEFAULT_END;
    }
    
}