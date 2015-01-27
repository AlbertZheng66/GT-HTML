CREATE TABLE BOOK(
    OID              VARCHAR(64)    NOT NULL,
    NAME             VARCHAR(64)    NOT NULL,
    ISDN             VARCHAR(64),
    PRICE            FLOAT,
    AUTHOR           VARCHAR(128),
    TRANSLATOR       VARCHAR(128),
    PRESS            VARCHAR(128),
    COVER            LONGVARBINARY,  -- ∑‚√Ê
	PUBLISH_DATE     DATE,
    CONSTRAINT PK_BOOK PRIMARY KEY (OID)
)
;