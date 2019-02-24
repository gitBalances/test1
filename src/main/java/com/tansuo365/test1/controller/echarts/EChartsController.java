package com.tansuo365.test1.controller.echarts;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "ECharts图表控制层",description = "ECharts图表控制层,对多种图表的测试处理")
@Controller
@RequestMapping("/data")
public class EChartsController {


//    @RequestMapping("/common")
//    public String common(){
//        return "/admin/data/common";
//    }

    @ApiOperation(value="echarts0", notes="[曲线图]/data/echarts0")
    @RequestMapping("/echarts0")
    public String echarts0(){
        return "/data/echarts0";
    }
}