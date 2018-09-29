#!/usr/bin/python
# -*- coding: utf-8 -*-


import datetime
import sys
import traceback

import requests

from DBUtils import *

""" 
 从API接口获取指标数值
 @pid 产品id
 @date 日期
 @ type_list 业务线的指标ID列表
"""
def getIndexValueFromAPI(pid,date,type_list):
    biaConn = getBiaConncet()
    cur = biaConn.cursor()
    try:
        for type in type_list:
            url = metrics_api + "?type="+str(type)+"&date="+str(date)+"&pid="+str(pid)
            print(url)
            req = requests.get(url)
            data = req.json()
            if(data['success'] == 1):
               data_list = data['data']
               for i in data_list:
                    for sub_value in type_list[type]:
                       if sub_value in i:
                                insertSql="""INSERT INTO BIA.BI_INDEX_VAL_TODAY (INDEX_ID,STAT_DATE,STAT_HOUR,STAT_TIME,STAT_VALUE,CREATE_TIME,CREATER,UPDATE_TIME,UPDATER) VALUES ({},'{}',{},'{}',{},'{}','{}','{}','{}') 
                                             ON DUPLICATE KEY UPDATE STAT_VALUE={} , UPDATE_TIME='{}',UPDATER='{}';""".format(
                                    type_list[type][sub_value],i['date'],i['hour'],datetime.datetime.now(),i[sub_value],datetime.datetime.now(),'sys',datetime.datetime.now(),'sys',i[sub_value],datetime.datetime.now(),'sys')
                                #print(insertSql)
                                cur.execute(insertSql)
        biaConn.commit()
        print(str(datetime.datetime.now())+" INFO getIndexValueFromAPI: pid:"+str(pid)+",date="+str(date))
    except :
        print("getIndexValueFromAPI 发生异常，插入数据进行回滚处理。")
        traceback.print_exc()
        biaConn.rollback()
    finally:
        cur.close()
        biaConn.close()



