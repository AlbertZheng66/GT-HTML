CREATE TABLE VALIDATION_BEAN(
    STRING1          VARCHAR(64)    NOT NULL,
    STRING2          VARCHAR(64)    NOT NULL,
    TITLE            VARCHAR(64),
    DICTIONARY1      VARCHAR(64),
    LOOKUP1          VARCHAR(64),
    LOOKUP2          VARCHAR(64),
    EMAIL            VARCHAR(64),
    URL              VARCHAR(64),
    PHONE            VARCHAR(64),
    MOBILE           VARCHAR(64),
    SSN              VARCHAR(64),
    CHINESE          VARCHAR(64),
    INT1             INTEGER(10),
    LONG1            INTEGER(10),
    FLOAT1           FLOAT(10,2),
    DOUBLE1          FLOAT(10,2),
    BOOLEAN1         VARCHAR(2),
	DATE1            DATE,
    CONSTRAINT PK_VALIDATE_BEAN PRIMARY KEY (STRING1)
)
;