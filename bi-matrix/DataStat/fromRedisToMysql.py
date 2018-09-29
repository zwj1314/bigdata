import datetime
import re
import time

from DBUtils import *


# 获得N分钟前的时间戳
def getBeforNMinTimeStamp(NMin):
    return int(round(time.time() * 1000)) - 60000 * NMin


# 建立Redis连接
redisConn = getRedisConncet()
mysqlConn = getMysqlConnect()
# 当天日期,格式为YMD  获得13位当前时间的时间戳和10分钟前时间戳
startTime = getBeforNMinTimeStamp(nMinAgo)
endTime = int(round(time.time() * 1000))
todayDate = datetime.datetime.now().strftime("%Y%m%d")


def fromRedisToMysql(redisConn=redisConn,
                     mysqlConn=mysqlConn,
                     startTime=startTime,
                     endTime=endTime,
                     todayDate=todayDate):
    # 从redis读取所有的key
    todayKeys = redisConn.keys("*" + todayDate)
    todayKeys = list(map(lambda x: str(x, encoding="utf-8"), todayKeys))
    platformNameList=list(filter(lambda x: len(re.findall(".*_(.*)_.*", x))>0,todayKeys))
    platformNames = set(list(map(lambda x: re.findall(".*_(.*)_.*", x)[0], platformNameList)))
    platcormCollects=list(filter(lambda x: len(re.findall("((?:deviceid|userid)_\d{6})",x))>0,todayKeys))

    for platformName in platformNames:
        redisDeviceidValue = redisConn.zcount("deviceid" + "_" + platformName + "_" + todayDate, startTime, endTime)
        redisUseridValue = redisConn.zcount("userid" + "_" + platformName + "_" + todayDate, startTime, endTime)

        request_date2 = datetime.datetime.now().strftime("%Y-%m-%d")
        timeField = datetime.datetime.now()
        createTimeField = datetime.datetime.now()
        updateTimeField = datetime.datetime.now()

        # 需要插入的数据 day->request_date time->timeField platform_name->platformName user_count->redisUseridValue device_count-> redisDeviceidValue
        # 数据插入mysql
        cur = mysqlConn.cursor()
        insertSql = """INSERT INTO BEHAVIOR.access_day (day,time,platform_name,user_count,device_count,create_date,update_date) VALUES ('{}','{}','{}',{},{},'{}','{}')""".format(
            request_date2, timeField, platformName, redisUseridValue, redisDeviceidValue, createTimeField,
            updateTimeField)
        cur.execute(insertSql)
        mysqlConn.commit()

    # 计算公司userid和deviceid数量
    for platcormCollect in platcormCollects:
        deviceidCount=redisConn.zcount(platcormCollect, startTime, endTime)
        dimension=re.findall("(\w+)_\d{6}",platcormCollect)[0]+"_count"
        request_date2 = datetime.datetime.now().strftime("%Y-%m-%d")
        timeField = datetime.datetime.now()
        createTimeField = datetime.datetime.now()
        updateTimeField = datetime.datetime.now()

        cur = mysqlConn.cursor()
        insertSql = """INSERT INTO BEHAVIOR.access_dimension (day,time,dimension,value,create_date,update_date) VALUES ('{}','{}','{}',{},'{}','{}')""".format(
            request_date2, timeField, dimension, deviceidCount, createTimeField,updateTimeField)
        cur.execute(insertSql)
        mysqlConn.commit()

if __name__ == '__main__':
    fromRedisToMysql()
