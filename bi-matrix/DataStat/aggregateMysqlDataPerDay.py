import datetime

from DBUtils import *

# 建立数据库连接
mysqlConn = getMysqlConnect()
cur = mysqlConn.cursor()
# 建立Redis连接
redisConn = getRedisConncet()

# 获取昨日数据统计
insertSql = """
select platform_name,sum(user_count) user_count,sum(device_count) device_count
from BEHAVIOR.access_day where day=DATE_SUB(CURDATE(), INTERVAL 1 DAY)  and substring(time,16,1) = "0"
group by platform_name
"""
cur.execute(insertSql)
resultSet = cur.fetchall()

for result in resultSet:
    platformName = result[0]
    user_count = int(result[1])
    device_count = int(result[2])
    day = datetime.datetime.now().strftime("%Y-%m-%d")
    yesterday = (datetime.datetime.today() - datetime.timedelta(days=1)).strftime("%Y%m%d")
    createTimeField = datetime.datetime.now()
    updateTimeField = datetime.datetime.now()

    # 插入昨日数据的统计
    insertSql = """INSERT INTO BEHAVIOR.access_all (day,platform_name,user_count,device_count,create_date,update_date) VALUES ('{}','{}',{},{},'{}','{}')""".format(
        day, platformName, user_count, device_count, createTimeField, updateTimeField)
    cur.execute(insertSql)
    mysqlConn.commit()

    # 删除昨日的redis数据
    redisConn.delete("deviceid" + "_" + platformName + "_" + yesterday)
    redisConn.delete("userid" + "_" + platformName + "_" + yesterday)
