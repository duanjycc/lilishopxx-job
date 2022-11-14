package com.xxl.job.executor.jobcalculate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.util.HttpRequest;
import com.xxl.job.executor.jobcalculate.dao.CalculateDao;
import com.xxl.job.executor.jobcalculate.mai.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxl.job.executor.jobcalculate.service.CalculateService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {


    @Autowired
    CalculateDao calculateDao;

    @Override
    public void internalprice() {
        SimpleDateFormat startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        String startTimef=startTime.format(new Date());
        String endTimef=endTime.format(new Date());

        //查找配置每天发行量
        Configure co= calculateDao.internalprice("dailyDistribution");

        //查找每天销毁量
        double xhzo=calculateDao.getsumzonx(startTimef,endTimef);

        //查找当前价格
        Configure counitPrice= calculateDao.internalprice("unitPrice");
        double price=counitPrice.getNumericalAlue();
        //查找市场总量
        double sczl=calculateDao.getsczl();

        if(co.getNumericalAlue()>xhzo){
            double yu=co.getNumericalAlue()-xhzo;
            double p=yu/sczl;
            price=counitPrice.getNumericalAlue()*p;
            price=counitPrice.getNumericalAlue()-price;
        }else {
            double yu=xhzo-co.getNumericalAlue();
            double p=yu/sczl;
            price=counitPrice.getNumericalAlue()*p;
            price=counitPrice.getNumericalAlue()+price;
        }

        //修改价格
        calculateDao.updateConf("unitPrice",price);


    }

    @Override
    public void warrantCalculationPower() {
        //查找配置每天发行量
        Configure co= calculateDao.internalprice("dailyDistribution");

        //查找积分总数量
        long totalpoint =calculateDao.gettotalpoint();

        //查找当前价格
        Configure counitPrice= calculateDao.internalprice("unitPrice");

        //查出有积分的会员
        List<Member> mlist=calculateDao.getmlist();

        //根据会员积分占比分配SSD

        for(int i=0;mlist.size()>i;i++){
            //计算出占比
           double zb= (double)mlist.get(i).getPoint()/(double)totalpoint;
           //计算出可以获得的ssd卷
           double ssdsum= zb*co.getNumericalAlue();

           //减去的积分
           double point=ssdsum*counitPrice.getNumericalAlue();
           //判断卷的价值是否大于积分
           if(ssdsum*counitPrice.getNumericalAlue()>mlist.get(i).getPoint()){
               ssdsum=mlist.get(i).getPoint()/counitPrice.getNumericalAlue();
               point=mlist.get(i).getPoint();
           }

           //修改会员SSD数量
           int m=calculateDao.updatessdnum(mlist.get(i).getId(),ssdsum,ssdsum*counitPrice.getNumericalAlue());

           //插入会员收益表
            MemberIncome mi=new MemberIncome();
            mi.setUserId(Long.valueOf(mlist.get(i).getId()));
            mi.setCreationTime(new Date());
            mi.setQuantity(ssdsum);
            mi.setIncomeProportion(zb*100+"");
            mi.setIncomeType("1");
            calculateDao.isertssdm(mi);
        }
    }

    public static void main(String[] args) {
        double ssdsum=3.87359954;
        double point=ssdsum*35.0000;
        System.out.println(point);
    }
    @Override
    public void operationalAllocation() {
        //查找配置每天运营卷数量
        Configure co= calculateDao.internalprice("dailyOperation");

        List<Configure> list=calculateDao.operationalList("运营账号");

        for(int i=0;i<list.size();i++){
            Double yh=list.get(i).getNumericalAlue()*co.getNumericalAlue();

            //修改会员SSD数量
            int m=calculateDao.updatessdnum(list.get(i).getType(),yh,0);

            //插入会员收益表
            MemberIncome mi=new MemberIncome();
            mi.setUserId(Long.valueOf(list.get(i).getType()));
            mi.setCreationTime(new Date());
            mi.setQuantity(yh);
            mi.setIncomeProportion(list.get(i).getNumericalAlue()+"");
            mi.setIncomeType("2");
            calculateDao.isertssdm(mi);
        }
    }

    @Override
    public void cleanUpTheDeity() {
        calculateDao.cleanUpTheDeity();
    }

    @Override
    public void issuanceDestruction() {
        //查找当前价格
        Configure counitPrice= calculateDao.internalprice("unitPrice");
        //查找配置销毁量
        Configure co= calculateDao.internalprice("jbxh");
        DestroyDetail de = new DestroyDetail();
        de.setUserId(0000000000l);
        de.setCreateTime(new Date());
        de.setMerName("减半销毁");
        de.setPrice(0.0);
        de.setWantCount(co.getNumericalAlue());
        de.setStatus("0");
        de.setDestroyTime(new Date());
        de.setWantPrice(counitPrice.getNumericalAlue()+"");
        de.setStoreId("0000000000");

        calculateDao.issuanceDestruction(de);
    }

    @Override
    public void destructionAmount() {
        //查询当前系统销毁总量
        Double xtxh=calculateDao.selctxtxul()*100000000;

        //查询区块链上的销毁总量
        String rs= HttpRequest.sendPost("http://localhost:3600"+"/zmm/test/queryToken/",
                "address=TUeZMs864qVJCoKQEtuNKnK5FoBc3FKNWw"+
                        "&contract=TCPrL5GK8c2HjWhWeYhqaymKdFjfLUjipd"
        );
        JSONObject jsonObjecttx =JSONObject.parseObject(rs);
        Double amount =Double.parseDouble(jsonObjecttx.get("amount").toString());
        //转入差值到区块链销毁地址上

        //差值
        long cha= (long) (xtxh-amount);

        //
        String rszr=HttpRequest.sendPost("http://localhost:3600"+"/zmm/test/sendToken/",
                "address="+
                        "&pk="+
                        "&contract="+
                        "&get="+
                        "&amount="+cha+
                        "&remark=zk"
        );

    }

    @Override
    public void pricesynchronization() {
        Double jrxhl=calculateDao.selctxtxuljrxh();
        //查找配置每天发行量加减价格
        PriceCalculationConfiguration co= calculateDao.selctpccf(jrxhl);
        if(co!=null){
            //修改价格
            if(co.getAddSubtract()==0){
                calculateDao.updatepricjian(co.getPrice());
            }else{
                calculateDao.updatepricadd(co.getPrice());
            }
        }
    }
}
