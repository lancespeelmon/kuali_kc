DELIMITER /
DECLARE temp NUMBER;
BEGIN
        SELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'SEQ_DISCLOSURE_ID';
        IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_DISCLOSURE_ID'; END IF;
END;
/

CREATE SEQUENCE SEQ_DISCLOSURE_ID INCREMENT BY 1 START WITH 1 NOCACHE
/
DELIMITER ;
