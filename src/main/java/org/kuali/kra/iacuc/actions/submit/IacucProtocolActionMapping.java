/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.submit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.drools.brms.FactBean;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.meeting.CommitteeScheduleMinute;
import org.kuali.kra.protocol.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolActionMapping;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/*
 * This class is for the condition attributes of of the protocol action.
 * i.e., the condition of protocol status, submissionstatus, action type code etc.
 */
public class IacucProtocolActionMapping extends ProtocolActionMapping {
    
    private static final Map<String, String> ACTION_TYPE_SUBMISSION_TYPE_MAP;
    static {
        final Map<String, String> codeMap = new HashMap<String, String>();        
//TODO: More to do here for IACUC.
//TODO:IACUC        codeMap.put(IacucProtocolActionType.SUSPENDED, IacucProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
//        codeMap.put(IacucProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, IacucProtocolSubmissionType.REQUEST_TO_CLOSE);
//        codeMap.put(IacucProtocolActionType.TERMINATED, IacucProtocolSubmissionType.REQUEST_FOR_TERMINATION);        
        ACTION_TYPE_SUBMISSION_TYPE_MAP = Collections.unmodifiableMap(codeMap);
    }

    private static final List<String> APPROVE_ACTION_TYPES;
    static {
        final List<String> codes = new ArrayList<String>();     
        codes.add(IacucProtocolActionType.IACUC_APPROVED);
//TODO: More here to do for IACUC. Must include "designated member approval" and any other similar approvals.
//        codes.add(IacucProtocolActionType.EXPEDITE_APPROVAL);
//        codes.add(IacucProtocolActionType.GRANT_EXEMPTION);
        APPROVE_ACTION_TYPES = codes;
    }


    public IacucProtocolActionMapping(String actionTypeCode, String submissionStatusCode, String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId, Integer submissionNumber) {
        super(actionTypeCode, submissionStatusCode, submissionTypeCode, protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
    }
    
    protected String getActionTypeSubmissionType(String actionTypeCode) {
        return ACTION_TYPE_SUBMISSION_TYPE_MAP.get(actionTypeCode);
    }

    /**
     * 
     * This method Checks if there are any pending submissions for this protocol
     * @return
     */
    public boolean getSubmissionCountCond2() {
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        
        return businessObjectService.countMatching(IacucProtocolSubmission.class, positiveFieldValues) == 0;
    }
    
    /**
     * 
     * This method Check if there are any pending amendmends/renewals for this protocols
     * @return
     */
    public boolean getSubmissionCountCond3() {
        return dao.getProtocolSubmissionCountFromProtocol(protocol.getProtocolNumber());
    }
    
//TODO: IACUC work needed here to determine additional request types    
    /**
     * 
     * This method check to see if there is pending submission with one of the following submission type
     * 107 - Request to deactivate
     * 108 - Request to lift hold
     * 109 --Request to close (should be here eventually...)
     * 110 --Request For Suspension   (ditto)
     * 108 --Request for Termination  (ditto)
     * @return
     */
    public boolean getSubmissionCountCond4() {
        
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        positiveFieldValues.put("submissionStatusCode", getPendingSubmissionStatusCodes());
        positiveFieldValues.put("submissionTypeCode", Arrays.asList(new String[] {
                IacucProtocolSubmissionType.REQUEST_TO_DEACTIVATE, IacucProtocolSubmissionType.REQUEST_TO_LIFT_HOLD}));
        
        return businessObjectService.countMatching(IacucProtocolSubmission.class, positiveFieldValues) == 0;

    }
        
    
    /*
     * Coeus called submission status of 100/101/102 as pending submission
     */
    protected List<String> getPendingSubmissionStatusCodes() {
        List<String> submissionStatusCodes = new ArrayList<String>();
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE);
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.IN_AGENDA); 
        submissionStatusCodes.add(IacucProtocolSubmissionStatus.PENDING);
        return submissionStatusCodes;

    }
    
   /**
     * check if there are any other pending submissions.
     * Basically, check the matching protocol submission with the highest submission# does not have
     * status of (100,101,102)
     */
    @SuppressWarnings("unchecked")
    public boolean getSubmissionCountCond5() {                
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<IacucProtocolSubmission> submissions = (List<IacucProtocolSubmission>)businessObjectService.findMatchingOrderBy(IacucProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return submissions.isEmpty() || !getPendingSubmissionStatusCodes().contains(submissions.get(0).getSubmissionStatusCode());
        
    }
    
    /**
     * 
     * This method Check if protocol has a submission which is in statuscode (100,101,102, 201, 202)  
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean getSubmissionCountForWithdraw() {              
        List <String> statusCodes = Arrays.asList(new String[] {"101","102","103"});
        Map<String, Object> positiveFieldValues = new HashMap<String, Object>();
        positiveFieldValues.put(PROTOCOL_NUMBER, protocol.getProtocolNumber());
        positiveFieldValues.put(SEQUENCE_NUMBER, protocol.getSequenceNumber());
        List<IacucProtocolSubmission> submissions = (List<IacucProtocolSubmission>)businessObjectService.findMatchingOrderBy(IacucProtocolSubmission.class, positiveFieldValues, "submissionNumber", false);
        return !submissions.isEmpty() && statusCodes.contains(submissions.get(0).getSubmissionStatusCode());
        
    }
    
}