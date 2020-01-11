package com.jinchi.auth.service;

import java.util.Map;

/**
 * @author：XudongHu
 * @className:CardService
 * @description:
 * @date:23:29 2019/3/18
 * @Modifer:
 */
public interface CardService {
    Map<Object,Object> tokenByCard(String cardNumber);
}
