# mysql数据库配置
mysqlArgs = {
    "host": '172.16.2.147',
    "port": 3306,
    "user": "BEHAVIOR_APP",
    "password": "czwssYpiZmV6svsQ",
    "db": "BEHAVIOR",
    "charset": 'utf8mb4',
}

# mysql数据库配置[bia]
biaArgs = {
    "host": '172.16.2.147',
    "port": 3307,
    "user": "BIA_APP",
    "password": "6wiuysOXgKHGvWbR",
    "db": "BIA",
    "charset": 'utf8mb4',
}


# mysql数据库配置[bia]
nonoArgs = {
    "host": '172.16.2.109',
    "port": 3315,
    "user": "bi_etl",
    "password": "eEEna6GO",
    "db": "db_nono",
    "charset": 'utf8mb4',
}


# mysql数据库配置[bia]
memberArgs = {
    "host": '172.16.2.109',
    "port": 3321,
    "user": "bi_query",
    "password": "4t89me1e",
    "db": "usr_member",
    "charset": 'utf8mb4',
}



# redis数据库配置
redisArgs = {
    "host": '172.16.2.157',
    "port": 6379,
    "password": "Mzjf_redis"
}

# 读取多久前的redis数据(单位:分钟)
nMinAgo = 10


# 获取指标统计api
metrics_api = r'http://172.16.2.160/bi-apiserver/mrc/metrics'


bizcode_metrics_list={
    # 白领贷业务线各操作对应的指标集合
    "87":{'apply':{'count':1007,'amt':1008},
          'jinjian':{'count':1009,'amt':1010},
          'finish':{'count':1011,'amt':1012}
          },
    # 精英贷业务线各操作对应的指标集合
    "140":{'apply':{'count':1000,'amt':1001},
           'jinjian':{'count':1002,'amt':1003},
           'finish':{'count':1004,'amt':1005},
           'invitcode':{'count':1006}
           },
    # 保本理财指标集合
    "1":{'zf_chujie':{'count':1016,'amt':1014}
              },
    # 曾凤账户指标集合
    "2":{'zf_chujie':{'count':1015,'amt':1013}
           }
    }
