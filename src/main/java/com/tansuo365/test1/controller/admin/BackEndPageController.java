package com.tansuo365.test1.controller.admin;

import com.sun.xml.internal.bind.v2.TODO;
import com.tansuo365.test1.bean.user.User;
import com.tansuo365.test1.mapper.UserMapper;
import com.tansuo365.test1.service.user.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 后端页面控制层 (需权限判定)
 * 1-12 加入easyui页面
 */
@Controller
@RequestMapping("/admin")
public class BackEndPageController {

//    private static final String ADMIN_LOGIN_TYPE = LoginEnum.ADMIN.toString();

    @Autowired
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    /*==============会员管理=============*/

    //前端会员
    @RequestMapping("/member/members")
    @RequiresPermissions(value = {"会员信息管理"})
    public String members(){
        return "/admin/member/members";
    }

    //会员等级, 角色 配置
    @RequestMapping("/member/mrole")
    @RequiresPermissions(value = {"会员等级管理"})
    public String mrole(){
        return "/admin/member/mrole";
    }

    /*==============货品价格管理=============*/

    //石油焦
    @RequestMapping("/goods/coke")
    @RequiresPermissions(value = {"石油焦信息"})
    public String coke(){
        return "/admin/goods/coke";
    }

    //煅后焦
    @RequestMapping("/goods/calcined")
    @RequiresPermissions(value = {"煅后焦信息"})
    public String calcined(){
        return "/admin/goods/calcined";
    }

    //沥青
    @RequestMapping("/goods/asphalt")
    @RequiresPermissions(value = {"改质沥青信息"})
    public String asphalt(){
        return "/admin/goods/asphalt";
    }

    //阳极
    @RequestMapping("/goods/anode")
    @RequiresPermissions(value = {"阳极信息"})
    public String anode(){
        return "/admin/goods/anode";
    }


    /*===============员工管理===============*/

    //人员管理
    @RequestMapping("/employees/employees")
    @RequiresPermissions(value = {"员工信息管理"})
    public String employees(){
        return "/admin/employees/employees";
    }

    /*=============管理员/权限管理===========*/

    //后台管理员table 外加角色设置
    @RequestMapping("/power/user")
    @RequiresPermissions(value = {"后台用户管理"})
    public String user(){
        return "/admin/power/user";
    }

    //后台管理员的Role设定页面
//    @RequestMapping("/power/role")
//    public String userRole(){
//        return "/admin/power/role";
//    }

    /*=============系统配置===========*/

    //树形菜单
    @RequestMapping("/auth/menu")
    @RequiresPermissions(value = {"路径配置"})
    public String eMenu(){
        return "/admin/auth/menu";
    }

    //数据库备份
    @RequestMapping("/auth/dbbackup")
    @RequiresPermissions(value = {"数据库备份"})
    public String dbbackup(){
        return "/admin/auth/dbbackup";
    }

    //管理员角色表设定
    @RequestMapping("/auth/roleconfig")
    @RequiresPermissions(value = {"系统角色管理"})
    public String roleconfig(){
        return "admin/auth/roleconfig";
    }

    //系统日志
    @RequestMapping("/auth/syslog")
    @RequiresPermissions(value = {"系统日志"})
    public String syslog(){
        return "/admin/auth/syslog";
    }

    //系统设置
    @RequestMapping("/auth/sysconfig")
    @RequiresPermissions(value = {"系统配置"})
    public String sysconfig(){
        return "/admin/auth/sysconfig";
    }




    /*=================基本路径===================*/

    @RequestMapping("")
    @RequiresAuthentication
    public String indexMain(){
        return "/admin/index";
    }


    /*===============页面管理===============*/

    @RequestMapping("/main")
    @RequiresAuthentication
    public String main(){
        return "/admin/main";
    }

    /*老版本*/
    @RequestMapping("/index")
    @RequiresAuthentication
    public String index(){
        return "/admin/index"; //52行跳转过来
    }

//    @RequestMapping("/deleteOrder")
//    public String deleteOrder(){
//        return "/admin/deleteOrder";
//    }
//
//    @RequestMapping("/deleteProduct")
//    public String deleteProduct(){
//        return "/admin/deleteProduct";
//    }
//
//    @RequestMapping("/listProduct")
//    public String listProduct(){
//        return "/admin/listProduct";
//    }




    //注册页面
    @RequestMapping("/reg")
    public String reg(){
        return "/admin/reg";
    }

    //注册功能
    @ResponseBody
    @RequestMapping("/register")
    public Map<String,Object> reg(User user){

        Map<String,Object> map = new HashMap<>();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName, user.getPassword(), salt, times).toString();
        user.setPassword(encodedPassword);
        user.setSalt(salt);


        int insertCode = 0;
        try {
            insertCode = userMapper.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(insertCode>0){
            map.put("code",1);
            map.put("message","注册成功,请登录");
            return map;
//            return "redirect:/admin";
        }else{
            map.put("code",0);
            map.put("message","注册失败,请再试一次");
            return map;
//            return "/admin/reg";
        }
    }

    @RequestMapping("/unauthorized")
    public String noPerms(){
        return "/admin/unauthorized";
    }

    @RequestMapping("/file")
    @RequiresAuthentication
    public String file(){
        return "/admin/file";
    }





}
