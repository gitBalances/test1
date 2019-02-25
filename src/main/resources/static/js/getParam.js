/*获取json数据中的某一个字段*/
function getParamValues(name, json) {
    var ret = [];
    var len = Object.keys(json).length;
    for (var i = 0; i < len; i++) {
        ret.push(json[i][name]);
    }
    return ret;
}

/*获取json数据中的某一个字段key根据给定value => 数组*/
function getParamValuesByCname(name, json, key, value) {
    var ret = [];
    var len = Object.keys(json).length;

    for (var i = 0; i < len; i++) {
        if (json[i][key] == value) {
            ret.push(json[i][name]);
        }
    }
    return ret;
}


/*数组去重*/
Array.prototype.delDuplicate = function () {
    var arr = [];
    for (var i = 0; i < this.length; i++) {
        if (arr.indexOf(this[i]) == -1) {
            arr.push(this[i]);
        }
    }
    return arr;
}


// /*数组去重方法版本*/
// function delDuplicateArrayItem(array){
//     var arr = [];
//     for(var i in array){
//         if(arr.indexOf(array[i]) == -1){
//             arr.push(array[i]);
//         }
//     }
//     return arr;
// }


/*遍历js数组*/
// Array.prototype.traversal = function(){
//     var arrStr = '';
//     for(var i=0;i<this.length;i++){
//         arrStr += this[i];
//     }
//     return arrStr;
// }

//获取以公司为key的todayprice数组
/*newJson = getJsonResult("today_price", data, "company", companys);*/
/*返回:{'companyName1':[2323,5321],'companyName2':[3000,2311],'companyName3':[2703,1688]}*/
/*name为要查询的生成数组的name,data为数据json,key为要根据哪个键去进行数组分组,values为key的数组集合*/
function getJsonResult(name, json, key, values) {
    var DataJSONStr = '';
    var ret = [];
    var len = Object.keys(json).length;
    for (var j = 0; j < values.length; j++) {
        for (var i = 0; i < len; i++) {
            if (json[i][key] == values[j]) {
                ret.push(json[i][name]);
            }
        }
        if (j != values.length - 1) {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "],");
            ret = [];
        } else {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "]");
            ret = [];
        }
    }
    var jsonStr = DataJSONStr;
    console.log("jsonStr:" + jsonStr);
    return jsonStr;
}

/*返回source中的填充数据: (格式如下)*/
// {
//     'createTimes':['2012', '2013', '2014', '2015'],
//     'companyName1': [41.1, 30.4, 65.1, 53.3],
//     'companyName2': [86.5, 92.1, 85.7, 83.1],
//     'companyName3': [24.1, 67.2, 79.5, 86.4]
// }
/*新增参数times为指定的times的字符串名称*/
function getJsonResultPro(name, json, key, values, times) {
    var DataJSONStr = '';
    var ret = [];
    var len = Object.keys(json).length;
    for (var j = 0; j < values.length; j++) {
        for (var i = 0; i < len; i++) {
            if (json[i][key] == values[j]) {
                // console.log("values[i]:" + values[i]);
                // console.log("json[i][name]:" + json[i][name]);
                ret.push(json[i][name]);
            }
        }
        if (j != values.length - 1) {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "],");
            ret = [];
        } else {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "]");
            ret = [];
        }
    }
    var createTimes = getParamValues("create_time", json).delDuplicate();
    var jsonStr = "'" + times + "'" + ":[" + createTimes + "]," + DataJSONStr;
    return jsonStr;
}


//根据输入公司名获取公司的历史价格数组
// function getPriceByCname(cname,dataJson){
//     var ret = [];
//     var len = Object.keys(dataJson).length;
//     console.log("dataJson:"+dataJson);
//
//     for (var i = 0; i < len; i++) {
//         // alert(JSON.stringify(dataJson));
//         if (dataJson[i]) {
//
//             // alert("dataJson[i][cname]:"+dataJson[i][cname]);
//         }
//     }
//     // }
//     return ret;
//
// }



//根据传入的json数据,
// function getArrByName(name,json){
//     var b = json.hasOwnProperty(name);
//     console.log("b:>>>"+b);
//     return b;
// }

/*size:需要设定几个. typeIn:line,row? . seriesLayout:根据什么排列数据*/
function getSeriesContent(size, typeIn, seriesLayout) {
    var contentArr = [];
    for (var i = 0; i < size; i++) {
        contentArr.push({type: typeIn, seriesLayoutBy: seriesLayout});
    } 
    return contentArr;
}


