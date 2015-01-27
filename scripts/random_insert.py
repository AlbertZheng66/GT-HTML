# -*- coding: utf-8 -*-

import sys
reload(sys)
sys.setdefaultencoding('utf-8')


import os
import codecs

#db = pg.connect(dbname = 'gt_demo', host = 'localhost', user='gt_demo', passwd='gt_demo')



#insertSql = u"INSERT INTO FILTER_BEAN(STRING1, LONG1, FLOAT1, DOUBLE1, INT1, DICTIONARY1, LOOKUP1, LOOKUP2, TITLE)";
#insertSql += u"               VALUES ('STR-%d', %d, %d.%d, %d.%d, %d, 'DIC-%d', 'LookUp1-%d', 'LookUp2-%d', 'TITLE-%d'); \n "
insertSql = u"INSERT INTO USER_INFO(OID, USER_ID, GENDER, DEGREE, NAME, SSN, EMAIL, BIRTHDAY, OFFICE_NUMBER, MOBILE, INSERT_TIME)";
insertSql += u"           VALUES('%d',  '0000-%d', 'F',   '00%d',  '测试用户-%d', '0', 'aa@bb.com', now(), '88888888', '13888888888', now()); \n "


totalRow = 2000
targetFile = codecs.open('USER_INFO_DATA.sql', 'w')
for index in range(totalRow):
    #targetFile.write(insertSql % (index, index, index, index, index, index, index, index, index, index, index));
	targetFile.write(insertSql % (index, index, index%5, index));
    #db.query(insertSql % (index, index, index, index, index, index, index, index, index, index, index))

# db.close()
