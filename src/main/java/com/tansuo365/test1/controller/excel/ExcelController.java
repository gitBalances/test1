package com.tansuo365.test1.controller.excel;

import com.tansuo365.test1.bean.goods.Anode;
import com.tansuo365.test1.bean.goods.CalcinedCoke;
import com.tansuo365.test1.bean.goods.MAsphalt;
import com.tansuo365.test1.bean.goods.PetroleumCoke;
import com.tansuo365.test1.bean.log.LogEnum;
import com.tansuo365.test1.entity.Goods;
import com.tansuo365.test1.excel.ExcelLogs;
import com.tansuo365.test1.excel.ExcelUtil;
import com.tansuo365.test1.mapper.goods.AnodeMapper;
import com.tansuo365.test1.mapper.goods.CalcinedCokeMapper;
import com.tansuo365.test1.mapper.goods.MAsphaltMapper;
import com.tansuo365.test1.mapper.goods.PetroleumCokeMapper;
import com.tansuo365.test1.service.goods.GoodsCommonService;
import com.tansuo365.test1.util.CodeJudgerUtils;
import com.tansuo365.test1.util.PetroleumCokeGradeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用于excel导入的controller
 * {@link #importExcel 导入excel,接收excel导入,文件为uploadFile,接收文件参数String instance标识上传类型}
 */
@Api(value = "Excel导入控制层",description = "Excel导入控制层,导出使用js插件")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private GoodsCommonService goodsCommonService;
    @Autowired
    private CodeJudgerUtils codeJudgerUtils;

    @Resource
    private PetroleumCokeMapper petroleumCokeMapper;
    @Resource
    private CalcinedCokeMapper calcinedCokeMapper;
    @Resource
    private MAsphaltMapper mAsphaltMapper;
    @Resource
    private AnodeMapper anodeMapper;

    private Class C;

    public Class getC() {
        return this.C;
    }

    public void setC(Class c) {
        this.C = c;
    }

    @ApiOperation(value="导入Excel", notes="导入Excel")
    @RequestMapping("/importExcel")
    public Integer importExcel(@RequestParam(value = "uploadFile") MultipartFile uploadFile,
                               @RequestParam(value = "instance") String instance, Model model) {
        InputStream in = null;
        System.out.println("in Excel Controller importExcel method;");
        //获取前台excel输入流
        try {
            in = uploadFile.getInputStream();
            System.err.println(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //excel的表头与文字对应,获取excel表头.
        if (uploadFile.getOriginalFilename().isEmpty() || uploadFile.getSize() == 0) {
            String message = "上传失败";
            model.addAttribute("m", message);
        }
        ExcelLogs log = new ExcelLogs();
        int code = 0;
        List<Goods> list = null;
        //通过前端instance实例类型进行Class和Mapper类型的设置
        instanceJudge(instance);
        //获取货品泛型集合
        Collection<Goods> goodsCollection = ExcelUtil.importExcel(getC(), in, "yyyy/MM/dd HH:mm:ss", log, 0);
        list = (List) goodsCollection;
        System.out.println(">>>list in excelcontro : "+list);
        code = goodsCommonService.insertBatchList(list);

//        if(instance.equals("石油焦")){
//            List<Goods> newList = null;
//            for(Goods goods:list){
//                PetroleumCoke petroleumCoke = (PetroleumCoke) goods;
//                Goods newGoodsAfterSetGrade = PetroleumCokeGradeUtil.setGradeBySulfur(petroleumCoke);
//                if(newList == null){
//                    newList = new ArrayList<>();
//                }
//                newList.add(newGoodsAfterSetGrade);
//            }
//            code = goodsCommonService.insertBatchList(newList);
//        }else{

//        }

        codeJudgerUtils.whichCodeIsOK(list, code, LogEnum.ADD_ACTION.toString(), instance);

        return code;
    }

    //判定前端导入instance类型
    private void instanceJudge(String instance) {
        if (instance.equals("石油焦")) {
            goodsCommonService.setGoodsTypeMapper(petroleumCokeMapper);
            setC(PetroleumCoke.class);
        }
        if (instance.equals("煅后焦")) {
            goodsCommonService.setGoodsTypeMapper(calcinedCokeMapper);
            setC(CalcinedCoke.class);
        }
        if (instance.equals("改质沥青")) {
            goodsCommonService.setGoodsTypeMapper(mAsphaltMapper);
            setC(MAsphalt.class);
        }
        if (instance.equals("阳极")) {
            goodsCommonService.setGoodsTypeMapper(anodeMapper);
            setC(Anode.class);
        }
    }
}