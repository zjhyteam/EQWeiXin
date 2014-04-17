-- Create table
create table TABAREA
(
  cata_id  VARCHAR2(40),
  level_id NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TABAREA.level_id
  is '1：国外2：中国 3：浙江';