/**
 * Created by niuzitong on 10/08/2017.
 */
//FR查询调用接口
var host = $("#host").text();
var localhost = '192.168.44.106'
//var url = 'http://' + localhost + '/bi-finereport-helper/rest/frdata/json';
var url = '/dashboard/fr_helper';


// 绘图相关配置

// 图表属性：折线颜色[上周/昨天/今天]
var line_color = ['#6c7882', '#1c711c', '#ff0000'];

// 图表属性：折线宽度
var line_width = 2.5;

main_func()

function main_func() {
    render_per_hour_data("访问用户数","main1")
    render_per_hour_data("待初审金额","main2")
    render_per_hour_data("初审通过量","main3")

    render_per_hour_data("初审处理量","main4")

    render_per_hour_data("待终审量","main5")
    render_per_hour_data("待终审金额","main6")
    render_per_hour_data("终审通过量","main7")
    render_per_hour_data("终审处理量","main8")

    render_per_hour_data("终审通过金额","main9")
}

function render_per_hour_data(data_source_name,elemID) {
    var params_cumsum = {
        report: "rBIA/信审_白领贷-大屏-分时统计_实时在线.fm",
        param: JSON.stringify({}),
        dsName: data_source_name
    };

    $.get(url, params_cumsum, function (data, status) {


        var data_sum=_.sum(_.map(data[data_source_name],x=>x["IDX_VAL"]))

        var lastweek= _.map(data[data_source_name],x=>x["LAST_WEEK_IDX_VAL"])
        var yesterday=_.map(data[data_source_name],x=>x["LAST_IDX_VAL"])
        var today=_.map(data[data_source_name],x=>x["IDX_VAL"])


        var xAxis_data = _.range(24)
        var now_hour= moment().hour()

        today = _.map(today,function (x, y) {
            if (y<=now_hour){
                if (_.isNull(x) ){
                    return 0
                }else  return x
            }else return "-"


        })

        var echartOption = get_echarts_option(xAxis_data, [lastweek, yesterday, today], 'line', formatNumber(data_sum), data_source_name)
        var main = document.getElementById(elemID);
        var myChart = echarts.init(main);
        myChart.setOption(echartOption)

    })
    //    数据刷新时间

    var params_uodateTime = {
        report: "BIA/白领贷-大屏-分时统计_实时在线.frm",
        param: JSON.stringify({}),
        dsName: '更新时间'
    };

    $.get(url, params_uodateTime, function (data, status) {
        var time = data["更新时间"][0]["time"]['value']
        document.getElementById('now_time').innerHTML = moment(time).format('YYYY-MM-DD HH:mm')
    })



}



function get_echarts_option(xAxis_data, series_data, series_data_type, cunsumTitle, title) {
    var option = {
        backgroundColor: '#020103',
        tooltip: {trigger: 'axis'},
        title: [{
            text: cunsumTitle,
            top: '7%',
            textAlign: 'center',
            left: '50%',
            textStyle: {
                fontSize: 15,
                color: 'rgba(255, 255, 255, 1.5)'
            }
        }, {
            text: title,
            top: '-1%',
            left: '50%',
            textAlign: 'center',
            textStyle: {
                color: '#aaa',
                fontWeight: 'normal',
                fontSize: 10
            }
        }],
        grid: {
            show: false,
            top: '10%',
            bottom: '20%',
            left: '18%',
        },
        xAxis: {
            type: 'category',
            splitLine: {
                show: false
            },
            data: xAxis_data,
            axisTick: {
                alignWithLabel: true,
                interval: 0
            }
        },
        yAxis: {
            type: 'value',
            scale: true,
            splitLine: {
                show: false,
                lineStyle: {}
            },
            max: (_.max(_.map(series_data, x => _.max(x))) * 1.5).toFixed(0)
        },
        color: line_color,
        series: [{
            name: '',
            smooth: true,
            type: series_data_type,
            data: series_data[0],
            symbol: 'none',
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        width: line_width, //折线宽度
                    }
                }
            },
        },
            {
                name: '',
                smooth: true,
                type: series_data_type,
                data: series_data[1],
                symbol: 'none',
                itemStyle: {
                    normal: {
                        lineStyle: {
                            width: line_width, //折线宽度
                        }
                    }
                },
            },
            {
                name: '',
                smooth: true,
                type: series_data_type,
                data: series_data[2],
                symbol: 'none',
                itemStyle: {
                    normal: {
                        lineStyle: {
                            width: line_width, //折线宽度
                        }
                    }
                },
            }
        ],
        textStyle: {
            color: "#aaa"
        }
    }
    return option;
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


// 10分钟调一次数据库 数据一小时刷新一次
setInterval(function () {
    main_func()
}, 180000);

window.onresize = function () {
    //重置容器高宽
    var myChart1 = echarts.getInstanceByDom(document.getElementById('main1'))
    var myChart2 = echarts.getInstanceByDom(document.getElementById('main2'))
    var myChart3 = echarts.getInstanceByDom(document.getElementById('main3'))
    var myChart4 = echarts.getInstanceByDom(document.getElementById('main4'))
    var myChart5 = echarts.getInstanceByDom(document.getElementById('main5'))
    var myChart6 = echarts.getInstanceByDom(document.getElementById('main6'))
    var myChart7 = echarts.getInstanceByDom(document.getElementById('main7'))
    var myChart8 = echarts.getInstanceByDom(document.getElementById('main8'))
    var myChart9 = echarts.getInstanceByDom(document.getElementById('main9'))
    var myChart10 = echarts.getInstanceByDom(document.getElementById('main10'))
    var myChart11 = echarts.getInstanceByDom(document.getElementById('main11'))
    var myChart12 = echarts.getInstanceByDom(document.getElementById('main12'))
    for (i of _.compact([myChart1, myChart2, myChart3, myChart4, myChart5, myChart6, myChart7, myChart8, myChart9,myChart10,myChart11,myChart12])) {
        i.resize()
    }


};
