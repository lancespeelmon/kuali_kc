DELIMITER /
INSERT INTO CUST_REPORT_TYPE (REPORT_TYPE_CODE, REPORT_TYPE_DESC, UPDATE_USER, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID)
VALUES (1, 'Global', 'admin', NOW(),1, UUID())
/

DELIMITER ;
