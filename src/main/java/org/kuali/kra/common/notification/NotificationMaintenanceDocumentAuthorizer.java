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
package org.kuali.kra.common.notification;

import java.util.HashSet;
import java.util.Set;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.kns.exception.AuthorizationException;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class NotificationMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    @Override
    public Set<String> getDocumentActions(Document document, Person user, Set<String> documentActions) {
        Set<String> actions = getDocumentActions(document);
        actions = super.getDocumentActions(document, user, actions);
        return actions;
    }

    protected Set<String> getDocumentActions(Document document) {
        Set<String> documentActions = new HashSet<String>();
        boolean hasModifyPermission = getNotificationAuthorizationService().hasPermission(
                PermissionConstants.MODIFY_NOTIFICATION);
        boolean hasViewPermission = hasModifyPermission
                || getNotificationAuthorizationService().hasPermission(
                        PermissionConstants.VIEW_NOTIFICATION);
        if (hasModifyPermission) {
            documentActions = getDocumentActionsWithModifyPermission(document);
        }
        else if (hasViewPermission) {
            documentActions = getDocumentActionsWithViewPermission(document);
        }
        else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Edit/View", "Notification");
        }
        return documentActions;
    }

    private NotificationAuthorizationService getNotificationAuthorizationService() {
        return KraServiceLocator.getService(NotificationAuthorizationService.class);
    }
    
    private Set<String> getDocumentActionsWithModifyPermission(Document document) {
        Set<String> documentActions = new HashSet<String>();
        if (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("I")
                || document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("S")) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ROUTE);
        }
        else {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
        }
        return documentActions;

    }

    private Set<String> getDocumentActionsWithViewPermission(Document document) {
        Set<String> documentActions = new HashSet<String>();
        String maintAction = ((MaintenanceDocumentBase) document).getNewMaintainableObject().getMaintenanceAction();
        if (document.getDocumentHeader().getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("I")) {
            if (maintAction.equals(KNSConstants.MAINTENANCE_COPY_ACTION)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Copy", "Notification");
            }
            else if (maintAction.equals(KNSConstants.MAINTENANCE_NEW_ACTION)) {
                throw new AuthorizationException(GlobalVariables.getUserSession().getPerson().getPrincipalName(), "Create", "Notification");
            }
            else {
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
                documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
            }
        }
        else {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
        }
        return documentActions;

    }    
}
