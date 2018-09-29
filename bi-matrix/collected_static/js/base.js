function renderTable(params, id,title) {
    var table_name=title||"查询结果"
    $.get("/ajax/api",params, function (data) {

        for (var item in data['data']) {
            if( data['data'][item]["am_id"] == ""){
                break;
            }else{
                data['data'][item]["am_id"] = channel_name_list[data['data'][item]["am_id"]]
            }
        }

        var date=moment().add(-15, 'days').format('MMDD');
        var title=$("#title").text()
        $(id).bootstrapTable('destroy').bootstrapTable({
            data: data['data'],
            exportDataType:"all",
            exportOptions:{
                fileName: title+'_'+date,  //文件名称设置
                worksheetName: title+'_'+date,  //表格工作区名称
                tableName: title+'明细数据',
            },
            sortName: "date",
            sortOrder: "desc", //排序方式
            sortable: true
        })
        $(".fixed-table-toolbar").prepend("<div class='columns columns-left btn-group pull-left checkResult'><h4>"+table_name+"</h4></div>")

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


