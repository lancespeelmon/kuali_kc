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
package org.kuali.kra.proposaldevelopment.bo.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Primary Key for the ProposalPerson BO.
 */
@SuppressWarnings("serial")
public class ProposalPersonId implements Serializable {
    
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name="PROP_PERSON_NUMBER")
    private Integer proposalPersonNumber;
   
    public String getProposalNumber() {
        return this.proposalNumber;
    }
    
    public Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProposalPersonId)) return false;
        if (obj == null) return false;
        ProposalPersonId other = (ProposalPersonId) obj;
        return StringUtils.equals(proposalNumber, other.proposalNumber) &&
               ObjectUtils.equals(proposalPersonNumber, other.proposalPersonNumber);
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(proposalNumber).append(proposalPersonNumber).toHashCode();
    }
}
