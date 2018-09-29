"""userBehaviorAnalysis URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url,include
from django.contrib import admin
from userBehaviorWeb import views
from django.conf.urls.static import static
from django.conf import settings
from django.views.static import serve
handler505 = views.handler404  # 404
handler505 = views.handler404  # 505

urlpatterns = [
    url(r"^dashboard/",include("dashboard.urls")),
    url(r'^admin/', admin.site.urls),
    url(r'^$', views.home),
    url(r'^login/$', views.login),
    url(r'^login.html$', views.login),
    url(r'^index$', views.index),
    url(r'^index.html$', views.index),
    url(r'^logout$', views.logout),
    url(r"^ajax/api$", views.get_api_data, name='ajax_name'),
    url(r"^ajax/channel$", views.get_channels, name='ajax_name'),
    url(r"^web$", views.platform_module),
    url(r"^web/(\w+)$", views.ajax),
    # url(r"^get-channels$", views.get_channels),
    url(r'^test.html$', views.test),
    url(r'^static/(?P<path>.*)$',serve,{'document_root':settings.STATIC_ROOT})
]



