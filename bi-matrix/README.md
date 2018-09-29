### 项目介绍

---
　用户行为系统及探索性数据处理的项目

### Building
```
安装anaconda：https://www.anaconda.com/download/#macos
pip install django-cors-headers
pip install django
pip install ldap3
```
### Runing
```
tmux ls
tmux atta -t bi-matrix
cd /opt/bi-matrix
git pull
/opt/anaconda3/bin/python /opt/bi-matrix/manage.py runserver 0.0.0.0:8080
```

### 目前实现功能

---
```
* 用户行为系统框架
* 集团及整体实时登录用户数：redis -> mysql 
* 实时指标统计[精英贷]：hbase -> api ->mysql
    # 以从api接口统计精英贷指标数据为例
    python3 ./DataStat/statIndexValue.py api 140

```