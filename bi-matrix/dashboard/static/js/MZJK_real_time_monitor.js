/**
 * Created by niuzitong on 10/08/2017.
 */
var host = $("#host").text();
var localhost = 'localhost'
// var url = 'http://' + '192.168.47.46' + '/bi-finereport-helper/rest/frdata/json';
var url = '/dashboard/fr_helper';

// 判断是否是移动设备,对移动端进行特殊处理
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    if (window.screen.width >= 768) {
        flag = true;
    }
    return flag;
}


var bodyWidth = document.body.clientWidth;
var bodyHeight = document.body.clientHeight;


get_real_time_and_render()


function get_real_time_and_render() {
    var params = {
        report: "BIA/麦子借款-大屏-实时监控.frm",
        param: JSON.stringify({}),
        dsName: '实时监控'
    };
    $.get(url, params, function (data, status) {
        var data_dict = data['实时监控'][0];
        data_dict=data_dict==undefined?{'fangkuanjine':0,'fangkuanbishu':0,'jinjianbishu':0,'zhuceyonghu':0}:data_dict
        if (bodyWidth > bodyHeight) {
            var fangkuanliang_tag = '<div type="button" class="btn  btn-lg"style="font-size:3vmax;background: rgba(255, 255, 255, 0)"><span style="color: white;float:bottom;">放款量(万):    </span></div>'
            var comma_tag = '<div type="button" class="btn  btn-lg"style="font-size: 3.5vmax;background: rgba(255, 255, 255, 0);color: white;margin: 0;padding: 0">,</div>'

        } else {
            var fangkuanliang_tag = '<div type="button" class="btn  btn-lg"style="font-size:3vmin;background: rgba(255, 255, 255, 0)"><span style="color: white;float:bottom;">放款量(万):    </span></div>'
            var comma_tag = '<div type="button" class="btn  btn-lg"style="font-size: 3.5vmin;background: rgba(255, 255, 255, 0);color: white;margin: 0;padding: 0">,</div>'

        }

        // 放款量渲染 放款量小数点百万之下保留两位小数，百万以上保留一位小数
        var fangkuanliang_ori = data_dict['fangkuanjine'] > 100 ? data_dict['fangkuanjine'].toFixed(1) : data_dict['fangkuanjine']


        var fangkuanliang = formatNumber(fangkuanliang_ori)

        for (i of fangkuanliang) {
            
            if (bodyWidth > bodyHeight) {
                $('.btn').css('font-size','4vmax')
                $('.btn p').css('font-size','3vmax')
                if (i != ',') {
                    fangkuanliang_tag += '<div type="button" class="btn  btn-lg"style="font-size: 3.5vmax;background-color: #641c67;color: white;margin-right: 0.5%;margin-left: 0.5%">' + i + '</div>'
                }
                else {
                    fangkuanliang_tag += comma_tag
                }
            } else {
                $('.btn').css('font-size','4vmin')
                $('.btn p').css('font-size','3vmin')
                if (i != ',') {
                    fangkuanliang_tag += '<div type="button" class="btn  btn-lg"style="font-size: 6vmin;background-color: #641c67;color: white;margin-right: 0.5%;margin-left: 0.5%;padding: 1%">' + i + '</div>'
                }
                else {
                    fangkuanliang_tag += comma_tag
                }
            }

        }
        document.getElementById('fangkuanjine').innerHTML = fangkuanliang_tag;


        // 放款笔数渲染
        var fangkuanbishu = formatNumber(data_dict['fangkuanbishu'])
        document.getElementById('data_1').innerHTML = fangkuanbishu;

        // 放款笔数渲染
        var jinjianbishu = formatNumber(data_dict['jinjianbishu'])
        document.getElementById('data_2').innerHTML = jinjianbishu;

        // 放款笔数渲染
        var zhuceyonghu = formatNumber(data_dict['zhuceyonghu'])
        document.getElementById('data_3').innerHTML = zhuceyonghu;
    })
}


function formatNumber(strNum) {
    if (strNum.length <= 3) {
        return strNum;
    }
    if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(strNum)) {
        return strNum;
    }
    var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
    var re = new RegExp();
    re.compile("(\\d)(\\d{3})(,|$)");
    while (re.test(b)) {
        b = b.replace(re, "$1,$2$3");
    }
    return a + "" + b + "" + c;
}

// 3分钟调一次数据库 数据5分钟刷一次
setInterval(function () {
    get_real_time_and_render()
}, 180000);


window.onresize=function () {
    if (bodyWidth > bodyHeight) {
            $('.btn').css('font-size','4vmax')
                $('.btn p').css('font-size','3vmax')
        } else {
            $('.btn').css('font-size','4vmin')
                $('.btn p').css('font-size','3vmin')
        }
}
