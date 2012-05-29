DELIMITER /
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PROTOCOL','Document','PROTOCOL_DISCLOSE_STATUS_CODES',1,'CONFG','100;101;200;201;202','Protocol status codes a coi disclosure event will be considered active.','A',UUID())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PROTOCOL','Document','SPONSORS_FOR_PROTOCOL_DISCLOSE',1,'CONFG','COI Disclosures','Define sponsors for protocol requiring disclosure.','A',UUID())
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KC','KC-PROTOCOL','Document','ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE',1,'CONFG','N','All IRB protocols require disclosure, irrespective to funding sponsor code','A',UUID())
/
DELIMITER ;