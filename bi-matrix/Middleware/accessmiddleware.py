
from django.utils.deprecation import MiddlewareMixin
import time
from userBehaviorWeb.models import AccessLog

class Access(MiddlewareMixin):
    def process_request(self,request):
        try:
            # 记录除静态文件之外的请求日志
            if not '/static/' in  request.path:
                cookies = request.COOKIES.copy()
                if cookies.__contains__('csrftoken'):
                    cookies.pop('csrftoken')
                # if cookies.__contains__('sessionid'):
                #     cookies.pop('sessionid')
                access_log = AccessLog()
                access_log.request_date = time.strftime("%Y-%m-%d", time.localtime())
                access_log.request_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
                access_log.user_name = request.session.get("username",None)
                access_log.request_url = request.path
                access_log.cookies = cookies
                access_log.request_get = dict(request.GET._iterlists())
                access_log.request_post = dict(request.POST._iterlists())
                access_log.response_code = 0
                access_log.remark = ''
                access_log.save()
        except Exception as e:
            #result = {"data":[],"success":0,"errorMessage":e.message,"errorCode":0}
            result = {"data":[],"success":0,"errorCode":0}
            print(result)

    def process_response(self, request, response):

        return response

