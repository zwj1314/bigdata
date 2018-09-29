from django.shortcuts import render
import requests
from django.http import HttpResponse,HttpResponseRedirect,JsonResponse
# Create your views here.

def dashboard_home(request):
    return render(request,"dashboard/dashboard_home.html")

def dashboard(request,page_name):

    return render(request,"dashboard/"+page_name+".html")


def fr_helper(request):
    full_path=request.get_full_path()
    params=full_path.split("?")[1]
    fr_helper_url='http://' + 'bia.nonobank.com' + '/bi-finereport-helper/rest/frdata/json'
    fr_data=requests.get(fr_helper_url+"?"+params).json()
    return JsonResponse(fr_data)
