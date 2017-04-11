-----------------------------------------------
-- Export file for user WXDB@WXDB            --
-- Created by AVIN_PC on 2017/4/11, 23:48:52 --
-----------------------------------------------

set define off
spool wxdb.log

prompt
prompt Creating table LOGINUSER
prompt ========================
prompt
create table WXDB.LOGINUSER
(
  id         INTEGER not null,
  loginname  VARCHAR2(20) not null,
  loginpsw   VARCHAR2(100) not null,
  roleid     INTEGER not null,
  wxaccounts VARCHAR2(50),
  isdeleted  CHAR(1) default 0 not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table WXDB.LOGINUSER
  add constraint PK_LOGINUSER_ID primary key (ID)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table OFFICIALACCOUNT
prompt ==============================
prompt
create table WXDB.OFFICIALACCOUNT
(
  id          INTEGER not null,
  accountname NVARCHAR2(20) not null,
  accountnum  VARCHAR2(100) not null,
  appid       VARCHAR2(100) not null,
  secret      VARCHAR2(100) not null,
  isdeleted   CHAR(1) default 0 not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column WXDB.OFFICIALACCOUNT.accountname
  is '公众号名称';
comment on column WXDB.OFFICIALACCOUNT.accountnum
  is '公众号账号';
comment on column WXDB.OFFICIALACCOUNT.appid
  is 'APPID';
comment on column WXDB.OFFICIALACCOUNT.secret
  is 'SECRET';
comment on column WXDB.OFFICIALACCOUNT.isdeleted
  is '删除标识';
alter table WXDB.OFFICIALACCOUNT
  add constraint PK_ACCOUNTS_ID primary key (ID)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;
alter table WXDB.OFFICIALACCOUNT
  add constraint UQ_ACCOUNTS_ACCOUNTNUM unique (ACCOUNTNUM)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table RIGHTGROUP
prompt =========================
prompt
create table WXDB.RIGHTGROUP
(
  title     NVARCHAR2(20) not null,
  ordernum  INTEGER not null,
  groupcode VARCHAR2(20) not null,
  id        INTEGER not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255;
alter table WXDB.RIGHTGROUP
  add constraint PK_RIGHTGROUP_ID primary key (ID)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;
alter table WXDB.RIGHTGROUP
  add constraint UQ_RIGHTGROUP_GROUPCODE unique (GROUPCODE)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table USERRIGHT
prompt ========================
prompt
create table WXDB.USERRIGHT
(
  id              INTEGER not null,
  groupcode       INTEGER not null,
  title           NVARCHAR2(20) not null,
  url             NVARCHAR2(100) not null,
  displayinbanner CHAR(1) not null,
  ordernum        INTEGER not null,
  isdeleted       CHAR(1) not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255;
comment on column WXDB.USERRIGHT.groupcode
  is '权限分组代码';
comment on column WXDB.USERRIGHT.title
  is '权限名称';
comment on column WXDB.USERRIGHT.url
  is '权限对应url';
comment on column WXDB.USERRIGHT.displayinbanner
  is '是否在导航显示';
comment on column WXDB.USERRIGHT.ordernum
  is '排序';
comment on column WXDB.USERRIGHT.isdeleted
  is '是否删除';
alter table WXDB.USERRIGHT
  add constraint PK_USERRIGHT_ID primary key (ID)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table USERROLE
prompt =======================
prompt
create table WXDB.USERROLE
(
  id         INTEGER not null,
  title      NVARCHAR2(20) not null,
  userrights VARCHAR2(200),
  ordernum   INTEGER not null,
  rolecode   VARCHAR2(20) not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255;
alter table WXDB.USERROLE
  add constraint PK_USERROLE_ID primary key (ID)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;
alter table WXDB.USERROLE
  add constraint UQ_USERROLE_ROLECODE unique (ROLECODE)
  using index 
  tablespace WXDB
  pctfree 10
  initrans 2
  maxtrans 255;

prompt
prompt Creating table VISITWXSERVLOG
prompt =============================
prompt
create table WXDB.VISITWXSERVLOG
(
  accountnum    VARCHAR2(100) not null,
  requestflag   VARCHAR2(50) not null,
  requesturl    NVARCHAR2(1000) not null,
  requestmethod VARCHAR2(10) not null,
  postdata      NVARCHAR2(1000) not null,
  responsedata  NVARCHAR2(1000) not null,
  visitdate     DATE not null,
  module        VARCHAR2(50) not null,
  operation     VARCHAR2(50)
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column WXDB.VISITWXSERVLOG.accountnum
  is '微信号';
comment on column WXDB.VISITWXSERVLOG.requestflag
  is '访问标识';
comment on column WXDB.VISITWXSERVLOG.requesturl
  is '访问url';
comment on column WXDB.VISITWXSERVLOG.requestmethod
  is '访问方法';
comment on column WXDB.VISITWXSERVLOG.postdata
  is '提交数据';
comment on column WXDB.VISITWXSERVLOG.responsedata
  is '响应数据';
comment on column WXDB.VISITWXSERVLOG.visitdate
  is '访问时间';
comment on column WXDB.VISITWXSERVLOG.module
  is '模块';
comment on column WXDB.VISITWXSERVLOG.operation
  is '操作';

prompt
prompt Creating table WXGROUP
prompt ======================
prompt
create table WXDB.WXGROUP
(
  accountnum VARCHAR2(100) not null,
  groupid    INTEGER not null,
  groupname  NVARCHAR2(20) not null,
  usercount  INTEGER not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255;
comment on column WXDB.WXGROUP.accountnum
  is '微信公众号';
comment on column WXDB.WXGROUP.groupid
  is '微信分组id';
comment on column WXDB.WXGROUP.groupname
  is '微信分组名称';
comment on column WXDB.WXGROUP.usercount
  is '微信分组人数';

prompt
prompt Creating table WXSERVCALLBACKLOG
prompt ================================
prompt
create table WXDB.WXSERVCALLBACKLOG
(
  accountnum   VARCHAR2(100),
  openid       VARCHAR2(100),
  httpmethod   VARCHAR2(10) not null,
  url          NVARCHAR2(500) not null,
  postdata     NVARCHAR2(1000),
  responsedata NVARCHAR2(1000),
  msgtype      VARCHAR2(50) not null,
  event        VARCHAR2(20),
  visitdate    DATE not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WXTEMPLATE
prompt =========================
prompt
create table WXDB.WXTEMPLATE
(
  accountnum      VARCHAR2(100) not null,
  templateid      VARCHAR2(100) not null,
  templatetitle   NVARCHAR2(20) not null,
  primaryindustry NVARCHAR2(20) not null,
  deputyindustry  NVARCHAR2(20) not null,
  templatecontent NVARCHAR2(500) not null,
  templateexample NVARCHAR2(500)
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column WXDB.WXTEMPLATE.accountnum
  is '微信号';
comment on column WXDB.WXTEMPLATE.templateid
  is '微信模板id';
comment on column WXDB.WXTEMPLATE.templatetitle
  is '微信模板标题';
comment on column WXDB.WXTEMPLATE.primaryindustry
  is '一级行业';
comment on column WXDB.WXTEMPLATE.deputyindustry
  is '二级行业';
comment on column WXDB.WXTEMPLATE.templatecontent
  is '模板内容';
comment on column WXDB.WXTEMPLATE.templateexample
  is '模板例子';

prompt
prompt Creating table WXUSER
prompt =====================
prompt
create table WXDB.WXUSER
(
  accountnum     VARCHAR2(100) not null,
  subscribe      CHAR(1) not null,
  openid         VARCHAR2(100),
  nickname       NVARCHAR2(20),
  sex            CHAR(1),
  city           NVARCHAR2(20),
  province       NVARCHAR2(20),
  country        NVARCHAR2(20),
  language       VARCHAR2(20),
  headimgurl     NVARCHAR2(500),
  subscribe_time NUMBER(10),
  unionid        VARCHAR2(100),
  remark         NVARCHAR2(20),
  groupid        INTEGER
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column WXDB.WXUSER.accountnum
  is '公众号';
comment on column WXDB.WXUSER.subscribe
  is '是否关注';
comment on column WXDB.WXUSER.openid
  is '加密的微信号';
comment on column WXDB.WXUSER.nickname
  is '昵称';
comment on column WXDB.WXUSER.sex
  is '性别';
comment on column WXDB.WXUSER.city
  is '城市';
comment on column WXDB.WXUSER.province
  is '省份';
comment on column WXDB.WXUSER.country
  is '国家';
comment on column WXDB.WXUSER.language
  is '用户语言';
comment on column WXDB.WXUSER.headimgurl
  is '用户头像url';
comment on column WXDB.WXUSER.subscribe_time
  is '用户关注时间';
comment on column WXDB.WXUSER.remark
  is '备注';
comment on column WXDB.WXUSER.groupid
  is '分组id';

prompt
prompt Creating table WXUSERBAK
prompt ========================
prompt
create table WXDB.WXUSERBAK
(
  accountnum     VARCHAR2(100) not null,
  subscribe      CHAR(1) not null,
  openid         VARCHAR2(100),
  nickname       NVARCHAR2(20),
  sex            CHAR(1),
  city           NVARCHAR2(20),
  province       NVARCHAR2(20),
  country        NVARCHAR2(20),
  language       VARCHAR2(20),
  headimgurl     NVARCHAR2(500),
  subscribe_time NUMBER(10),
  unionid        VARCHAR2(100),
  remark         NVARCHAR2(20),
  groupid        INTEGER,
  batch          INTEGER not null
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on column WXDB.WXUSERBAK.accountnum
  is '公众号';
comment on column WXDB.WXUSERBAK.subscribe
  is '是否关注';
comment on column WXDB.WXUSERBAK.openid
  is '加密的微信号';
comment on column WXDB.WXUSERBAK.nickname
  is '昵称';
comment on column WXDB.WXUSERBAK.sex
  is '性别';
comment on column WXDB.WXUSERBAK.city
  is '城市';
comment on column WXDB.WXUSERBAK.province
  is '省份';
comment on column WXDB.WXUSERBAK.country
  is '国家';
comment on column WXDB.WXUSERBAK.language
  is '用户语言';
comment on column WXDB.WXUSERBAK.headimgurl
  is '用户头像url';
comment on column WXDB.WXUSERBAK.subscribe_time
  is '用户关注时间';
comment on column WXDB.WXUSERBAK.remark
  is '备注';
comment on column WXDB.WXUSERBAK.groupid
  is '分组id';
comment on column WXDB.WXUSERBAK.batch
  is '备份批次';

prompt
prompt Creating table WXUSEREX
prompt =======================
prompt
create table WXDB.WXUSEREX
(
  accountnum VARCHAR2(100) not null,
  openid     VARCHAR2(100) not null,
  wxnum      VARCHAR2(100),
  phone      VARCHAR2(20)
)
tablespace WXDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence SEQ_AUTOINC
prompt =============================
prompt
create sequence WXDB.SEQ_AUTOINC
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating package PACK_OFFICIALACCOUNT
prompt =====================================
prompt
create or replace package wxdb.pack_officialaccount as
  procedure InsertOrUpdate(p_id          in integer,
                           p_accountname in nvarchar2,
                           p_accountnum  in varchar2,
                           p_appid       in varchar2,
                           p_secret      in varchar2,
                           p_rowcount    out integer);
  procedure Remove(p_id in integer, p_rowcount out integer);
end pack_officialaccount;
/

prompt
prompt Creating package PACK_VISITWXSERVLOG
prompt ====================================
prompt
create or replace package wxdb.pack_visitwxservlog as

  procedure InsertData(p_accountnum    in varchar2,
                       p_requestflag   in varchar2,
                       p_requesturl    in nvarchar2,
                       p_requestmethod in varchar2,
                       p_postdata      in nvarchar2,
                       p_responsedata  in nvarchar2,
                       p_visitdate     in date,
                       p_module        in varchar2,
                       p_operation     in varchar2,
                       p_rowcount      out integer);

end pack_visitwxservlog;
/

prompt
prompt Creating package PACK_WXGROUP
prompt =============================
prompt
create or replace package wxdb.pack_wxgroup as

  procedure BatchMoveToGroup(p_accountnum in varchar2,
                             p_openids    in varchar2,
                             p_togroupid  in integer,
                             p_rowcount   out integer);
  procedure Remove(p_accountnum in varchar2,
                   p_groupid    in varchar2,
                   p_rowcount   out integer);

  procedure InsertOrUpdate(p_isinsert   in char,
                           p_accountnum in varchar2,
                           p_groupid    in integer,
                           p_groupname  in nvarchar2,
                           p_usercount  in integer,
                           p_rowcount   out integer);

end pack_wxgroup;
/

prompt
prompt Creating package PACK_WXSERVCALLBACKLOG
prompt =======================================
prompt
create or replace package wxdb.pack_wxservcallbacklog as

  procedure InsertData(p_accountnum   varchar2,
                       p_openid       varchar2,
                       p_httpmethod   varchar2,
                       p_url          nvarchar2,
                       p_postdata     nvarchar2,
                       p_responsedata nvarchar2,
                       p_msgtype      varchar2,
                       p_event        varchar2,
                       p_visitdate    date,
                       p_rowcount     out integer);

end pack_wxservcallbacklog;
/

prompt
prompt Creating package PACK_WXTEMPLATE
prompt ================================
prompt
create or replace package wxdb.pack_wxtemplate as

  procedure InsertData(p_accountnum      in varchar,
                       p_templateid      in varchar2,
                       p_templatetitle   in nvarchar2,
                       p_primaryindustry in nvarchar2,
                       p_deputyindustry  in nvarchar2,
                       p_templatecontent in nvarchar2,
                       p_templateexample in nvarchar2,
                       p_rowcount        out integer);

end pack_wxtemplate;
/

prompt
prompt Creating package PACK_WXUSER
prompt ============================
prompt
create or replace package wxdb.pack_wxuser as

  type cur_type is ref cursor; --定义游标类型  

  procedure BackUp(p_accountnum in varchar2, p_rowcount out integer);

  procedure InsertOrUpdate(p_accountnum     in varchar2,
                           p_subscribe      in char,
                           p_openid         in varchar2,
                           p_nickname       in nvarchar2,
                           p_sex            in char,
                           p_city           in nvarchar2,
                           p_province       in nvarchar2,
                           p_country        in nvarchar2,
                           p_language       in nvarchar2,
                           p_headimgurl     in nvarchar2,
                           p_subscribe_time in number,
                           p_unionid        in varchar2,
                           p_remark         in nvarchar2,
                           p_groupid        in integer,
                           p_rowcount       out integer);

  procedure SelectByWhere(p_accountnum  in varchar,
                          p_groupid     in integer,
                          p_pageindex   in integer,
                          p_pagesize    in integer,
                          p_recordcount out integer);

  procedure SetSubscribeFalse(p_accountnum in varchar2,
                              p_openid     in varchar2,
                              p_rowcount   out integer);

end pack_wxuser;
/

prompt
prompt Creating type STR_SPLIT
prompt =======================
prompt
CREATE OR REPLACE TYPE WXDB.str_split IS TABLE OF VARCHAR2 (4000)
/

prompt
prompt Creating function SPLITSTR
prompt ==========================
prompt
CREATE OR REPLACE FUNCTION WXDB.splitstr(p_string IN VARCHAR2, p_delimiter IN VARCHAR2)
    RETURN str_split
    PIPELINED
AS
    v_length   NUMBER := LENGTH(p_string);
    v_start    NUMBER := 1;
    v_index    NUMBER;
BEGIN
    WHILE(v_start <= v_length)
    LOOP
        v_index := INSTR(p_string, p_delimiter, v_start);

        IF v_index = 0
        THEN
            PIPE ROW(SUBSTR(p_string, v_start));
            v_start := v_length + 1;
        ELSE
            PIPE ROW(SUBSTR(p_string, v_start, v_index - v_start));
            v_start := v_index + 1;
        END IF;
    END LOOP;

    RETURN;
END splitstr;
/

prompt
prompt Creating package body PACK_OFFICIALACCOUNT
prompt ==========================================
prompt
create or replace package body wxdb.pack_officialaccount as

  procedure InsertOrUpdate(p_id          in integer,
                           p_accountname in nvarchar2,
                           p_accountnum  in varchar2,
                           p_appid       in varchar2,
                           p_secret      in varchar2,
                           p_rowcount    out integer) is
  begin
    if p_id > 0 then
      begin
        update officialaccount
           set accountname = p_accountname,
               appid       = p_appid,
               secret      = p_secret
         where id = p_id
           and isdeleted = 0;
        commit;
        p_rowcount := sql%rowcount;
      end;
    else
      begin
        insert into officialaccount
          (accountname, accountnum, appid, secret)
        values
          (p_accountname, p_accountnum, p_appid, p_secret);
        commit;
        select max(id) into p_rowcount from officialaccount;
      end;
    end if;
  exception
    when Dup_val_on_index then
      begin
        dbms_output.put_line('违反唯一性约束');
        p_rowcount := 10001;
      end;
  end;

  procedure Remove(p_id in integer, p_rowcount out integer) is
  begin
    update officialaccount
       set isdeleted = 1
     where id = p_id
       and isdeleted = 0;
    commit;
    p_rowcount := sql%rowcount;
  end;

end pack_officialaccount;
/

prompt
prompt Creating package body PACK_VISITWXSERVLOG
prompt =========================================
prompt
create or replace package body wxdb.pack_visitwxservlog as

  procedure InsertData(p_accountnum    in varchar2,
                       p_requestflag   in varchar2,
                       p_requesturl    in nvarchar2,
                       p_requestmethod in varchar2,
                       p_postdata      in nvarchar2,
                       p_responsedata  in nvarchar2,
                       p_visitdate     in date,
                       p_module        in varchar2,
                       p_operation     in varchar2,
                       p_rowcount      out integer) is
  begin
    insert into visitwxservlog
      (accountnum,
       requestflag,
       requesturl,
       requestmethod,
       postdata,
       responsedata,
       visitdate,
       module,
       operation)
    values
      (p_accountnum,
       p_requestflag,
       p_requesturl,
       p_requestmethod,
       p_postdata,
       p_responsedata,
       p_visitdate,
       p_module,
       p_operation);
    commit;
    p_rowcount := sql%rowcount;
  end;

end pack_visitwxservlog;
/

prompt
prompt Creating package body PACK_WXGROUP
prompt ==================================
prompt
create or replace package body wxdb.pack_wxgroup as

  procedure BatchMoveToGroup(p_accountnum in varchar2,
                             p_openids    in varchar2,
                             p_togroupid  in integer,
                             p_rowcount   out integer) is
    v_groupid integer;
    cursor cur_openid is
      select * from table(splitstr(p_openids, ','));
  begin
    p_rowcount := 0;
    for c_row in cur_openid loop
      begin
        p_rowcount := p_rowcount + 1;
        savepoint a;
        select groupid
          into v_groupid
          from wxuser
         where accountnum = p_accountnum
           and openid = c_row.COLUMN_VALUE
           and groupid != p_togroupid;
        update wxuser
           set groupid = p_togroupid
         where accountnum = p_accountnum
           and openid = v_groupid;
        update wxgroup
           set usercount = usercount - 1
         where accountnum = p_accountnum
           and groupid = v_groupid;
        update wxgroup
           set usercount = usercount + 1
         where accountnum = p_accountnum
           and groupid = p_togroupid;
        commit;
      exception
        when no_data_found then
          p_rowcount := p_rowcount - 1;
          dbms_output.put_line('没找到对应数据');
        when others then
          p_rowcount := p_rowcount - 1;
          rollback to a;
      end;
    end loop;
  
  end;

  procedure Remove(p_accountnum in varchar2,
                   p_groupid    in varchar2,
                   p_rowcount   out integer) is
    v_count integer;
  begin
  
    select usercount
      into v_count
      from wxgroup
     where accountnum = p_accountnum
       and groupid = p_groupid;
    update wxgroup
       set usercount = usercount + v_count
     where accountnum = p_accountnum
       and groupid = 0;
    update wxuser
       set groupid = 0
     where accountnum = p_accountnum
       and groupid = p_groupid;
    delete wxgroup
     where accountnum = p_accountnum
       and groupid = p_groupid;
    commit;
    p_rowcount := sql%rowcount;
  exception
    when no_data_found then
      dbms_output.put_line('没找到对应数据');
    when others then
      rollback;
  end;

  procedure InsertOrUpdate(p_isinsert   in char,
                           p_accountnum in varchar2,
                           p_groupid    in integer,
                           p_groupname  in nvarchar2,
                           p_usercount  in integer,
                           p_rowcount   out integer) is
  begin
    if p_isinsert = 1 then
      begin
        insert into wxgroup
          (accountnum, groupid, groupname, usercount)
        values
          (p_accountnum, p_groupid, p_groupname, p_usercount);
      end;
    elsif p_isinsert = 0 then
      begin
        update wxgroup
           set groupname = p_groupname
         where accountnum = p_accountnum
           and groupid = p_groupid;
      end;
    end if;
    commit;
    p_rowcount := SQL%ROWCOUNT;
  end;

end pack_wxgroup;
/

prompt
prompt Creating package body PACK_WXSERVCALLBACKLOG
prompt ============================================
prompt
create or replace package body wxdb.pack_wxservcallbacklog as

  procedure InsertData(p_accountnum   in varchar2,
                       p_openid       in varchar2,
                       p_httpmethod   in varchar2,
                       p_url          in nvarchar2,
                       p_postdata     in nvarchar2,
                       p_responsedata in nvarchar2,
                       p_msgtype      in varchar2,
                       p_event        in varchar2,
                       p_visitdate    in date,
                       p_rowcount     out integer) is
  begin
    insert into wxservcallbacklog
      (
       
       accountnum,
       openid,
       httpmethod,
       url,
       postdata,
       responsedata,
       msgtype,
       event,
       visitdate
       
       )
    values
      (p_accountnum,
       p_openid,
       p_httpmethod,
       p_url,
       p_postdata,
       p_responsedata,
       p_msgtype,
       p_event,
       p_visitdate);
    commit;
    p_rowcount := sql%rowcount;
  end;

end pack_wxservcallbacklog;
/

prompt
prompt Creating package body PACK_WXTEMPLATE
prompt =====================================
prompt
create or replace package body wxdb.pack_wxtemplate as

  procedure InsertData(p_accountnum      in varchar,
                       p_templateid      in varchar2,
                       p_templatetitle   in nvarchar2,
                       p_primaryindustry in nvarchar2,
                       p_deputyindustry  in nvarchar2,
                       p_templatecontent in nvarchar2,
                       p_templateexample in nvarchar2,
                       p_rowcount        out integer) is
  begin
    insert into wxtemplate
      (accountnum,
       templateid,
       templatetitle,
       primaryindustry,
       deputyindustry,
       templatecontent,
       templateexample)
    values
      (p_accountnum,
       p_templateid,
       p_templatetitle,
       p_primaryindustry,
       p_deputyindustry,
       p_templatecontent,
       p_templateexample);
       commit;
       p_rowcount := sql%rowcount;
  end;

end pack_wxtemplate;
/

prompt
prompt Creating package body PACK_WXUSER
prompt =================================
prompt
create or replace package body wxdb.pack_wxuser as

  procedure BackUp(p_accountnum in varchar2, p_rowcount out integer) is
    v_batch integer;
  begin
    select nvl(max('batch'), 0) into v_batch from wxuserbak;
    v_batch := v_batch + 1;
    insert into wxuserbak
      select t.*, v_batch batch
        from wxuser t
       where t.accountnum = p_accountnum;
    delete from wxuser where accountnum = p_accountnum;
    commit;
    p_rowcount := sql%rowcount;
  exception
    when others then
      rollback;
  end;

  procedure InsertOrUpdate(p_accountnum     in varchar2,
                           p_subscribe      in char,
                           p_openid         in varchar2,
                           p_nickname       in nvarchar2,
                           p_sex            in char,
                           p_city           in nvarchar2,
                           p_province       in nvarchar2,
                           p_country        in nvarchar2,
                           p_language       in nvarchar2,
                           p_headimgurl     in nvarchar2,
                           p_subscribe_time in number,
                           p_unionid        in varchar2,
                           p_remark         in nvarchar2,
                           p_groupid        in integer,
                           p_rowcount       out integer) is
    v_count integer;
  
  begin
    select count(t.accountnum)
      into v_count
      from wxuser t
     where t.accountnum = p_accountnum
       and t.openid = p_openid;
    if v_count > 0 then
      begin
        update wxuser
           set subscribe      = p_subscribe,
               nickname       = p_nickname,
               sex            = p_sex,
               city           = p_city,
               province       = p_province,
               country        = p_country,
               language       = p_language,
               headimgurl     = p_headimgurl,
               subscribe_time = p_subscribe_time,
               unionid        = p_unionid,
               remark         = p_remark,
               groupid        = p_groupid
         where accountnum = p_accountnum
           and openid = p_openid;
      end;
    else
      begin
        insert into wxuser
          (accountnum,
           subscribe,
           openid,
           nickname,
           sex,
           city,
           province,
           country,
           language,
           headimgurl,
           subscribe_time,
           unionid,
           remark,
           groupid)
        values
          (p_accountnum,
           p_subscribe,
           p_openid,
           p_nickname,
           p_sex,
           p_city,
           p_province,
           p_country,
           p_language,
           p_headimgurl,
           p_subscribe_time,
           p_unionid,
           p_remark,
           p_groupid);
      end;
    end if;
    commit;
    p_rowcount := sql%rowcount;
  end;

  procedure SelectByWhere(p_accountnum  in varchar,
                          p_groupid     in integer,
                          p_pageindex   in integer,
                          p_pagesize    in integer,
                          p_recordcount out integer) is
    v_result       cur_type;
    v_select       varchar2(300);
    v_recountcount varchar2(200);
  begin
    v_select       := 'select t.* from wxuser t where 1=1';
    v_recountcount := 'select count(0) from wxuser where 1=1';
    if p_accountnum is not null and length(p_accountnum) > 0 then
      begin
        v_recountcount := v_recountcount || ' and accountnum = ''' ||
                          p_accountnum || '''';
        v_select       := v_select || ' and t.accountnum = ''' ||
                          p_accountnum || '''';
      end;
    end if;
    if p_groupid is not null and p_groupid > 0 then
      begin
        v_recountcount := v_recountcount || ' and groupid = ' || p_groupid;
        v_select       := v_select || ' and t.groupid = ' || p_groupid;
      end;
    end if;
    execute immediate v_recountcount
      into p_recordcount;
    v_select := v_select || ' order by t.subscribe_time desc';
    if p_pageindex is not null and p_pageindex > 0 and
       p_pagesize is not null and p_pagesize > 0 then
      begin
        v_select := 'select ttt.* ,g.groupname as groupname from (select tt.*, rownum as rowno from (' ||
                    v_select || ') tt where rownum <= ' ||
                    (p_pageindex * p_pagesize) ||
                    ') ttt left join wxgroup g on ttt.groupid = g.groupid where ttt.rowno >= ' ||
                    ((p_pageindex - 1) * p_pagesize);
      end;
    end if;
    open v_result for v_select;
  end;

  procedure SetSubscribeFalse(p_accountnum in varchar2,
                              p_openid     in varchar2,
                              p_rowcount   out integer) is
    v_groupid integer;
  begin
    select groupid
      into v_groupid
      from wxuser
     where accountnum = p_accountnum
       and openid = p_openid;
    update wxgroup
       set usercount = usercount - 1
     where accountnum = p_accountnum
       and groupid = v_groupid;
    update wxuser
       set subscribe = 0
     where accountnum = p_accountnum
       and openid = p_openid;
    commit;
    p_rowcount := sql%rowcount;
  exception
    when others then
      rollback;
  end;

end pack_wxuser;
/

prompt
prompt Creating trigger TRG_LOGINUSER_INSERT
prompt =====================================
prompt
CREATE OR REPLACE TRIGGER WXDB.
TRG_LOGINUSER_INSERT
  BEFORE INSERT ON LOGINUSER
  FOR EACH ROW
  
BEGIN
  SELECT seq_autoinc.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

prompt
prompt Creating trigger TRG_OFFICIALACCOUNT_INSERT
prompt ===========================================
prompt
CREATE OR REPLACE TRIGGER WXDB.
TRG_officialaccount_Insert
  BEFORE INSERT ON officialaccount
  FOR EACH ROW
  
BEGIN
  SELECT seq_autoinc.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

prompt
prompt Creating trigger TRG_RIGHTGROUP_INSERT
prompt ======================================
prompt
CREATE OR REPLACE TRIGGER WXDB.
TRG_rightgroup_Insert
  BEFORE INSERT ON rightgroup
  FOR EACH ROW
  
BEGIN
  SELECT seq_autoinc.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

prompt
prompt Creating trigger TRG_USERRIGHT_INSERT
prompt =====================================
prompt
CREATE OR REPLACE TRIGGER WXDB.
TRG_userright_Insert
  BEFORE INSERT ON userright
  FOR EACH ROW
  
BEGIN
  SELECT seq_autoinc.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

prompt
prompt Creating trigger TRG_USERROLE_INSERT
prompt ====================================
prompt
CREATE OR REPLACE TRIGGER WXDB.
TRG_userrole_Insert
  BEFORE INSERT ON userrole
  FOR EACH ROW
  
BEGIN
  SELECT seq_autoinc.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/


spool off
