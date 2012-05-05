UPDATE MEMBERSHIP_ROLE SET COMMITTEE_TYPE_CODE = '1' WHERE COMMITTEE_TYPE_CODE = null
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE) 
VALUES ( 15, 'Vet', sysdate, 'admin', SYS_GUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE) 
VALUES ( 16, 'IACUC Chair', sysdate, 'admin', SYS_GUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 17, 'IACUC Alternate', sysdate, 'admin', SYS_GUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 18, 'IACUC Member', sysdate, 'admin', SYS_GUID(), 1, '3' ) 
/
INSERT INTO MEMBERSHIP_ROLE ( MEMBERSHIP_ROLE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID,VER_NBR, COMMITTEE_TYPE_CODE ) 
VALUES ( 19, 'IACUC Admin', sysdate, 'admin', SYS_GUID(), 1, '3' ) 
/
