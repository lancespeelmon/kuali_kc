-- View for Coeus compatibility 
CREATE OR REPLACE VIEW OSP$COMM_MEMBERSHIPS AS

SELECT
    COMMITTEE_ID,
    DECODE (PERSON_ID, NULL, TO_CHAR(ROLODEX_ID),
                             PERSON_ID) AS PERSON_ID,
    COMM_MEMBERSHIP_ID AS MEMBERSHIP_ID,
    SEQUENCE_NUMBER,
    PERSON_NAME,
    DECODE (PERSON_ID, NULL, 'N',
                             'Y') AS NON_EMPLOYEE_FLAG,
    PAID_MEMBER_FLAG,
    TERM_START_DATE,
    TERM_END_DATE,
    MEMBERSHIP_TYPE_CODE,
    COMMENTS,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM COMM_MEMBERSHIPS;