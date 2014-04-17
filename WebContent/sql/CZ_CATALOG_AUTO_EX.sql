-- Create table
create table CZ_CATALOG_AUTO_EX
(
  cata_id           VARCHAR2(40) not null,
  event_id          VARCHAR2(40),
  operator          VARCHAR2(20),
  save_time         VARCHAR2(14),
  eq_type           CHAR(1),
  o_time            VARCHAR2(19),
  o_time_ns         INTEGER,
  lat               NUMBER(10,5),
  lon               NUMBER(10,5),
  depth             NUMBER(12,7),
  ml                NUMBER(16,7),
  ms                NUMBER(16,7),
  md                NUMBER(16,7),
  mb                NUMBER(16,7),
  mmb               NUMBER(16,7),
  mw                NUMBER(16,7),
  m                 NUMBER(16,7),
  dmin              NUMBER(16,7),
  gap_azi           NUMBER(16,7),
  rms               NUMBER(16,7),
  erh               NUMBER(16,7),
  erz               NUMBER(16,7),
  qloc              VARCHAR2(5),
  qnet              VARCHAR2(5),
  qcom              VARCHAR2(5),
  sum_stn           INTEGER,
  used_stn          INTEGER,
  sum_pha           INTEGER,
  used_pha          INTEGER,
  explosion_flag    VARCHAR2(10),
  epic_id           VARCHAR2(5),
  source_id         VARCHAR2(5),
  location_cname    VARCHAR2(128),
  location_province VARCHAR2(40),
  location_city     VARCHAR2(40),
  location_district VARCHAR2(40),
  location_bname    VARCHAR2(128)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 2M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table CZ_CATALOG_AUTO_EX
  add constraint AUTO_CATA_ID primary key (CATA_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    minextents 1
    maxextents unlimited
  );