""" 
 从mysql获取指标数值
 @pid 产品id
 @date 日期
 @ type_list 业务线的指标ID列表
"""
def getIndexValueFromMysql(pid,date,type_list):
    nonoConn = getNonoConncet()
    nonoCur = nonoConn.cursor()
    biaConn = getBiaConncet()
    biaCur = biaConn.cursor()
    memberConn = getMemberConncet()
    memberCur = memberConn.cursor()
    try:
        for type in type_list:
            query_sql = ''
            member_query_sql=''
            # 申请数
            if(type == 'apply'):
                query_sql = '''SELECT DATE(bp_publish_time) AS apply_date -- 申请日期
                                      ,HOUR(bp_publish_time) AS apply_hour -- 申请小时
                                      ,COUNT(id) AS apply_cnt -- 申请数
                                      ,SUM(bp_price) AS apply_amt -- 申请金额
                                      FROM db_nono.borrows_prepare
                                      WHERE p_id={}
                                      AND DATE(bp_publish_time)='{}'
                                      GROUP BY DATE(bp_publish_time),HOUR(bp_publish_time);'''.format(str(pid),str(date))
            # 进件数
            if(type == 'jinjian'):
                query_sql = '''SELECT DATE(bo_video_time) AS ml_date -- 进件日期
                                      ,HOUR(bo_video_time) AS ml_hour -- 进件小时
                                      ,COUNT(DISTINCT bo_id) AS ml_cnt -- 进件数
                                      ,SUM(bo_price) AS ml_amt -- 进件金额
                                      FROM (SELECT bo_id
                                      ,MIN(CASE WHEN amount_before_change_value>0 THEN amount_before_change_value ELSE bo_price END) bo_price
                                      ,MIN(b.create_time) AS bo_video_time
                                      FROM db_nono.borrows_archive b
                                      INNER JOIN db_nono.borrows AS a
                                      ON a.id=b.bo_id
                                      WHERE a.p_id={}
                                      AND b.keyword IN('borrow_video_audit') -- 视频提交
                                      AND b.is_audit>0 -- 视频已提交
                                      AND DATE(b.create_time)='{}'
                                      GROUP BY bo_id) AS b
                                      GROUP BY DATE(bo_video_time),HOUR(bo_video_time);'''.format(str(pid),str(date))
            # 放款数
            if(type == 'finish'):
                query_sql = '''SELECT DATE(bo_finish_time) AS loan_date -- 放款日期
                                      ,HOUR(bo_finish_time) AS loan_hour -- 放款小时
                                      ,COUNT(id) AS loan_cnt -- 放款数
                                      ,SUM(bo_finish_price) AS loan_amt -- 放款金额
                                      FROM db_nono.borrows
                                      WHERE p_id={}
                                      AND DATE(bo_finish_time)='{}'
                                      AND bo_is_finish=1
                                      GROUP BY DATE(bo_finish_time),HOUR(bo_finish_time);'''.format(str(pid),str(date))
            # 邀请码数量
            if(type == 'invitcode'):
                member_query_sql = '''SELECT DATE(valid_time) AS invit_date -- 邀请日期
                                      ,HOUR(valid_time) AS apply_hour -- 邀请小时
                                      ,COUNT(id) AS apply_cnt -- 邀请数
                                      FROM usr_member.invitation_code
                                      WHERE STATUS = 1 AND p_id={}
                                      AND DATE(valid_time)='{}'
                                      GROUP BY DATE(valid_time),HOUR(valid_time);'''.format(str(pid),str(date))

            #print (query_sql)
            if(len(query_sql) > 0):
                nonoCur.execute(query_sql)
                results = nonoCur.fetchall()
            if(len(member_query_sql) > 0):
                memberCur.execute(member_query_sql)
                results = memberCur.fetchall()

            for i in results:
                for sub_value in type_list[type]:
                    insertSql = ''
                    if(sub_value == "count"):
                        insertSql="""INSERT INTO BIA.BI_INDEX_VAL_TODAY (INDEX_ID,STAT_DATE,STAT_HOUR,STAT_TIME,STAT_VALUE,CREATE_TIME,CREATER,UPDATE_TIME,UPDATER) VALUES ({},'{}',{},'{}',{},'{}','{}','{}','{}') 
                                     ON DUPLICATE KEY UPDATE STAT_VALUE={} , UPDATE_TIME='{}',UPDATER='{}';""".format(
                                    type_list[type][sub_value],i[0],i[1],datetime.datetime.now(),i[2],datetime.datetime.now(),'sys',datetime.datetime.now(),'sys',i[2],datetime.datetime.now(),'sys')

                    if(sub_value == "amt"):
                        insertSql="""INSERT INTO BIA.BI_INDEX_VAL_TODAY (INDEX_ID,STAT_DATE,STAT_HOUR,STAT_TIME,STAT_VALUE,CREATE_TIME,CREATER,UPDATE_TIME,UPDATER) VALUES ({},'{}',{},'{}',{},'{}','{}','{}','{}') 
                                 ON DUPLICATE KEY UPDATE STAT_VALUE={} , UPDATE_TIME='{}',UPDATER='{}';""".format(
                                type_list[type][sub_value],i[0],i[1],datetime.datetime.now(),i[3],datetime.datetime.now(),'sys',datetime.datetime.now(),'sys',i[3],datetime.datetime.now(),'sys')

                    if(len(insertSql) > 0):
                        print(insertSql)
                        biaCur.execute(insertSql)
        biaConn.commit()
        print(str(datetime.datetime.now())+" INFO getIndexValueFromMysql: pid:"+str(pid)+",date="+str(date))
    except :
        print("getIndexValueFromMysql 发生异常，插入数据进行回滚处理。")
        traceback.print_exc()
        nonoConn.rollback()
        biaConn.rollback()
        memberConn.rollback()
    finally:
        nonoCur.close()
        nonoConn.close()
        biaCur.close()
        biaConn.close()
        memberCur.close()
        memberConn.close()




