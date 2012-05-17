INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) 
VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'),
'KC-SUBAWARD','Create Subaward Invoice','Create Subaward Invoice','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) 
VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'SubawardInvoiceMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES 
(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,SYS_GUID(), 1, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward' AND NMSPC_CD='KC-SUBAWARD'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/

INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) 
VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'KC-SUBAWARD','Modify Subaward Invoice','Modify Subaward Invoice','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) 
VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Subaward Invoice' 
AND NMSPC_CD='KC-SUBAWARD'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'SubawardInvoiceMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward' AND NMSPC_CD='KC-SUBAWARD'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/

INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) 
VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'KC-SUBAWARD','View Subaward Invoice','View Subaward Invoice','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) 
VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Subaward Invoice' 
AND NMSPC_CD='KC-SUBAWARD'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'SubawardInvoiceMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward' AND NMSPC_CD='KC-SUBAWARD'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES( KRIM_TYP_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Derived Role: Requisitioner', 'subAwardRequisitionerDerivedRoleTypeService', 'Y', 'KC-SUBAWARD') 
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Requisitioner', 'KC-SUBAWARD', 'Subaward Requisitioner Role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Requisitioner'), 'Y', SYSDATE)
/

-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES 
(KRIM_RSP_ID_BS_S.NEXTVAL,(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-SUBAWARD','SubAwardRequisitionerReview','Subaward Invoice Maintenance Document - Requisitioner Review and Approval','Y',SYS_GUID(),1)
/
-- the attr names are in KRIM_ATTR_DEFN_T and the values for those are in the KRIM_RSP_ATTR_DATA_T
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'),
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM LIKE 'Document Type, Routing Node % Action Information'), 
    (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'SubAwardInvoiceMaintenanceDocument',
     SYS_GUID())
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM LIKE 'Document Type, Routing Node % Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
    NM = 'routeNodeName'),'SubAwardRequisitionerReview',SYS_GUID(),1)
/
-- if node is mandatory
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM LIKE 'Document Type, Routing Node % Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'true', SYS_GUID())
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
NM LIKE 'Document Type, Routing Node % Action Information'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', SYS_GUID())
/
-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner') , 
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'), 'Y')
/
-- inserting action for responsibility
---ACTN_TYP_CD = 'A' indicates "approve" action
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview')),
'Y')
/

