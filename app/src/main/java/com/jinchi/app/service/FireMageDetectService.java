package com.jinchi.app.service;

import com.jinchi.app.dto.Page;
import com.jinchi.app.dto.QueryDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface FireMageDetectService {

    Page page(QueryDTO queryDTO);

    Map detail(Long id);
}
