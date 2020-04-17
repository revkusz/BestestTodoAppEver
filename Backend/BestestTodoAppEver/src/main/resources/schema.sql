

create table USERS
(
    USERNAME VARCHAR_IGNORECASE(50)  not null
        primary key,
    PASSWORD VARCHAR_IGNORECASE(500) not null,
    ENABLED  BOOLEAN                 not null
);

create table AUTHORITIES
(
    ID       BIGINT not null AUTO_INCREMENT
        primary key,
    USERNAME  VARCHAR_IGNORECASE(50) not null,
    AUTHORITY VARCHAR_IGNORECASE(50) not null,
    constraint FK_AUTHORITIES_USERS
        foreign key (USERNAME) references USERS (USERNAME)
);

create unique index IX_AUTH_USERNAME
    on AUTHORITIES (USERNAME, AUTHORITY);

create table CATEGORY
(
    ID       BIGINT not null AUTO_INCREMENT
        primary key,
    COLOR    VARCHAR(255),
    DELETED  BOOLEAN,
    NAME     VARCHAR(255),
    SHOW_ALL BOOLEAN,
    OWNER_ID VARCHAR_IGNORECASE(50),
    constraint FK7J5DLRHTHV9Y4PEE8FTYD4DF2
        foreign key (OWNER_ID) references USERS (USERNAME)
);

create table TODO_ITEM
(
    ID          BIGINT not null AUTO_INCREMENT
        primary key,
    CREATED     TIMESTAMP,
    DELETED     BOOLEAN,
    DONE        BOOLEAN,
    DONE_TIME   TIMESTAMP,
    MESSAGE     VARCHAR(255),
    CATEGORY_ID BIGINT,
    OWNER_ID    VARCHAR_IGNORECASE(50),
    constraint FKJLOCFC12VMIHUGKENKIDPWEMC
        foreign key (CATEGORY_ID) references CATEGORY (ID),
    constraint FKP1R9B6T1X5X2EYBOV7SM708N1
        foreign key (OWNER_ID) references USERS (USERNAME)
);
