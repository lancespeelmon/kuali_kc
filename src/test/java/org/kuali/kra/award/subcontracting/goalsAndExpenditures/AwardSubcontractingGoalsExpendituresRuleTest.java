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
package org.kuali.kra.award.subcontracting.goalsAndExpenditures;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.springframework.util.AutoPopulatingList;


@SuppressWarnings("deprecation")
public class AwardSubcontractingGoalsExpendituresRuleTest {
    
    private static final String AWARD_NUMBER = "awardNumber";
    
    private static final String BAD_FORMAT_INPUT = "bad format";
    private static final String BLANK_INPUT = "blank format";
    private static final String NO_VALID_AWARD_INPUT = "no valid award input";
    private static final String VALID_AWARD_INPUT = "valid award input";
    
    private static final String BAD_FORMAT_PROPERTY_KEY = "bad format property key";
    private static final String BAD_FORMAT_ERROR_KEY = "bad format error key";    
    private static final String BLANK_FORMAT_PROPERTY_KEY = "blank format property key";
    private static final String BLANK_FORMAT_ERROR_KEY = "blank format error key";
    
    private final static String SUB_PLAN_FLAG = "subPlanFlag";
    private final static String SUB_PLAN_FLAG_VAL = "Y";
    
    private static final String DD_ENTRY_NAME = AwardSubcontractingBudgetedGoals.class.getSimpleName();

    protected static final Long AWARD_ID_VAL = 4444L;

        
    private Mockery context = new JUnit4Mockery();
    
    
    private AwardSubcontractingGoalsExpendituresRule rule;
    
    @Before
    public void setUp() throws Exception {
        // clear the message map, since our mocks and the rule will add messages to it
        GlobalVariables.setMessageMap(new MessageMap());
        //set up rule with mocks
        this.rule = new AwardSubcontractingGoalsExpendituresRule();
        this.rule.setAwardLookupable(context.mock(Lookupable.class));
        this.rule.setDictionaryValidationService(context.mock(DictionaryValidationService.class));
        // add the 'never invoked' conditions common to all tests
        addNegativeExpectations();
    }

    @After
    public void tearDown() {
        rule = null;
    }
       
    @Test 
    public void testValidateAwardNumberRequired() throws Exception {
       
        final String awardNumber = BLANK_INPUT;
        // the positive expectations in this case for dictionaryValidationService are that validateAttributeRequired() will be called exactly once
        context.checking(new Expectations() {{
            one(rule.getDictionaryValidationService()).validateAttributeRequired(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, false, AWARD_NUMBER); 
            // 'will' side-effect custom action to add dummy error message to global error map, since rule checks that map.
            GlobalVariables.getMessageMap().putError(BLANK_FORMAT_PROPERTY_KEY, BLANK_FORMAT_ERROR_KEY);
        }});
        
        Assert.assertFalse(rule.validateAwardNumber(awardNumber));
        // assert that dummy error generated by mock remains unchanged in the map
        assertError(BLANK_FORMAT_PROPERTY_KEY, BLANK_FORMAT_ERROR_KEY);
    }
    

    @Test 
    public void testValidateAwardNumberInvalidFormat() throws Exception {
        
        final String awardNumber = BAD_FORMAT_INPUT;
        // the positive expectations in this case are that validateAttributeRequired() and validateAttributeFormat() will be both called exactly once
        context.checking(new Expectations() {{
            one(rule.getDictionaryValidationService()).validateAttributeRequired(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, false, AWARD_NUMBER);
            one(rule.getDictionaryValidationService()).validateAttributeFormat(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, AWARD_NUMBER); 
            // 'will' side-effect custom action to add dummy error message to global error map, since rule checks that map.
            will(new CustomAction("Add random value to list") {
                public Object invoke(Invocation invocation) throws Throwable {
                    GlobalVariables.getMessageMap().putError(BAD_FORMAT_PROPERTY_KEY, BAD_FORMAT_ERROR_KEY);
                    return null;
                }
            });
        }});
        
        Assert.assertFalse(rule.validateAwardNumber(awardNumber));
        // assert that dummy error generated by mock remains unchanged in the map
        assertError(BAD_FORMAT_PROPERTY_KEY, BAD_FORMAT_ERROR_KEY);
    }
    
    
    
