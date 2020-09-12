# PgAdmissionSystem
Initial port :: 8080
Aust, ICT-Center, 2020


#database setUp commands

sqlplus /nolog 
connect / as sysdba 
#for oracle  18C run the following command 
alter session set "_ORACLE_SCRIPT"=true;

#if user is already created 
DROP USER DB_IUMS_PG_ADMISSION CASCADE;

create tablespace DB_IUMS_PG_ADMISSION_TS datafile ' DB_IUMS_PG_ADMISSION_TS_TABLESPACE.dat'size 10M autoextend on;

create user DB_IUMS_PG_ADMISSION identified by ig100 default tablespace DB_IUMS_PG_ADMISSION_TS;

GRANT ALL PRIVILEGES TO DB_IUMS_PG_ADMISSION;