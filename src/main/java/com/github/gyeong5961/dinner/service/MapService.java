package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.repository.MapRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapRepository mapRepository;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<Map> findAll() {
        return mapRepository.findAll();
    }


    public Map insert(Map map) {
        return mapRepository.saveAndFlush(map);
    }

    public Map update(Map map) {
        return mapRepository.saveAndFlush(map);
    }

    public Map find(Long id) {
        return mapRepository.findById(id).orElse(new Map());
    }

    public void delete(Long id) {
        Map Maps = mapRepository.findById(id).orElse(new Map());
        mapRepository.save(Maps);
    }

    public void delete(List<Map> Maps) {
        mapRepository.saveAll(Maps);
    }

}