    @Test 
    public void testValidateAwardNumberAwardNotFound() throws Exception {
        
        final String awardNumber = NO_VALID_AWARD_INPUT;
        // the positive expectations in this case are that validateAttributeRequired(), validateAttributeFormat() and getSearchResults() will be called exactly once
        context.checking(new Expectations() {{
            one(rule.getDictionaryValidationService()).validateAttributeRequired(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, false, AWARD_NUMBER);
            one(rule.getDictionaryValidationService()).validateAttributeFormat(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, AWARD_NUMBER);
            
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(SUB_PLAN_FLAG, SUB_PLAN_FLAG_VAL);
            fieldValues.put(AWARD_NUMBER, awardNumber);            
            one(rule.getAwardLookupable()).getSearchResults(fieldValues);
            will(returnValue(new ArrayList<Award>()));
        }});

        Assert.assertFalse(rule.validateAwardNumber(awardNumber));
        // verify error message generated by the rule when award number passes dictionary validation but a valid sub-plan award with that award number is not found 
        assertError(AWARD_NUMBER, KeyConstants.SUB_PLAN_AWARD_NOT_FOUND);
    }
    
     
    @Test 
    public void testValidateAwardNumberValidAward() throws Exception {
        
        final String awardNumber = VALID_AWARD_INPUT;
        // the positive expectations in this case are that validateAttributeRequired(), validateAttributeFormat() and getSearchResults() will be called exactly once
        context.checking(new Expectations() {{
            one(rule.getDictionaryValidationService()).validateAttributeRequired(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, false, AWARD_NUMBER);
            one(rule.getDictionaryValidationService()).validateAttributeFormat(DD_ENTRY_NAME, AWARD_NUMBER, awardNumber, AWARD_NUMBER);
            
            Award foundAward = new Award();
            foundAward.setAwardId(AWARD_ID_VAL);
            
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(SUB_PLAN_FLAG, SUB_PLAN_FLAG_VAL);
            fieldValues.put(AWARD_NUMBER, awardNumber);
            List<Award> awardResults = new ArrayList<Award>();            
            awardResults.add(foundAward);
            
            one(rule.getAwardLookupable()).getSearchResults(fieldValues);
            will(returnValue(awardResults));
        }});

        Assert.assertTrue(rule.validateAwardNumber(awardNumber));
        // check that the award id of the found award is available at the end of a successful validation
        Assert.assertEquals(AWARD_ID_VAL.toString(), rule.getAwardId());
    }
     
    
    // this method adds the constant negative expectations which are that 
    // 1. validateAttributeFormat() is never invoked with a blank input
    // 2. getSearchResults() is never invoked with a blank input 
    // 3. getSearchResults() is never invoked with a bad format input 
    private void addNegativeExpectations() {
        
        final Map<String, String> blankFieldValues = new HashMap<String, String>();
        blankFieldValues.put(SUB_PLAN_FLAG, SUB_PLAN_FLAG_VAL);
        blankFieldValues.put(AWARD_NUMBER, BLANK_INPUT);        
        
        final Map<String, String> badFormatFieldValues = new HashMap<String, String>();
        badFormatFieldValues.put(SUB_PLAN_FLAG, SUB_PLAN_FLAG_VAL);
        badFormatFieldValues.put(AWARD_NUMBER, BAD_FORMAT_INPUT);
        
        context.checking(new Expectations() {{
            never(rule.getDictionaryValidationService()).validateAttributeFormat(DD_ENTRY_NAME, AWARD_NUMBER, BLANK_INPUT, AWARD_NUMBER);
            never(rule.getAwardLookupable()).getSearchResults(blankFieldValues);            
            never(rule.getAwardLookupable()).getSearchResults(badFormatFieldValues);
        }});
    }
    
    
    /**
     * Assert an error.  The Error Map should have one error with the given
     * property key and error key.
     * @param propertyKey
     * @param errorKey
     */
    @SuppressWarnings("rawtypes")
    private void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        Assert.assertNotNull(errors);
        Assert.assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getErrorKey(), errorKey);
    }
    
}
