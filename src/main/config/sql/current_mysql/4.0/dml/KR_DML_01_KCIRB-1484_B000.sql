DELIMITER /
INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Full Unmask Field'),'KC-SYS','View Active Protocol Review Types','Can view all active protocol review types','Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Protocol Review Types'),'Y',UUID(),1)
/
DELIMITER ;
