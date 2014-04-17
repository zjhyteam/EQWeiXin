-- Create table
create table GROUPSEND
(
  id        NUMBER not null,
  title     VARCHAR2(30),
  s_time    DATE,
  content   VARCHAR2(300),
  imagename VARCHAR2(100)
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
comment on column GROUPSEND.id
  is '群发信息ID';
comment on column GROUPSEND.title
  is '主题';
comment on column GROUPSEND.s_time
  is '发送时间';
comment on column GROUPSEND.content
  is '内容';
comment on column GROUPSEND.imagename
  is '图片名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GROUPSEND
  add constraint GROUPS_IDPK primary key (ID)
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
