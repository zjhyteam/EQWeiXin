-- Create table
create table CODEMAINTENANCE
(
  id          NUMBER(10) not null,
  name        VARCHAR2(20),
  valid       NUMBER(1),
  code        NUMBER(20),
  description VARCHAR2(200),
  value       VARCHAR2(300),
  flag        NUMBER(1)
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
comment on column CODEMAINTENANCE.name
  is '名称';
comment on column CODEMAINTENANCE.valid
  is '有效1无效0';
comment on column CODEMAINTENANCE.code
  is '代码';
comment on column CODEMAINTENANCE.description
  is '代码描述';
comment on column CODEMAINTENANCE.value
  is '代码值';
comment on column CODEMAINTENANCE.flag
  is '1:增加，0:不增加';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CODEMAINTENANCE
  add constraint ID_PK primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
