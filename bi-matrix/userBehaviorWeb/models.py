from django.db import models
from django import forms
import uuid
import django.utils.timezone as timezone


# Create your models here.
class UUIDTools(object):
    """uuid function tools"""

    @staticmethod
    def uuid1_hex():
        """
        return uuid1 hex string

        eg: 23f87b528d0f11e696a7f45c89a84eed
        """
        return uuid.uuid1().hex[0:16]


class UserForm(forms.Form):
    username = forms.CharField(label='用户名：', max_length=100, error_messages={'required': u'用户名不能为空'})
    password = forms.CharField(label='密码：', widget=forms.PasswordInput(), error_messages={'required': u'密码不能为空'})


class Biz(models.Model):
    biz_code = models.CharField(verbose_name='业务线代码', default=None, unique=True, max_length=200, help_text="唯一")
    biz_name = models.CharField(verbose_name='业务线代码名称', max_length=256, help_text="")
    app_show = models.BooleanField(verbose_name='app是否显示', default=0, help_text="1:显示;0:不显示")
    app_seq = models.IntegerField(verbose_name='app显示排序', default=0, help_text="app显示顺序")
    web_show = models.BooleanField(verbose_name='web是否显示', default=0, help_text="1:显示;0:不显示")
    web_seq = models.IntegerField(verbose_name='web显示顺序', default=0, help_text="web显示顺序")
    create_time = models.DateTimeField(verbose_name="创建时间", auto_now_add=True, help_text="创建时间")
    creater = models.CharField(verbose_name='创建人', default=None, blank=True, max_length=10, help_text="创建人")
    update_time = models.DateTimeField(verbose_name='修改时间', auto_now=True, help_text="修改时间")
    updater = models.CharField(verbose_name='修改人', default=None, blank=True, max_length=10, help_text="修改人")
    remark = models.CharField(verbose_name='备注', default=None, blank=True, max_length=200, help_text="备注")

    def __str__(self):
        return self.biz_name

    class Meta:  # 定义表名
        verbose_name = '业务线站点配置表'
        verbose_name_plural = '业务线站点配置表'
        db_table="user_behavior_web_biz"



class Site(models.Model):
    env_tuple = (("sit", "sit"), ("sbt", "sbt"), ("pre", "pre"), ("prd", "prd"))
    terminal_tuple = (("pc", "pc"), ("mobile", "mobile"))

    biz = models.ForeignKey(Biz, on_delete=models.DO_NOTHING)
    site_code = models.CharField(verbose_name="uuid", default=UUIDTools.uuid1_hex, unique=True,
                                 max_length=234, help_text="UUID,16位")
    site_name = models.CharField(verbose_name="站点名称", default=None, unique=True, max_length=234, help_text="站点名称")
    site_url = models.CharField(verbose_name="站点URL", default=None, max_length=256, help_text="站点URL")
    env = models.CharField(verbose_name="站点环境", choices=env_tuple, max_length=256, help_text="站点环境")
    terminal = models.CharField(verbose_name="站点终端", choices=terminal_tuple, max_length=256,
                                help_text="站点终端")
    create_time = models.DateTimeField(verbose_name="创建时间", auto_now_add=True, help_text="创建时间")
    creater = models.CharField(verbose_name='创建人', default=None, blank=True, max_length=10, help_text="创建人")
    update_time = models.DateTimeField(verbose_name='修改时间', auto_now=True, help_text="修改时间")
    updater = models.CharField(verbose_name='修改人', default=None, blank=True, max_length=10, help_text="修改人")
    remark = models.CharField(verbose_name='备注', default=None, blank=True, max_length=200, help_text="备注")

    def __str__(self):
        return self.site_name

    class Meta:  # 定义表名
        verbose_name = '站点配置表'
        verbose_name_plural = '站点配置表'
        db_table="user_behavior_web_site"



class Cvs_rates(models.Model):
    cvs_type_tuple = ( (2, "注册UV转化率"), (3, "推广投资转化率"))
    data_source_tuple = ((1, "业务数据"), (2, "行为数据"))
    at_tuple = ((None, "空"), ("openview", "页面浏览"), ("input", "输入"), ("click", "点击"), ("IEVENT", "业务结果"))


    all_csv_type_tuple = ((0, "空"), (1, "第一步"), (2, "第二步"))

    biz = models.ForeignKey(Biz, verbose_name="业务线", on_delete=models.DO_NOTHING)
    site = models.ForeignKey(Site, verbose_name="站点", on_delete=models.DO_NOTHING)
    page_name = models.CharField(verbose_name="页面名称", default=None, blank=True, max_length=234, help_text="页面名称")
    cvs_type = models.IntegerField(verbose_name="转化率类型", choices=cvs_type_tuple, default=0, help_text="转化率类型，枚举值")
    seq_sort = models.IntegerField(verbose_name="步骤序号", default=0, help_text="步骤序号")

    all_csv_type = models.IntegerField(verbose_name="总体转化率步骤", choices=all_csv_type_tuple, default=0,
                                       help_text="总体转化率步骤")
    step_name = models.CharField(verbose_name="步骤名称", default=None, max_length=234, help_text="每一步骤的显示名称")
    is_show = models.BooleanField(verbose_name="是否展示", default=1, help_text="是否前端展示")
    data_source = models.IntegerField(verbose_name="数据源", choices=data_source_tuple, default=0, help_text="数据的统计来源，枚举值")
    action_type = models.CharField(verbose_name="事件类型", choices=at_tuple, blank=True, default=None, max_length=20,
                                   help_text="web日志中事件类型")
    action_value = models.CharField(verbose_name="事件值",  blank=True, default=None, max_length=200,
                                    help_text="web日志中事件值")
    index_name = models.CharField(verbose_name="指标名称", default=None, blank=True, max_length=20, help_text="业务数据中指标名称")

    create_time = models.DateTimeField(verbose_name="创建时间", auto_now_add=True, help_text="创建时间")
    creater = models.CharField(verbose_name='创建人', default=None, blank=True, max_length=10, help_text="创建人")
    update_time = models.DateTimeField(verbose_name='修改时间', auto_now=True, help_text="修改时间")
    updater = models.CharField(verbose_name='修改人', default=None, blank=True, max_length=10, help_text="修改人")
    remark = models.CharField(verbose_name='备注', default=None, blank=True, max_length=200, help_text="备注")

    def __str__(self):
        return self.site.site_name

    class Meta:  # 定义表名
        verbose_name = '转化率配置表'
        verbose_name_plural = '转化率配置表'
        db_table="user_behavior_web_cvs_rates"


class AccessLog(models.Model):
    '''
    用户行为后台系统操作记录表
    '''
    id = models.AutoField(db_column='ID', primary_key=True)  # Field name made lowercase.
    request_date = models.DateField(blank=True, null=True)
    request_time = models.DateTimeField()
    user_name = models.CharField(max_length=20, blank=True, null=True)
    request_url = models.CharField(max_length=200, blank=True, null=True)
    cookies = models.CharField(max_length=500, blank=True, null=True)
    request_get = models.CharField(max_length=500, blank=True, null=True)
    request_post = models.TextField(blank=True, null=True)
    response_code = models.IntegerField(blank=True, null=True)
    remark = models.CharField(max_length=200, blank=True, null=True)

    class Meta:
        managed = False
        verbose_name = '系统操作记录表'
        verbose_name_plural = '系统操作记录表'
        db_table = 'access_log'
