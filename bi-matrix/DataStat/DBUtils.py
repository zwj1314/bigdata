import pymysql
import redis

from settings import *


# 连接mysql
def getMysqlConnect():
    mysqlConn = pymysql.connect(host=mysqlArgs["host"],
                                port=mysqlArgs["port"],
                                user=mysqlArgs["user"],
                                password=mysqlArgs["password"],
                                db=mysqlArgs["db"],
                                charset=mysqlArgs["charset"])
    return mysqlConn


def getRedisConncet():
    r = redis.Redis(host=redisArgs["host"],
                    port=redisArgs["port"],
                    password=redisArgs["password"])
    return r


def getBiaConncet():
    biaConn = pymysql.connect(host=biaArgs["host"],
                              port=biaArgs["port"],
                              user=biaArgs["user"],
                              password=biaArgs["password"],
                              db=biaArgs["db"],
                              charset=biaArgs["charset"])
    return biaConn


def getNonoConncet():
    nonoConn = pymysql.connect(host=nonoArgs["host"],
                              port=nonoArgs["port"],
                              user=nonoArgs["user"],
                              password=nonoArgs["password"],
                              db=nonoArgs["db"],
                              charset=nonoArgs["charset"])
    return nonoConn


def getMemberConncet():
    memberConncet = pymysql.connect(host=memberArgs["host"],
                               port=memberArgs["port"],
                               user=memberArgs["user"],
                               password=memberArgs["password"],
                               db=memberArgs["db"],
                               charset=memberArgs["charset"])
    return memberConncet