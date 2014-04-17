-- Create table
create table JET_FUNCTION
(
  functionid   VARCHAR2(32) not null,
  location     VARCHAR2(256),
  title        VARCHAR2(100),
  parent       VARCHAR2(32),
  orderno      NUMBER(4) not null,
  nodetype     VARCHAR2(1) not null,
  type         VARCHAR2(1) not null,
  description  VARCHAR2(256) not null,
  log          VARCHAR2(1),
  developer    VARCHAR2(50),
  active       VARCHAR2(3),
  functiondesc VARCHAR2(500),
  auflag       CHAR(1),
  rbflag       CHAR(1) default '1',
  param1       VARCHAR2(200),
  param2       VARCHAR2(200),
  createdate   DATE,
  owner        VARCHAR2(32)
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
-- Add comments to the table 
comment on table JET_FUNCTION
  is '功能表';
-- Add comments to the columns 
comment on column JET_FUNCTION.functionid
  is '功能ID';
comment on column JET_FUNCTION.location
  is '地址连接';
comment on column JET_FUNCTION.title
  is '标题';
comment on column JET_FUNCTION.parent
  is '菜单的父节点';
comment on column JET_FUNCTION.orderno
  is '在同一级菜单中的序号';
comment on column JET_FUNCTION.nodetype
  is '菜单节点是1，菜单叶子是0，按钮是2';
comment on column JET_FUNCTION.type
  is 'widget是1，url是0';
comment on column JET_FUNCTION.log
  is '在action中是否记日志，1为记，其它不记';
comment on column JET_FUNCTION.developer
  is '开发人员';
comment on column JET_FUNCTION.active
  is '是否可用，0为不可用1为可用';
comment on column JET_FUNCTION.functiondesc
  is '功能描述';
comment on column JET_FUNCTION.param1
  is '参数1';
comment on column JET_FUNCTION.param2
  is '参数2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table JET_FUNCTION
  add primary key (FUNCTIONID)
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
