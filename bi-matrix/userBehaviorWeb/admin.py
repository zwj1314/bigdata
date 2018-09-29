from django.contrib import admin
from .models import Site, Biz,Cvs_rates

# Register your models here.

admin.site.site_header = "用户行为分析管理"


class SiteInline(admin.TabularInline):
    model = Site
    fields = ["site_code", "site_name", "site_url", "env", "terminal"]
    readonly_fields = ("site_code",)
    extra = 1

    def has_delete_permission(self, request, obj=None):
        return False

@admin.register(Biz)
class BizInline(admin.ModelAdmin):
    # 业务线代码名称和业务线代码共占一行
    fields = (
        ('biz_name',
         "biz_code",),
    )
    readonly_fields = ("biz_name", "biz_code")

    inlines = [SiteInline]

    def has_delete_permission(self, request, obj=None):
        return False



@admin.register(Cvs_rates)
class BizInline(admin.ModelAdmin):
    fields = ["biz", "site",  "cvs_type", "seq_sort","all_csv_type","step_name","is_show","data_source","page_name",
              "action_type","action_value","index_name","creater","updater","remark"]

    list_display = ("biz", "site",  "cvs_type", "seq_sort","all_csv_type","step_name","is_show","data_source","page_name",
                    "action_type","action_value","index_name","creater","updater","remark")

    ordering = ("biz", "site",  "cvs_type", "seq_sort")


    search_fields =("biz__biz_name", "site__site_name","cvs_type","step_name","is_show","data_source","page_name",
                    "action_type","action_value","index_name","creater","updater","remark")

    def has_delete_permission(self, request, obj=None):
        return False