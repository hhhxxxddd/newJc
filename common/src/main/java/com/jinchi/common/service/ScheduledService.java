package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.RealTimeData;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.RealTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 定时任务服务
 */

@Component
public class ScheduledService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);
    @Value("${enable.timeService}")
    Boolean enableTimeService;
    @Value("${realTime.url}")
    String realTimeUrl;

    String[] cellNums = {"合成槽701", "合成槽702", "合成槽703", "合成槽704",
            "合成槽705", "合成槽706", "合成槽707", "合成槽708",
            "合成槽801", "合成槽802", "合成槽803", "合成槽804",
            "合成槽805", "合成槽806", "合成槽807", "合成槽808",};

    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;
    @Autowired
    AppDeviceStatisticHeadMapper appDeviceStatisticHeadMapper;
    @Autowired
    AppDeviceStatisticDetailMapper deviceStatisticDetailMapper;
    @Autowired
    AppDeviceStatisticDetailMapper appDeviceStatisticDetailMapper;
    @Autowired
    AppSpotcheckStatisticHeadMapper appSpotcheckStatisticHeadMapper;
    @Autowired
    AppSpotcheckStatisticDetailMapper appSpotcheckStatisticDetailMapper;
    @Autowired
    DeviceSpotcheckRecordHeadMapper deviceSpotcheckRecordHeadMapper;
    @Autowired
    DeviceSpotcheckRecordDetailsMapper deviceSpotcheckRecordDetailsMapper;
    @Autowired
    ProductionInstrumentDataMapper instrumentDataMapper;
    @Autowired
    ProductionBatchInfoMapper batchInfoMapper;
    @Autowired
    ProductionBatchInfoService batchInfoService;
    @Autowired
    ProductionInstrumentAddressMapper addressMapper;

    @Scheduled(cron = "0 9 15 * * ?")
    void scheduleTest() {
    }

    /**
     * 将device_document_main中的设备信息进行更新，将所有设备信息，放入统计的几个头表之中去
     * 执行时间：每天的0：30
     */
    @Transactional
    @Scheduled(cron = "0 30 0 * * ?")
    void getAllDeviceInfoEveryDay() {
        if (enableTimeService) {
            logger.info("开始执行任务：每天0：30执行设备信息统计服务");
            //获取所有部门
            BasicInfoDeptExample example = new BasicInfoDeptExample();
            example.createCriteria();
            List<BasicInfoDept> depts = basicInfoDeptMapper.selectByExample(example);
            Date now = new Date();

            for (int i = 0; i < depts.size(); i++) {
                AppDeviceStatisticHead head = new AppDeviceStatisticHead();
                head.setDeptCode(depts.get(i).getCode());
                head.setDeptName(depts.get(i).getName());
                head.setStatisticalDate(now);

                DeviceDocumentMainExample example1 = new DeviceDocumentMainExample();
                example1.createCriteria().andDeptCodeEqualTo(depts.get(i).getCode());
                List<DeviceDocumentMain> mains = deviceDocumentMainMapper.selectByExample(example1);
                int totalDevice = mains.size();
                head.setTotalDevice(totalDevice);

                int[] nums = new int[4];
                for (int j = 1; j <= 4; j++) {
                    example1.clear();
                    example1.createCriteria().andDeptCodeEqualTo(depts.get(i).getCode()).andStatusCodeEqualTo(j);
                    nums[j - 1] = deviceDocumentMainMapper.countByExample(example1);
                }
                head.setTotalInService(nums[0]);
                head.setTotalStandby(nums[1]);
                head.setTotalRecondition(nums[2]);
                head.setTotalScrap(nums[3]);
                appDeviceStatisticHeadMapper.insertSelective(head);

                //开始插入设备明细表
                long headCode = head.getCode();

                Set<String> devicePrename = new HashSet<>();
                for (int j = 0; j < mains.size(); j++) {
                    devicePrename.add(mains.get(j).getDeviceName().split("-")[0]);
                }

                for (String deviceName : devicePrename) {
                    AppDeviceStatisticDetail detail = new AppDeviceStatisticDetail();
                    detail.setReportHeadCode(headCode);
                    detail.setDeviceName(deviceName);
                    String sql1 = "select count(*) from device_document_main where (device_name = '" + deviceName + "' or device_name like '" + deviceName + "-%') and status_code = " + 1 + " and dept_code = " + depts.get(i).getCode();
                    String sql2 = "select count(*) from device_document_main where (device_name = '" + deviceName + "' or device_name like '" + deviceName + "-%') and status_code = " + 2 + " and dept_code = " + depts.get(i).getCode();
                    String sql3 = "select count(*) from device_document_main where (device_name = '" + deviceName + "' or device_name like '" + deviceName + "-%') and status_code = " + 3 + " and dept_code = " + depts.get(i).getCode();
                    String sql4 = "select count(*) from device_document_main where (device_name = '" + deviceName + "' or device_name like '" + deviceName + "-%') and status_code = " + 4 + " and dept_code = " + depts.get(i).getCode();
                    detail.setInServiceNum(deviceDocumentMainMapper.countByNameByStatus(sql1));
                    detail.setStandbyNum(deviceDocumentMainMapper.countByNameByStatus(sql2));
                    detail.setReconditionNum(deviceDocumentMainMapper.countByNameByStatus(sql3));
                    detail.setScrapNum(deviceDocumentMainMapper.countByNameByStatus(sql4));
                    detail.setDeviceNum(detail.getInServiceNum() + detail.getStandbyNum() + detail.getReconditionNum() + detail.getScrapNum());
                    appDeviceStatisticDetailMapper.insertSelective(detail);
                }
            }
            logger.info("设备信息统计服务完成");
        }
    }

    /**
     * 每个月的1号生成n条(n=部门数量)的点检统计头表数据
     */
    @Transactional
    @Scheduled(cron = "0 0 0 1 * ?")
    void genertorMonthlySpotcheckHead() {
        if (enableTimeService) {
            logger.info("开始执行任务：每个月1号0点执行 -- 生成n条(n=部门数量)的点检统计头表数据服务");
            BasicInfoDeptExample example = new BasicInfoDeptExample();
            example.createCriteria();
            List<BasicInfoDept> depts = basicInfoDeptMapper.selectByExample(example);
            Date now = new Date();

            for (int i = 0; i < depts.size(); i++) {
                AppSpotcheckStatisticHead head = new AppSpotcheckStatisticHead();
                head.setDeptCode(depts.get(i).getCode());
                head.setDeptName(depts.get(i).getName());
                head.setStatisticalDate(now);
                appSpotcheckStatisticHeadMapper.insertSelective(head);
            }
            logger.info("生成n条(n=部门数量)的点检统计头表数据完成");
        }
    }

    /**
     * 每天统计前一天的各部门的点检信息，将所有前一天的点检信息之中异常的插入到点检统计详情表中
     * 每天0：10
     */
    @Transactional
    @Scheduled(cron = "0 10 0 * * ?")
    void genertorDaylySpotcheckDetail() {
        if (enableTimeService) {
            logger.info("开始执行任务：每天统计前一天的各部门的点检信息");

            BasicInfoDeptExample example = new BasicInfoDeptExample();
            example.createCriteria();
            List<BasicInfoDept> depts = basicInfoDeptMapper.selectByExample(example);

            Date now = new Date();
            long b = 24;
            Date yersToday = new Date(now.getTime() - b * 3600 * 1000);

            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date month = cal.getTime();
            //System.out.println(month.toString());
            for (int i = 0; i < depts.size(); i++) {
                //获取昨天0点到今天0点的时间间隔
                DeviceSpotcheckRecordHeadExample example1 = new DeviceSpotcheckRecordHeadExample();
                example1.createCriteria().andDeptCodeEqualTo(depts.get(i).getCode()).andFinishTimeBetween(yersToday, now);
                List<DeviceSpotcheckRecordHead> heads = deviceSpotcheckRecordHeadMapper.selectByExample(example1);
                // System.out.println(heads.size());
                boolean flag = true;
                int totalCount = 0;
                for (int j = 0; j < heads.size(); j++) {
                    DeviceSpotcheckRecordDetailsExample example2 = new DeviceSpotcheckRecordDetailsExample();
                    example2.createCriteria().andRecordCodeEqualTo(heads.get(j).getCode());
                    List<DeviceSpotcheckRecordDetails> details = deviceSpotcheckRecordDetailsMapper.selectByExample(example2);
                    for (int k = 0; k < details.size(); k++) {
                        if (details.get(k).getMainValues() == 1) {
                            flag = false;
                            totalCount++;
                            break;
                        }
                    }
                }
                if (!flag) {
                    //获取头表的信息
                    AppSpotcheckStatisticHeadExample example2 = new AppSpotcheckStatisticHeadExample();
                    example2.createCriteria().andDeptCodeEqualTo(depts.get(i).getCode()).andStatisticalDateEqualTo(month);
                    AppSpotcheckStatisticHead head = appSpotcheckStatisticHeadMapper.selectByExample(example2).get(0);
                    AppSpotcheckStatisticDetail detail = new AppSpotcheckStatisticDetail();
                    detail.setStatisitcDate(now);
                    detail.setReportHeadCode(head.getCode());
                    detail.setAnomalyNum(totalCount);
                    detail.setSpotcheckNum(heads.size());
                    appSpotcheckStatisticDetailMapper.insertSelective(detail);
                }
            }
            logger.info("每天统计前一天的各部门的点检信息完成");
        }
    }

    @Scheduled(cron = "0 30 0 * * ?")
    void getDateFromRemoteServer() {
        if (enableTimeService) {
            System.out.println("执行任务--从实时数据库读取数据，写入xxx");
        }
    }

    /**
     * 查询“生产管理批次信息表”，查询相同“批次信息batch”的批次信息记录。
     * 若该batch下的所有记录的“状态标志位status_flag”值为1。
     * 则按“批次追溯预览”接口一样，生成一条批次追溯记录，
     * 并同时将该batch下的所有记录的“生成批次完成标记batch_flag”变为1。
     */
    @Scheduled(cron = "0 50 0 * * ?")
    void batchService() {
        if (enableTimeService) {
            System.out.println("执行任务--从实时数据库读取数据，写入xxxxxxxxx");
        }
    }

    /**
     * 定时存储仪器仪表数据服务 – 合成工序
     * 半点，整点执行
     * 秒，分，时，日，月，周几，年（年一般不写）
     */
    @Scheduled(cron = "0 0,30 * ? * ?")
    @Transactional
    void getInstrumentData() {
        if (enableTimeService) {
            logger.info("仪器仪表数据统计开始");
            ProductionInstrumentAddressExample example = new ProductionInstrumentAddressExample();
            example.createCriteria();
            List<ProductionInstrumentAddress> addresses = addressMapper.selectByExample(example);

            for (int i = 0; i < 8; i++) {
                int l = i * 10;
                ProductionInstrumentData data = new ProductionInstrumentData();
                data.setCellNum("70" + (i + 1));
                data.setSampleTime(new Date());
                Float ph, temp, s1, s2, s3, s4, a1, a2, w1, w2;
                ph = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                temp = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s3 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s4 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                a1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                a2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                w1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                w2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                if (null != ph && null != temp && null != s1 && null != s2 &&
                        null != s3 && null != s4 && null != a1 && null != w1 &&
                        null != w1 && null != w2) {
                    data.setPhValue(ph);
                    data.setTemperature(temp);
                    data.setSaltFlow1(s1);
                    data.setSaltFlow2(s2);
                    data.setSaltFlow3(s3);
                    data.setSaltFlow4(s4);
                    data.setAmmoniaBases1(a1);
                    data.setAmmoniaBases2(a2);
                    data.setAmmoniaWater(w1);
                    data.setAmmoniaGas(w2);
                    data.setSolidContainingContent(0f);
                    data.setTransducerShow(0f);
                    data.setMeasured3c(0f);
                    instrumentDataMapper.insertSelective(data);
                }
            }

            for (int i = 8; i < 16; i++) {
                int l = i * 10;
                ProductionInstrumentData data = new ProductionInstrumentData();
                data.setCellNum("80" + (i - 7));
                data.setSampleTime(new Date());
                Float ph, temp, s1, s2, s3, s4, a1, a2, w1, w2;
                ph = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                temp = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s3 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                s4 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                a1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                a2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                w1 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                w2 = RealTimeUtil.timelyHttpMethod(realTimeUrl, addresses.get(l).getAddress());
                l++;
                if (null != ph && null != temp && null != s1 && null != s2 &&
                        null != s3 && null != s4 && null != a1 && null != w1 &&
                        null != w1 && null != w2) {
                    data.setPhValue(ph);
                    data.setTemperature(temp);
                    data.setSaltFlow1(s1);
                    data.setSaltFlow2(s2);
                    data.setSaltFlow3(s3);
                    data.setSaltFlow4(s4);
                    data.setAmmoniaBases1(a1);
                    data.setAmmoniaBases2(a2);
                    data.setAmmoniaWater(w1);
                    data.setAmmoniaGas(w2);
                    data.setSolidContainingContent(0f);
                    data.setTransducerShow(0f);
                    data.setMeasured3c(0f);
                    instrumentDataMapper.insertSelective(data);
                }
            }
            logger.info("仪器仪表数据统计结束");
        }
    }

    /**
     *
     */
    void processCompete() {
        if (enableTimeService) {

        }
    }

    /**
     * 0:20 启动定时生成批次追溯记录服务，
     * 将所有已完成的同一批次(batch 相同)下的批次信息生成一条批次追溯记录
     */
    @Scheduled(cron = "0 20 0 * * ?")
    void generatorBatchRetrospect() {
        if (enableTimeService) {
            logger.info("开始定时生成批次追溯记录服务");
            ProductionBatchInfoExample example = new ProductionBatchInfoExample();
            example.createCriteria();
            List<ProductionBatchInfo> infos = batchInfoMapper.selectByExample(example);
            List<String> batchs = new ArrayList<>();

            for (int i = 0; i < infos.size(); i++) {
                String batch = infos.get(i).getBatch();
                if (!batchs.contains(batch)) {
                    batchs.add(batch);
                }
            }

            for (int i = 0; i < batchs.size(); i++) {
                example.clear();
                example.createCriteria().andBatchEqualTo(batchs.get(i));
                List<ProductionBatchInfo> temps = batchInfoMapper.selectByExample(example);
                boolean flag = true;
                for (int l = 0; l < temps.size(); l++) {
                    if (!temps.get(l).getStatusFlag()) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    logger.info(batchs.get(i) + "--部分未完成");
                } else {
                    logger.info(batchs.get(i) + "--全部完成");
                    batchInfoService.getRecord("系统", batchs.get(i));
                }
            }
            logger.info("生成批次追溯记录服务已完成");
        }
    }
}
