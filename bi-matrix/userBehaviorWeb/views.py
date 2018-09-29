from django.shortcuts import render
from django.http import HttpResponse,HttpResponseRedirect,JsonResponse

from ldap3 import Server, Connection, ALL, NTLM
from django.conf import settings
from django.contrib import auth
import requests
import pandas as pd
import pymysql
from .models import Biz,Site
import json
from django.core.cache import cache


# Create your views here.
def home(request):
    '''
    首页
    :param request:
    :return:
    '''
    username=request.session.get("username",None)
    if username is not None:
        return HttpResponseRedirect("/web")
    return render(request,'login.html')


def test(request):
    return render(request,'test.html')


def login(request):
    '''
    登录功能
    :param request:
    :return:
    '''
    if request.method=="POST":
        paras = request.POST
        LDAP_USER = paras['username']
        LDAP_PASS = paras['password']
        a=LDAP_USER+LDAP_PASS
        if LDAP_USER=="" or LDAP_PASS=="":
            return render(request,"login.html",{"login_feedback":"用户名或密码不能为空"})
        else:
            server = Server(settings.LDAP_URI, get_info=ALL)
            conn = Connection(server, user=settings.LDAP_DOMAIN + "\\" + LDAP_USER, password=LDAP_PASS, authentication= NTLM)
            if(conn.bind()):
                request.session['username']= LDAP_USER
                return HttpResponseRedirect("/")
                ## TODO 验证登录用户在该系统具体页面访问权限
            else:
                return render(request,"login.html",{"login_feedback":"用户名和密码不匹配"})
    return HttpResponseRedirect("/")

def logout(request):
    '''
    系统登出
    :param request:
    :return:
    '''
    auth.logout(request)
    response = HttpResponseRedirect("/")
    response.delete_cookie('platform')
    response.delete_cookie('biz_code')
    response.delete_cookie('site_code')
    return response


def index(request):
    '''
    主页面
    :param request:
    :return:
    '''
    username=request.session.get("username",None)
    if username is  None:
        return HttpResponseRedirect("/login.html")
    biz_list= Biz.objects.filter(web_show=1).values("id","biz_code","biz_name","web_seq").order_by("web_seq")
    bizAndSiteDict={}
    for biz in biz_list:
        id=biz["id"]
        biz_name=biz["biz_name"]
        biz_code=biz["biz_code"]

        site_list=Site.objects.filter(biz_id=id).values("site_code","site_name")
        site_list_tuple=[]
        for site in site_list:
            site_list_tuple.append((biz_code,site["site_name"],site["site_code"]))
        bizAndSiteDict[biz_name]=site_list_tuple

    return render(request,"web_home.html",{"username":username,"bizAndSiteDict":bizAndSiteDict})




def platform_module(request):
    '''
    平台模块数据处理
    :param request:
    :return:
    '''
    username=request.session.get("username",None)
    if username is  None:
        return HttpResponseRedirect("/")
    username=request.session.get("username","游客")
    biz_list= Biz.objects.filter(web_show=1).values("id","biz_code","biz_name","web_seq").order_by("web_seq")

    platform = request.COOKIES.get("platform", "web")
    current_biz_code = request.COOKIES.get("biz_code", "")
    current_site_code = request.COOKIES.get("site_code", "")
    current_biz_name=""
    current_site_name=""

    bizAndSiteDict={}
    for biz in biz_list:
        id=biz["id"]
        biz_name=biz["biz_name"]
        biz_code=biz["biz_code"]
        if biz_code == current_biz_code:
            current_biz_name = biz_name

        site_list=Site.objects.filter(biz_id=id).values("site_code","site_name")
        site_list_tuple=[]
        for site in site_list:
            site_list_tuple.append((biz_code,site["site_name"],site["site_code"]))
            if site["site_code"] == current_site_code:
                current_site_name = site["site_name"]
        bizAndSiteDict[biz_name]=site_list_tuple

    if (current_biz_code == "" or current_site_code == ""):
        # platform__home.html
        return render(request,platform+"_home"+".html",{"username":username,"bizAndSiteDict":bizAndSiteDict})
    elif platform =="web":
        # web.html
        return render(request,"web.html",{"username":username,"platform":"web","biz":current_biz_name,"site":current_site_name,"site_id":current_site_code,"bizAndSiteDict":bizAndSiteDict})
    else:
        # app.html
        return render(request,'index_'+platform+".html",{"username":username,"platform":"app","product":current_site_code,"bizAndSiteDict":bizAndSiteDict})


def ajax(request,module):
    '''
    ajax调用不同模块数据
    :param request:
    :param ajax:
    :return:
    '''
    platform=request.COOKIES.get("platform", "web")
    biz_code=request.COOKIES.get("biz_code", "")
    site_code=request.COOKIES.get("site_code", "")

    if platform=="app":
        product_name=request.GET.get('product')
        return render(request,'ajax/'+ajax+".html",{"platform":ajax.split("_")[-1],"product":product_name})
    if platform=="web":
        biz_site_dict={"biz":biz_code,"site":site_code}
        page_name=requests.get(settings.API_SERVER+"/h5/list/page?site_code={}".format(site_code)).json()["data"]
        page_name_list=[]
        for i in page_name:
            page_name_list.append(i["page_name"])
    return render(request,'ajax/'+platform+'_'+module+".html",{"platform":"web","biz":biz_code,"site":site_code,"page_name_list":page_name_list,"biz_site_dict":biz_site_dict})


def get_api_data(requset):
    '''
    获取api的请求数据
    :param requset:
    :return: 返回接口数据
    '''
    try:
        params = requset.GET.copy()
        if params.__contains__('api_name'):
            api_url = settings.API_SERVER + params.get('api_name')
            params.pop("api_name")
            from urllib.parse import urlencode
            request_params = urlencode(params)
            full_api_url = api_url +"?"+request_params
            print(full_api_url)
            result = requests.get(full_api_url).json()
            return HttpResponse(json.dumps(result), content_type="application/json")
        else:
            result = {"data":[],"success":1,"errorMessage":'参数缺失：api_name',"errorCode":0}
            return HttpResponse(json.dumps(result), content_type="application/json")
    except Exception as e:
        result = {"data":[],"success":0,"errorMessage":e.message,"errorCode":0}
        return HttpResponse(json.dumps(result), content_type="application/json")


def get_channels(request):
    '''
    获取渠道数
    :param request:
    :return:
    '''

    res = {}
    if cache.get('channel'):
        res = cache.get('channel')
    else:
        conn=pymysql.connect(host=settings.DATABASES["default"]["HOST"],
                             port=int(settings.DATABASES["default"]["PORT"]),
                             user=settings.DATABASES["default"]["USER"],
                             passwd=settings.DATABASES["default"]["PASSWORD"],
                             db=settings.DATABASES["default"]["NAME"],
                             charset='utf8mb4',)
        sql="select distinct id,channel_name from channel"
        df=pd.read_sql(sql,conn)
        a=df.to_dict(orient="records")
        res={"data":a}
        # 缓存8小时
        cache.set('channel',res, 36000)
        conn.close()

    return HttpResponse(json.dumps(res), content_type="application/json")




def handler404(request):
    response = render(request,'404.html')
    response.status_code = 404
    return response


def handler500(request):
    response = render(request,'500.html')
    response.status_code = 500
    return response
