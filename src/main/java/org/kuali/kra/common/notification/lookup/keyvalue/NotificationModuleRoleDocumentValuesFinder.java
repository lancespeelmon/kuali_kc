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
package org.kuali.kra.common.notification.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;

/**
 * Defines the values finder for the document types in the notification module
 * role qualifier screen
 */
public class NotificationModuleRoleDocumentValuesFinder extends KeyValuesBase {
    

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> documentList = new ArrayList<KeyLabelPair>();
        documentList.add(new KeyLabelPair(Protocol.class.getName(), "Protocol"));
        documentList.add(new KeyLabelPair(ProtocolOnlineReview.class.getName(), "Protocol Online Review"));
        return documentList;
    }

}