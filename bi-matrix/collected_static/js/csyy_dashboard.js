/**
 * Created by niuzitong on 03/07/2017.
 */
var header = $('#header')
header.css('height', window.innerHeight * 0.1 + 'px')
var main = $('div[id ^="main"]')

//
var main1 = document.getElementById('main1'),
    main2 = document.getElementById('main2'),
    main3 = document.getElementById('main3'),
    main4 = document.getElementById('main4'),
    main5 = document.getElementById('main5'),
    main6 = document.getElementById('main6'),
    main7 = document.getElementById('main7')
// //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
var resizemain = function () {
    main.css('width', window.innerWidth / 2 + 'px')
    main.css('height', window.innerHeight * 0.9 / 2 + 'px')
    //main1.style.width = window.innerWidth/2+'px';
    //main1.style.height = window.innerHeight*0.8/2+'px';
};
//
// //设置容器高宽
//
resizemain();

// 基于准备好的dom，初始化echarts实例
var myChart1 = echarts.init(main1, 'walden');
var myChart2 = echarts.init(main2, 'walden');
var myChart3 = echarts.init(main3, 'walden');
var myChart4 = echarts.init(main4, 'walden');
var myChart5 = echarts.init(main5, 'walden');
var myChart6 = echarts.init(main6, 'walden');
var myChart7 = echarts.init(main7, 'walden');


//FR查询调用接口
var host = $("#host").text();
var localhost = 'localhost'
var url = '/dashboard/fr_helper';


// 获取数据
function get_data(dataset) {
    var params = {
        report: "BIA/财神大屏.frm",
        param: JSON.stringify({}),
        dsName: dataset
    };
    var now_hour=moment().hour()
    if (dataset == '首投客户数_分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
            console.log(data)
            //将返回的数据重组成Object
            var hor = _.values(data)[0].map(x => x['HOR'].toString());
            var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
            var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);


            var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
            var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour+1)
            console.log(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))))
            option1 = get_echarts_option(['昨日', '今日'], '投资人数-分时', hor, [last_idx_val, idx_val], 'bar')
            option2 = get_echarts_option(['昨日', '今日'], '累计投资人数-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
            myChart1.setOption(option1)
            myChart2.setOption(option2)
        }});

    } else if (dataset == '投资金额_分时') {

        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
            var hor = _.values(data)[0].map(x => x['HOR']);
            var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
            var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

            var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
            var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour+1)
            console.log(cums_sum_idx_val)
            option3 = get_echarts_option(['昨日', '今日'], '投资金额-分时', hor, [last_idx_val, idx_val], 'bar')
            option4 = get_echarts_option(['昨日', '今日'], '投资金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
            myChart3.setOption(option3)
            myChart4.setOption(option4)
        }})

    } else if (dataset == '提现金额_分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
            var hor = _.values(data)[0].map(x => x['HOR']);
            var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
            var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);
            var cumsum_last_idx_val = _.map(_.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),x=>x==null?null:x.toFixed(2))
            var cums_sum_idx_val = _.map(_.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour+1),x=>x==null?null:x.toFixed(2))


            option5 = get_echarts_option(['昨日', '今日'], '提现金额-分时', hor, [last_idx_val, idx_val], 'bar')
            option6 = get_echarts_option(['昨日', '今日'], '提现金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
            myChart5.setOption(option5)
            myChart6.setOption(option6)
        }})

    }else if (dataset == '产品投资金额_分时') {

        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
            var dim_1_val = _.values(data)[0].map(x => x['DIM_1_VAL']);
            var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
            var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);


            option7 = get_echarts_option(['昨日', '今日'], '资金产品', dim_1_val, [last_idx_val, idx_val], 'bar')
            myChart7.setOption(option7)
        }})
    }
}
get_data('首投客户数_分时')
get_data('投资金额_分时')
get_data('提现金额_分时')
get_data('产品投资金额_分时')

// 获得echarts定制化option
function get_echarts_option(legend_data, title_text, xAxis_data, series_data, series_data_type) {
    var option = {
        legend: {
            right: 50,
            data: legend_data
        },
        tooltip: {},
        title: {
            text: title_text,
            x: 'center'
        },
        grid: {
                left: 100
            },
        xAxis: {
            type: 'category',
            splitLine: {
                show: false
            },
            data: xAxis_data,
            axisLable:{
            interval:0         
},
            axisTick: {
                alignWithLabel: true,

            }
        },
        color:['#3fb1e3','#626c91','#dd6b66','#759aa0','#8dc1a9','#7289ab','#73b9bc','#91ca8c'],
        yAxis: {
            type: 'value',
            //boundaryGap: [0, '100%'],

            scale: true,
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            },
        },
        series: [
            {
                name: '昨日',
                type: series_data_type,
                data: series_data[0]
            },
            {
                name: '今日',
                type: series_data_type,
                data: series_data[1]
            },

        ]
    }

    return option
}

var now_time=document.getElementById('now')
now_time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())


setInterval(function () {
    now_time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())
},1000);

setInterval(function () {
    $('#example1').slideToggle('slow').delay(2000)
    $('#example2').slideToggle('slow')
}, 20000)

setTimeout(function () {
    window.location.reload();
},60000)

//用于使chart自适应高度和宽度
window.onresize = function () {
    //重置容器高宽
    header.css('height', window.innerHeight * 0.1 + 'px')
    resizemain();
    myChart1.setOption(option1)
    myChart2.setOption(option2)
    myChart3.setOption(option3)
    myChart4.setOption(option4)
    myChart5.setOption(option5)
    myChart6.setOption(option6)
    myChart7.setOption(option7)
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart4.resize();
    myChart5.resize();
    myChart6.resize();
    myChart7.resize();
};