// <!--JSON填充形式(no)-->

function getJsonResultPro(name, json, key, values, times) {
    var DataJSONStr = '';
    var ret = [];
    var len = Object.keys(json).length;
    // = getParamValues("create_time", json).delDuplicate();
    for (var j = 0; j < values.length; j++) {
        for (var i = 0; i < len; i++) {
            if (json[i][key] == values[j]) {
                ret.push(json[i][name]);
            }
        }
        if (j != values.length - 1) {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "],");
            ret = [];
        } else {
            DataJSONStr += ("'" + values[j] + "':[" + ret + "]");
            ret = [];
        }
    }
    var createTimes = getCreateTimes("create_time", json).delDuplicate();
    var jsonStr = "'" + times + "'" + ":[" + createTimes + "]," + DataJSONStr + "";
    return jsonStr;
}


// <!--Array填充形式-->
// <!--// source: [-->
// <!--//     ['product', '2012', '2013', '2014', '2015'],-->
// <!--//     ['Matcha Latte', 41.1, 30.4, 65.1, 53.3],-->
// <!--//     ['Milk Tea', 86.5, 92.1, 85.7, 83.1],-->
// <!--//     ['Cheese Cocoa', 24.1, 67.2, 79.5, 86.4],-->
// <!--//     ['sulfur1',1.4,1.2,1.5,0.5],-->
// <!--//     ['sulfur2',2.4,3.2,1.5,1.5],-->
// <!--//     ['sulfur3',2.4,3.2,1.5,1.5],-->
// <!--// ],-->
// <!--['createTimes','2018-06-19 16:00:00','2019-01-20 16:00:00','2019-01-21 16:00:00'],-->
// <!--['华联石化',2323,5321,2422],-->
// <!--['海油石化',3000,2311,3211],-->
// <!--['昌邑石化',2703,1688,2900]-->
function getArrayResultPro(name, json, key, values, timename,timeArr) {

    var ret = [];
    // var createTimes = getCreateTimes("create_time", json).delDuplicate();
    var timesArrStr = timename + "," + timeArr;
    var timesArr = timesArrStr.split(",");
    ret.push(timesArr); //放入首行times集合
    var price = [];
    var priceStr = '';
    var len = Object.keys(json).length;
    // = getParamValues("create_time", json).delDuplicate();
    for (var j = 0; j < values.length; j++) {
        for (var i = 0; i < len; i++) {
            if (json[i][key] == values[j]) {
                priceStr += json[i][name] + ",";
            }

        }
        var s = values[j] + "," + priceStr.substring(0, priceStr.length - 1);
        priceStr = '';
        ret.push(s.split(","));
    }
    return ret;
}

<!--获取时间数组-->
function getCreateTimes(name, json) {
    var ret = [];
    var len = Object.keys(json).length;
    for (var i = 0; i < len; i++) {
        ret.push("'" + json[i][name] + "'");
    }
    return ret;
}

//将普通形式数组转为有''加持的.(在ECharts中一般在之前配合数据的时间进行设置)
//[1,2,3]or[nihao,shijie,dajia,doushuo,shijie,henda] ==>
//['1','2','3']or['nihao','shijie','dajia','doushuo','shijie','henda']
//本方法最终返回形式: ['timeName',"companyName1","companyName2","companyName3"]
function twoArrayToOne(companys,timeName){
    var timeAndcompanyArray = [];
    timeAndcompanyArray.push(timeName);
    for(var i in companys){
        timeAndcompanyArray.push(companys.map(String)[i]);
    }
    return timeAndcompanyArray;
}


//将普通形式数组转为有''加持的.(在ECharts中一般在之前配合数据的时间进行设置)
//[1,2,3]or[nihao,shijie,dajia,doushuo,shijie,henda] ==>
//['1','2','3']or['nihao','shijie','dajia','doushuo','shijie','henda']
function changeToStringArr(timeAndCompanyArray){
    var resultArr = [];
    // TODO 改变加入方式
    /**
     * 如果直接将companysStrArr加入timeAndcompanyArr数组则形式不正确;
     * 不正确方式:['createTime',['companyName1','companyName2','companyName3']]
     */
    for(var i in timeAndCompanyArray){
        resultArr.push(timeAndCompanyArray.map(String)[i]);
    }
    return resultArr;
}





