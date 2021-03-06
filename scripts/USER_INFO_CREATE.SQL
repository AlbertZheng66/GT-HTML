CREATE TABLE USER_INFO(
    OID              VARCHAR(64)     NOT NULL,
    USER_ID          VARCHAR(32)     NOT NULL,
    GENDER           CHAR(1),
    DEGREE           CHAR(2),
    NAME             VARCHAR(64),
    SSN              VARCHAR(32),
    EMAIL            VARCHAR(128),
    BIRTHDAY         TIMESTAMP(6),
    OFFICE_NUMBER    VARCHAR(32),
    MOBILE           VARCHAR(32),
    HOME_NUMBER      VARCHAR(32),
    INSERT_TIME      TIMESTAMP(6),
    DEFAULT_ADDRESS_ID VARCHAR(32),
    PASSWD           VARCHAR(256),
    CONSTRAINT PK_USER_INFO PRIMARY KEY (OID)
)
;