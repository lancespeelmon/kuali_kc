<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireAttributes" value="${DataDictionary.Questionnaire.attributes}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Questionnaire </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
    
        
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <%--<tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.questionnaireId}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionnaireId" 
                                          attributeEntry="${questionnaireAttributes.questionnaireId}" />
            </td>
        </tr>
        --%>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.name}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.name" 
                                          attributeEntry="${questionnaireAttributes.name}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.description}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.description" 
                                          attributeEntry="${questionnaireAttributes.description}" />
            </td>
        </tr>
        <tr>
            <th align="right" valign="middle" width="115">
                <kul:htmlAttributeLabel attributeEntry="${questionnaireAttributes.isFinal}" />
            </th>
            <td align="left" valign="middle" colspan="3">
                <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.isFinal" 
                                          attributeEntry="${questionnaireAttributes.isFinal}" />
            </td>
        </tr>
    </table>
</div>