
ALTER TABLE IACUC_PERSON_PROC_DETAIL
    ADD CONSTRAINT FK_PROC_PERS_RESP_ID FOREIGN KEY (IACUC_PROC_PERS_RESP_ID)
    REFERENCES IACUC_PROC_PERSON_RESPONSIBLE (IACUC_PROC_PERS_RESP_ID)
/

