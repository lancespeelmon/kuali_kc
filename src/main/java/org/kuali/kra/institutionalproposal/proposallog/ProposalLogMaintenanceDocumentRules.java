/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

public class ProposalLogMaintenanceDocumentRules extends MaintenanceDocumentRuleBase {
    
    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(
     * org.kuali.rice.kns.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        boolean retval = true;
        
        ProposalLog proposalLog = (ProposalLog) document.getNewMaintainableObject().getBusinessObject();
        
        if (ObjectUtils.isNotNull(proposalLog.getPiId()) && ObjectUtils.isNotNull(proposalLog.getRolodexId())) {
            GlobalVariables.getErrorMap().putError("document.newMaintainableObject.rolodexId", KeyConstants.ERROR_MULTIPLE_PRINCIPAL_INVESTIGATORS, "");
            retval = false;
        }
        
        return retval;
    }

}
