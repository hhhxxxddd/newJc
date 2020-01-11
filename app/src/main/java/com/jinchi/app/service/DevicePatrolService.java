package com.jinchi.app.service;

import com.jinchi.app.domain.BasicInfoLocation;
import com.jinchi.app.dto.IdDto;
import com.jinchi.app.dto.PatrolDTO;
import com.jinchi.app.dto.PatrolLocationDTO;
import com.jinchi.app.dto.PatrolPostDTO;

import java.util.List;

public interface DevicePatrolService {

    List<PatrolDTO> page(PatrolPostDTO patrolPostDTO);

    PatrolDTO detail(Long id);

    void commit(PatrolDTO patrolDTO);

    PatrolLocationDTO getLocationByIdCard(PatrolPostDTO patrolPostDTO);
}
