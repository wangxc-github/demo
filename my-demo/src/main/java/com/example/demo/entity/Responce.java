package com.example.demo.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author wangxc
 */
@Data
public class Responce {

    public static final String SUCCESS = "200";

    public static final String FAIL = "500";

    private String code = SUCCESS;

    private String msg = "操作成功";

    private Map datasouce = null;

}
