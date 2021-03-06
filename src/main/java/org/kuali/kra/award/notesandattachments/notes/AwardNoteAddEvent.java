/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.notesandattachments.notes;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is for the event to add award note
 */
public class AwardNoteAddEvent  extends AwardNoteEventBase<AwardNoteAddRule> {
    
    private static final String MSG = "Add award note  ";
    
    public AwardNoteAddEvent(String errorPathPrefix, AwardDocument document, AwardNotepad awardNotepad, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, awardNotepad, type);
    }
    
    public AwardNoteAddEvent(String errorPathPrefix, Document document, AwardNotepad awardNotepad, ErrorType type) {
        this(errorPathPrefix, (AwardDocument)document, awardNotepad, type);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public BusinessRuleInterface getRule() {
        return new AwardNoteAddRule();
    }

}
