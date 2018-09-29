#!/usr/bin/python
# -*- coding: utf-8 -*-

from ldap3 import Server, Connection, ALL, NTLM
LDAP_URI = '192.168.1.200'
LDAP_USER = 'nono\\sso'  #域和用户之间必须是双斜划线
LDAP_PASS = '2017@NONO.com'
BASE_DN = 'CN=sso,OU=nonobank,DC=server,DC=nonobank,DC=com'




server = Server(LDAP_URI, get_info=ALL)
conn = Connection(server, user=LDAP_USER, password=LDAP_PASS, authentication= NTLM)



print(conn.bind())

#返回基本信息
print(conn.extend.standard.who_am_i())

