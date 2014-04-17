-- Create table
create table KNOWLEDGE
(
  id           NUMBER,
  summary      VARCHAR2(200),
  content      VARCHAR2(20),
  keywordsflag NUMBER,
  keywords     VARCHAR2(50),
  title        VARCHAR2(50),
  imagename    VARCHAR2(40),
  num          NUMBER
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
comment on column KNOWLEDGE.summary
  is '摘要';
comment on column KNOWLEDGE.content
  is '内容（text文本文件名）';
comment on column KNOWLEDGE.keywordsflag
  is '关键字标记 1：启用，0：不启用';
comment on column KNOWLEDGE.keywords
  is '关键字';
comment on column KNOWLEDGE.title
  is '标题';
comment on column KNOWLEDGE.imagename
  is '图片名称';
comment on column KNOWLEDGE.num
  is '菜单序号';
-- Create/Recreate indexes 
create index KNID_PK on KNOWLEDGE (ID)
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
