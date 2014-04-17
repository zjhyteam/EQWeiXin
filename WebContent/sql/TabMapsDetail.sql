-- Create table
create table TABMAPSDETAIL
(
  mapid     VARCHAR2(40) not null,
  imagename VARCHAR2(100),
  time      VARCHAR2(20),
  address   VARCHAR2(100),
  longitude NUMBER(10,5),
  latitude  NUMBER(10,5),
  magnitude NUMBER(16,7),
  intensity NUMBER,
  depth     NUMBER(12,7)
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
comment on column TABMAPSDETAIL.imagename
  is '图片名称';
comment on column TABMAPSDETAIL.time
  is '时间';
comment on column TABMAPSDETAIL.address
  is '地点';
comment on column TABMAPSDETAIL.longitude
  is '经度';
comment on column TABMAPSDETAIL.latitude
  is '纬度';
comment on column TABMAPSDETAIL.magnitude
  is '震级';
comment on column TABMAPSDETAIL.intensity
  is '震中烈度';
comment on column TABMAPSDETAIL.depth
  is '震源深度';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TABMAPSDETAIL
  add constraint PK_MAPID primary key (MAPID)
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