""" 
 从当天明细指标数值表汇总到当天累计数值表
 @date 汇总日期
"""
def stat_today_to_day(date):
    biaConn = getBiaConncet()
    cur = biaConn.cursor()
    query_sql = '''select INDEX_ID,sum(STAT_VALUE) from BI_INDEX_VAL_TODAY where STAT_DATE = '{}' group by INDEX_ID order by INDEX_ID
'''.format(str(date));

    cur.execute(query_sql)
    results = cur.fetchall()
    try:
        for row in results:
            index_id = row[0]
            sum_value = row[1]

            insert_sql = '''INSERT INTO BIA.BI_INDEX_VAL_DAY (INDEX_ID,STAT_DATE,STAT_VALUE,CREATE_TIME,CREATER,UPDATE_TIME,UPDATER) VALUES ({},'{}',{},'{}','{}','{}','{}') 
                            ON DUPLICATE KEY UPDATE STAT_VALUE={} , UPDATE_TIME='{}',UPDATER='{}';'''.format(
                            index_id,str(date),sum_value,datetime.datetime.now(),'sys',datetime.datetime.now(),'sys',sum_value,datetime.datetime.now(),'sys')
            #print(insert_sql)
            cur.execute(insert_sql)
        biaConn.commit()
        print(str(datetime.datetime.now())+" INFO stat_today_to_day: date="+str(date))
    except Exception:
        print("stat_today_to_day 发生异常，插入数据进行回滚处理。")
        traceback.print_exc()
        biaConn.rollback()
    finally:
        cur.close()
        biaConn.close()



""" 
 从当天累计数值表汇总到总体累计数值表
 @date 汇总日期
"""
def stat_day_to_total(date):
    biaConn = getBiaConncet()
    cur = biaConn.cursor()
    query_sql = '''select INDEX_ID,sum(STAT_VALUE) from BI_INDEX_VAL_DAY group by INDEX_ID;'''

    cur.execute(query_sql)
    results = cur.fetchall()
    try:
        for row in results:
            index_id = row[0]
            sum_value = row[1]

            insert_sql = '''INSERT INTO BIA.BI_INDEX_VAL_TOTAL (INDEX_ID,STAT_VALUE,CREATE_TIME,CREATER,UPDATE_TIME,UPDATER) VALUES ({},{},'{}','{}','{}','{}') 
                            ON DUPLICATE KEY UPDATE STAT_VALUE={} , UPDATE_TIME='{}',UPDATER='{}';'''.format(
                            index_id,sum_value,datetime.datetime.now(),'sys',datetime.datetime.now(),'sys',sum_value,datetime.datetime.now(),'sys')
            #print(insert_sql)
            cur.execute(insert_sql)
        biaConn.commit()
        print(str(datetime.datetime.now())+" INFO stat_day_to_total: date="+str(date))
    except:
        print("stat_day_to_total 发生异常，插入数据进行回滚处理。")
        traceback.print_exc()
        biaConn.rollback()
    finally:
        cur.close()
        biaConn.close()



if __name__ == '__main__':

    # 默认获取当天日期
    today = str(datetime.date.today())
    #today = '2017-12-12'

    # 默认取精英贷的业务线pid
    pid = 140

    # api|mysql
    stat_func='api'
    if(len(sys.argv)>= 2):
        stat_func = str(sys.argv[1])
        print(str(datetime.datetime.now())+" INFO 参数，stat_func: "+stat_func)

    if(len(sys.argv)>= 3):
        pid=str(sys.argv[2])
        print(str(datetime.datetime.now())+" INFO 参数，pid: "+pid)

    if(len(sys.argv)>= 4):
        today=str(sys.argv[3])
        print(str(datetime.datetime.now())+" INFO 参数，date: "+today)
    else:
        print(str(datetime.datetime.now())+" INFO 参数[默认]，date: "+today)



    if(stat_func == "api"):
        # 从api获取指标数值
        #getIndexValueFromAPI(jyd_pid,today,jyd_list)
        getIndexValueFromAPI(pid,today,bizcode_metrics_list.get(pid))

    if(stat_func == "planB"):
        # 从mysql获取指标数值
        getIndexValueFromMysql(pid,today,bizcode_metrics_list.get(pid))
        # getIndexValueFromMysql(bld_pid,today,bld_list)

    # 当天累计数值表
    stat_today_to_day(today)
    # 总体累计数值表
    stat_day_to_total(today)

















