from django.conf.urls import url
from .views import dashboard_home,dashboard,fr_helper


urlpatterns = [
    url(r"^$",dashboard_home),
    url(r"^(.+).html$",dashboard),
    url(r"^fr_helper$",fr_helper)
]
