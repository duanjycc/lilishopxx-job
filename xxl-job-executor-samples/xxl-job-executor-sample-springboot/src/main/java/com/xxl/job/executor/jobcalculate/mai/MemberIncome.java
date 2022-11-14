/*
 * Copyright © 2022- ~ hc R&D 电信支撑部/产品研发中心 All rights reserved.
 */
package com.xxl.job.executor.jobcalculate.mai;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * <p>
 * 会员收益表
 * </p>
 *
 * @author zh
 * @since 2022-08-05
 */
@Data
public class MemberIncome{

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Long id;


    /**
     * 消费会员id
     */
    private Long consumerUserid;


    /**
     * 会员ID
     */
    private Long userId;


    /**
     * 创建时间
     */
    private Date creationTime;


    /**
     * 收益数量
     */
    private Double quantity;


    /**
     * 当前收益百分比
     */
    private String incomeProportion;


    /**
     * 做单ID
     */
    private String orderId;

    /**
     * 收益类型
     */
    private String incomeType;

}
