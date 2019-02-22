package com.tansuo365.test1.bean.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tansuo365.test1.bean.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

/**
 * 管理员日志
 * {@link #LogUser(String type,String content) 传入操作类型,操作内容}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogUser {

    private Integer id; //日志编号

    private String content; //操作内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date time; //操作时间

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Transient
    private String b_time; //起始时间 搜索用到

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Transient
    private String e_time; //结束时间 搜索用到

    private String type; //日志类型

    @ManyToOne
    private Integer user_id; //操作用户id

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user; // 操作用户

    public LogUser(String type,String content) {
        this.type = type;
        this.content = content;
    }
}