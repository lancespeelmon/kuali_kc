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
package org.kuali.kra.protocol.auth;


/**
 * Is the user allowed to administratively correct a protocol?
 */
public abstract class AdminCorrectionProtocolAuthorizer extends ProtocolAuthorizer {

    /**
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTask)
     */
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return canExecuteAction(task.getProtocol(), getActionTypeAdminCorrectionHook()) &&
               hasPermission(userId, task.getProtocol(), getPermissionMaintainProtocolSubmissionHook());
    }

    protected abstract String getActionTypeAdminCorrectionHook();
    protected abstract String getPermissionMaintainProtocolSubmissionHook();

}
