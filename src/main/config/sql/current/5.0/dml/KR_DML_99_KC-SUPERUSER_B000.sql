DECLARE roleCount NUMBER;
        rolePermCount NUMBER;
        CURSOR cur IS SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD LIKE 'KC%';
BEGIN
    SELECT COUNT(*) INTO roleCount FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser';
    IF roleCount = 0 THEN
      INSERT INTO KRIM_ROLE_T (ROLE_ID,KIM_TYP_ID,NMSPC_CD,ROLE_NM,DESC_TXT,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
          VALUES (KRIM_ROLE_ID_BS_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),'KC-SYS','KC Superuser','KC Superuser role for administration access','Y',SYSDATE,SYS_GUID(),1);
    END IF;
    
    FOR rec IN cur 
    LOOP
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM KRIM_ROLE_PERM_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser'') AND PERM_ID = ' || rec.PERM_ID INTO rolePermCount;
        IF rolePermCount = 0 THEN
            EXECUTE IMMEDIATE 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser''),' || rec.PERM_ID || ',''Y'',SYS_GUID(),1)';
        END IF;
    END LOOP;
END;
/

DECLARE roleMbrCount NUMBER;
BEGIN
    SELECT COUNT(*) INTO roleMbrCount FROM KRIM_ROLE_MBR_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser') AND MBR_ID = (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin');

    IF roleMbrCount = 0 THEN
        INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
            VALUES (KRIM_ROLE_MBR_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin'),'P',SYSDATE,SYS_GUID(),1);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
            VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,KRIM_ROLE_MBR_ID_BS_S.CURRVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber'),'000001',SYS_GUID(),1);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
            VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,KRIM_ROLE_MBR_ID_BS_S.CURRVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits'),'Y',SYS_GUID(),1);
    END IF;
END;
/
