DELIMITER /
CREATE TABLE NEGOTIATION_DOCUMENT (
	"DOCUMENT_NUMBER" VARCHAR2(40) NOT NULL,
	"VER_NBR" NUMBER(8,0) DEFAULT 1 NOT NULL,
	"OBJ_ID" VARCHAR2(36) NOT NULL,
	"UPDATE_TIMESTAMP" DATE NOT NULL,
	"UPDATE_USER" VARCHAR2(60) NOT NULL
)
/
ALTER TABLE NEGOTIATION_DOCUMENT 
ADD CONSTRAINT PK_NEGOTIATION_DOCUMENT 
PRIMARY KEY (DOCUMENT_NUMBER)
/
DELIMITER ;
