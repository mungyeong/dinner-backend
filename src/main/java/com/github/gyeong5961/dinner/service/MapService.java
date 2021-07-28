package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public Map addMap(Map map) {
        return mapRepository.save(map);
    }
}
