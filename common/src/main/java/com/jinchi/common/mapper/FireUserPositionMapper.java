package com.jinchi.common.mapper;

import com.jinchi.common.domain.FireUserPosition;
import com.jinchi.common.domain.FireUserPositionExample;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FireUserPositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int countByExample(FireUserPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int deleteByExample(FireUserPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    @Delete({
        "delete from fire_user_position",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    @Insert({
        "insert into fire_user_position (uid, pid)",
        "values (#{uid,jdbcType=INTEGER}, #{pid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(FireUserPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int insertSelective(FireUserPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    List<FireUserPosition> selectByExample(FireUserPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, uid, pid",
        "from fire_user_position",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    FireUserPosition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FireUserPosition record, @Param("example") FireUserPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FireUserPosition record, @Param("example") FireUserPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FireUserPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fire_user_position
     *
     * @mbggenerated
     */
    @Update({
        "update fire_user_position",
        "set uid = #{uid,jdbcType=INTEGER},",
          "pid = #{pid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FireUserPosition record);
}