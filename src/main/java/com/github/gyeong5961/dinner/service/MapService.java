package com.github.gyeong5961.dinner.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.repository.MapRepository;
import com.github.gyeong5961.dinner.vo.SearchMap;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    private final MapRepository mapRepository;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public Map addMap(Map map) {
        return mapRepository.save(map);
    }
}
