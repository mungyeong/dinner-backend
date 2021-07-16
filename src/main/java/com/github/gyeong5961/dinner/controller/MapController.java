package com.github.gyeong5961.dinner.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.service.MapService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/map")
public class MapController {

    private final MapService mapService;

    private final ObjectMapper mapper;

    public MapController(MapService mapService, ObjectMapper mapper) {
        this.mapService = mapService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public Map getMaps(@PathVariable Long id){
        return mapService.find(id);
    }

    @GetMapping(path = "")
    public List<Map> getAllMaps() {
        return mapService.findAll();
    }

    @PostMapping(path = "")
    public Map postMaps(@RequestBody String info) throws JsonProcessingException {
        Map map = mapper.readValue(info, Map.class);
        return mapService.insert(map);
    }

    @PutMapping(value = "")
    public Map putMaps(@RequestBody String info) throws JsonProcessingException {
        Map map = mapper.readValue(info, Map.class);
        return mapService.update(map);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMaps(@PathVariable Long id) {
        mapService.delete(id);
    }

    @DeleteMapping(value = "")
    public void deleteMaps(@RequestBody String list) throws JsonProcessingException {
        List<Map> Maps = mapper.readValue(list, new TypeReference<List<Map>>() {
        });
        mapService.delete(Maps);
    }
}
