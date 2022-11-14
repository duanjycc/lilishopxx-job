package com.xxl.job.executor.jobcalculate.dao;


import java.util.List;

import com.xxl.job.executor.jobcalculate.mai.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface CalculateDao {

    Configure internalprice(@Param("type")String type);

    List<Member> getmlist();

    long gettotalpoint();

    int updatessdnum(@Param("id")String id, @Param("ssdsum")double ssdsum, @Param("v")double v);

    int isertssdm(@Param("mi")MemberIncome mi);

    double getsumzonx(@Param("startTimef")String startTimef, @Param("endTimef")String endTimef);

    double getsczl();

    void updateConf(@Param("type")String type, @Param("price")double price);

    List<Configure> operationalList(@Param("yyzh")String yyzh);

    int cleanUpTheDeity();

    int issuanceDestruction(@Param("de")DestroyDetail de);

    Double selctxtxul();

    PriceCalculationConfiguration selctpccf(@Param("xtxh")Double xtxh);

    Double selctxtxuljrxh();

    int updatepricjian(@Param("price")double price);

    int updatepricadd(@Param("price")double price);
}
