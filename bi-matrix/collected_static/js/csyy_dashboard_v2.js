/**
 * Created by zhangjian on 02/08/2018.
 */
var header = $('#header')
header.css('height', window.innerHeight * 0.15 + 'px')
var main = $('div[id ^="main"]')

//
var main1 = document.getElementById('main1'),
    main2 = document.getElementById('main2'),
    main3 = document.getElementById('main3'),
    main4 = document.getElementById('main4'),
    main5 = document.getElementById('main5'),
    main6 = document.getElementById('main6'),
    main7 = document.getElementById('main7'),
    main8 = document.getElementById('main8')
// //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
var resizemain = function () {
    main.css('width', window.innerWidth / 4 + 'px')
    main.css('height', window.innerHeight * 0.6 / 2 + 'px')
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
var myChart8 = echarts.init(main8, 'walden');


//FR查询调用接口
var host = $("#host").text();
var localhost = 'localhost'
var url = '/dashboard/fr_helper';
console.log(url)

// 获取数据
function get_data(dataset) {
    var params = {
        report: "BIA/财神大屏_new.frm",
        param: JSON.stringify({}),
        dsName: dataset
    };
    var now_hour=moment().hour()
    if (dataset == '用户注册-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                //将返回的数据重组成Object
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)

                var cumsum = formatNumber(last_value(cums_sum_idx_val))

                option1 = get_echarts_option([['昨日'], ['今日']], cumsum,'用户注册-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart1.setOption(option1)
            }});

    } else if (dataset == '用户首投金额-分时') {

        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cumsum = formatNumber(last_value(cums_sum_idx_val).toFixed(2))

                option2 = get_echarts_option([['昨日'], ['今日']], cumsum,'用户首投金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart2.setOption(option2)
            }})

    } else if (dataset == '累计充值金额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cumsum = formatNumber(last_value(cums_sum_idx_val).toFixed(2))


                option3 = get_echarts_option([['昨日'], ['今日']],cumsum, '累计充值金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart3.setOption(option3)

            }})

    }else if (dataset == '累计提现金额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cumsum = formatNumber(last_value(cums_sum_idx_val).toFixed(2))

                option4 = get_echarts_option([['昨日'], ['今日']],cumsum, '累计提现金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart4.setOption(option4)
            }})

    }else if (dataset == '累计投资额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cumsum = formatNumber(last_value(cums_sum_idx_val).toFixed(2))


                option5= get_echarts_option([['昨日'], ['今日']], cumsum,'累计投资额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart5.setOption(option5)
            }})
    }else if (dataset == '各产品累计投资额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var zhaopaicaishen = _.values(data)[0].map(x => x['zhaopaicaishen']);
                var zhaocaika = _.values(data)[0].map(x => x['zhaocaika']);
                var xinrenbao = _.values(data)[0].map(x => x['xinrenbao']);


                var cums_sum_zhaopaicaishen = _.fill(_.map(zhaopaicaishen, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cums_sum_zhaocaika = _.fill(_.map(zhaocaika, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cums_sum_xinrenbao = _.fill(_.map(xinrenbao, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)


                //console.log(cums_sum_idx_val);

                option6= get_echarts_option2([['招牌财神'], ['招财卡'], ['新人宝']],  '各产品累计投资额-累加', hor, [cums_sum_zhaopaicaishen, cums_sum_zhaocaika, cums_sum_xinrenbao], 'line')
                myChart6.setOption(option6)
            }})
    }else if (dataset == '累计结算金额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var last_idx_val = _.values(data)[0].map(x => x['LAST_IDX_VAL']);
                var idx_val = _.values(data)[0].map(x => x['IDX_VAL']);

                var cumsum_last_idx_val = _.map(last_idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1)))
                var cums_sum_idx_val = _.fill(_.map(idx_val, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cumsum = formatNumber(last_value(cums_sum_idx_val).toFixed(2))

                option7= get_echarts_option([['昨日'], ['今日']], cumsum,'累计结算金额-累加', hor, [cumsum_last_idx_val, cums_sum_idx_val], 'line')
                myChart7.setOption(option7)
            }})
    }else if (dataset == '各产品累计结算金额-分时') {
        $.ajax({ type :"get", url :url, data :params, async :false, success :function (data, status) {
                var hor = _.values(data)[0].map(x => x['HOR']);
                var zhaopaicaishen = _.values(data)[0].map(x => x['zhaopaicaishen']);
                var zhaocaika = _.values(data)[0].map(x => x['zhaocaika']);
                var xinrenbao = _.values(data)[0].map(x => x['xinrenbao']);


                var cums_sum_zhaopaicaishen = _.fill(_.map(zhaopaicaishen, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cums_sum_zhaocaika = _.fill(_.map(zhaocaika, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)
                var cums_sum_xinrenbao = _.fill(_.map(xinrenbao, (value, index, array) => _.sum(_.slice(array, 0, index + 1))),null,now_hour)


                //console.log(cums_sum_idx_val);

                option8= get_echarts_option2([['招牌财神'], ['招财卡'], ['新人宝']], '各产品累计结算金额-累加', hor, [cums_sum_zhaopaicaishen, cums_sum_zhaocaika, cums_sum_xinrenbao], 'line')
                myChart8.setOption(option8)
            }})
    }

}

get_data('用户注册-分时')
get_data('用户首投金额-分时')
get_data('累计充值金额-分时')
get_data('累计提现金额-分时')
get_data('累计投资额-分时')
get_data('各产品累计投资额-分时')
get_data('累计结算金额-分时')
get_data('各产品累计结算金额-分时')

// 获得echarts定制化option
function get_echarts_option(legend_data, cunsumTitle, title, xAxis_data, series_data, series_data_type) {
    var option = {
        legend: [{
            right: 10,
            top: 10,
            data: legend_data[0]
        },
            {
                right: 10,
                top: 25,
                data: legend_data[1]
            },
        ],
        tooltip: {},
        title: [{
            text: cunsumTitle,
            top: '5%',
            textAlign: 'center',
            left: '50%',
            textStyle: {
                fontSize: 15
                //color: 'rgba(255, 255, 255, 1.5)'
            }
        }, {
            text: title,
            top: '-1%',
            left: '50%',
            textAlign: 'center',
            textStyle: {
                //color: '#aaa',
                //fontWeight: 'normal',
                fontSize: 12
            }
        }],
        // title: {
        //     text: title_text,
        //     x: 'center'
        // },
        grid: {
            top: 50,
            left: 100
        },
        xAxis: {
            type: 'category',
            splitLine: {
                show: false
            },
            data: xAxis_data,
            axisTick: {
                alignWithLabel: true
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
                data: series_data[0],

            },
            {
                name: '今日',
                type: series_data_type,
                data: series_data[1],
                //hoverAnimation: true,
            },


        ],
        textStyle: {
            color: "#aaa"
        }
    }

    return option
}

//
function get_echarts_option2(legend_data, title_text, xAxis_data, series_data, series_data_type) {
    var option = {
        legend: [{
            right: 10,
            top: 20,
            data: legend_data[0]
        },
            {
                right: 10,
                top: 40,
                data: legend_data[1]
            },
            {
                right: 10,
                top: 60,
                data: legend_data[2]
            }
        ],
        tooltip: {},
        title: {
            text: title_text,
            x: 'center',
            textStyle: {
                //color: '#aaa',
                //fontWeight: 'normal',
                fontSize: 15
            }
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
            axisTick: {
                alignWithLabel: true
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
                name: '招牌财神',
                type: series_data_type,
                data: series_data[0]
            },
            {
                name: '招财卡',
                type: series_data_type,
                data: series_data[1]
            },
            {
                name: '新人宝',
                type: series_data_type,
                data: series_data[2]
            },

        ]
    }

    return option
}

function last_value(array) {
    for (let i = 0; i < array.length; i++) {
        if (array[i] == null ) {
            return array[i-1]
        }
    }

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

var now_time=document.getElementById('now')
now_time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())


setInterval(function () {
    now_time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())
},1000);

// setInterval(function () {
//     $('#example1').slideToggle('slow').delay(2000)
//     $('#example2').slideToggle('slow')
// }, 10000)

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
    myChart8.setOption(option8)
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart4.resize();
    myChart5.resize();
    myChart6.resize();
    myChart7.resize();
    myChart8.resize();
};


