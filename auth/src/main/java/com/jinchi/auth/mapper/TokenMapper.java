package com.jinchi.auth.mapper;

import com.jinchi.auth.domain.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author：XudongHu
 * @className:TokenMapper
 * @description:
 * @date:17:07 2019/3/12
 * @Modifer:
 */
@Component
@Mapper
public interface TokenMapper {
    /**
     * 清空token表
     * @return
     */
    Integer tokenRemove();

    Token insert(Token token);

    Token update(Token token);

    void deleteByUserId(@Param("userId") Integer userId);

    Token byToken(Token token);
}
