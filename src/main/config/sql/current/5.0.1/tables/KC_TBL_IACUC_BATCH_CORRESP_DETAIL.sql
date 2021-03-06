CREATE TABLE IACUC_BATCH_CORRESP_DETAIL ( 
    BATCH_CORRESPONDENCE_DETAIL_ID NUMBER(12,0) NOT NULL, 
    BATCH_CORRESPONDENCE_TYPE_CODE VARCHAR2(3) NOT NULL, 
    PROTO_CORRESP_TYPE_CODE VARCHAR2(3) NOT NULL, 
    DAYS_TO_EVENT NUMBER(3,0) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL)
/

-- Primary Key Constraint 
ALTER TABLE IACUC_BATCH_CORRESP_DETAIL 
ADD CONSTRAINT PK_BATCH_CORRESPONDENCE_DETAIL 
PRIMARY KEY (BATCH_CORRESPONDENCE_DETAIL_ID)
/

