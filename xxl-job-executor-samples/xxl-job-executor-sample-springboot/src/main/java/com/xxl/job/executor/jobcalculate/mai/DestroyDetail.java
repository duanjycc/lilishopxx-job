/*
 * Copyright © 2022- ~ hc R&D 电信支撑部/产品研发中心 All rights reserved.
 */
package com.xxl.job.executor.jobcalculate.mai;


import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 销毁明细
 * </p>
 *
 * @author zh
 * @since 2022-08-05
 */
@Data

public class DestroyDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 销毁时间
     */
    private Date destroyTime;


    /**
     * 商铺名称
     */
    private String merName;


    /**
     * 让利金额
     */
    private Double price;


    /**
     * want数量
     */
    private Double wantCount;


    /**
     * 销毁状态（0:销毁成功，1:销毁失败）
     */
    private String status;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 做单时want单价
     */
    private String wantPrice;

    /**
     * 店铺ID
     */
    private String storeId;

}
