package com.jinchi.common.mapper;

import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author：XudongHu
 * @description: 公共批号mapper
 * @date:21:26 2018/11/19
 * <p>
 * 此处有许多根据id和类型查询,事实上id就为唯一键
 * 考虑到所有的批号都在一张表,如果只用id查询,万一传入的id为其他表中的数据,会造成错误
 * 故查询的时候最好传入对应的类型
 */
@Mapper
@Component
public interface CommonBatchNumberMapper {

    /**
     * 新增
     * <p>
     * 只有description
     * memo可以为空
     */
    int insert(CommonBatchNumber commonBatchNumber);

    /**
     * 更新
     * <p>
     * 多条件更新
     * String batchNumber;批号
     * Date createTime;创建时间
     * Integer createPersonId;创建人
     * Integer status;状态
     * Integer dataType;数据类型
     * Integer isUrgent;是否紧急
     * String description;描述
     * String memo;制程检测备注
     */
    void update(CommonBatchNumber commonBatchNumber);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据ids和数据类型批量删除
     *
     * @param ids
     * @return
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    CommonBatchNumber byId(@Param("id") Integer id);


    /**
     * 多条件查询
     *
     * @return 批号集合
     */
    List<CommonBatchNumber> findByCommon(CommonBatchNumber commonBatchNumber);


    /**
     * 根据批号全名查询
     *
     * @param batchNumber 批号全名
     * @return
     */
    CommonBatchNumber byBatchNumber(@Param("batchNumber") String batchNumber);


    /**
     * 以下为直接查出DTO===============================================================================
     */

    /**
     * 根据id和数据类型查询并组成DTO
     *
     * @param id
     * @param dataType NN
     * @return
     */
    CommonBatchNumberDTO createDTOByIdAndDataType(@Param("id") Integer id, @Param("dataType") Integer dataType);

    /**
     * 根据ids和创建人名称模糊查询
     */
    List<CommonBatchNumberDTO> createDTOByIdsAndPersonName(@Param("ids") List<Integer> ids,@Param("personName") String personName);

    /**
     * 根据id和时间查询
     *
     * @param id   TODO 有缺陷
     * @param date
     * @return
     */
    CommonBatchNumberDTO createDTOByIdAndCreateTime(@Param("id") Integer id, @Param("date") String date);

    /**
     * 多条件查询DTO
     * 使用dto中的任意属性查询,包括创建人名称
     *
     * @param dto NN
     * @return
     */
    List<CommonBatchNumberDTO> createDTOsByCommonDTO(@Param("dto") CommonBatchNumberDTO dto);

    /**
     * 多条件查询DTO
     * 分页
     */
    List<CommonBatchNumberDTO> createDTOsByCommonDTObyPage(@Param("dto") CommonBatchNumberDTO dto, @Param("pageBean") PageBean pageBean);

    /**
     * 计算总数
     *
     * @param dto
     * @return
     */
    Integer countSum(@Param("dto") CommonBatchNumberDTO dto);

    /**
     * 多条件查询批号
     * @param dataType 批号类型
     * @param createPersonId 创建人id
     * @param passTime 通过时间
     * @return
     */
    List<CommonBatchNumber> delivererTask(@Param("dataType") Integer dataType,
                                          @Param("createPersonId") Integer createPersonId,
                                          @Param("passTime") Date passTime);
}
