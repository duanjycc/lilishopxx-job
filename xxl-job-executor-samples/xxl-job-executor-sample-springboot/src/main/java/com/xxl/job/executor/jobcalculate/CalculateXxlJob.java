package com.xxl.job.executor.jobcalculate;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.executor.jobcalculate.service.CalculateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class CalculateXxlJob {
    private static Logger logger = LoggerFactory.getLogger(CalculateXxlJob.class);

    @Autowired
    CalculateService calculateService;

    //每天计算价格
    @XxlJob("internalprice")
    public void internalprice() throws Exception {
        calculateService.internalprice();
    }

    //每天根据积分分配SSD卷
    @XxlJob("warrantCalculationPower")
    public void warrantCalculationPower() throws Exception {
        calculateService.warrantCalculationPower();
    }


    //每天运营卷分配SSD卷
    @XxlJob("operationalAllocation")
    public void operationalAllocation() throws Exception {
        calculateService.operationalAllocation();
    }

    //每天清理挂单
    @XxlJob("cleanUpTheDeity")
    public void cleanUpTheDeity() throws Exception {
        calculateService.cleanUpTheDeity();
    }

    //每天发出减半直接进入销毁
    @XxlJob("issuanceDestruction")
    public void issuanceDestruction() throws Exception {
        calculateService.issuanceDestruction();
    }

    //每天同步销毁量
    @XxlJob("destructionAmount")
    public void destructionAmount() throws Exception {
        calculateService.destructionAmount();
    }

    //每天价格同步
    @XxlJob("pricesynchronization")
    public void pricesynchronization() throws Exception {
        calculateService.pricesynchronization();
    }
}
