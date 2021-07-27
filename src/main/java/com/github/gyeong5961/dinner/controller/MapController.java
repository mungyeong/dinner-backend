package com.github.gyeong5961.dinner.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.service.MapService;
import com.github.gyeong5961.dinner.vo.SearchMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.ValueHandler;
import java.util.List;

@RestController
@RequestMapping(value = "/api/map")
public class MapController {

    private final MapService mapService;

    private final ObjectMapper mapper;


    public MapController(MapService mapService, ObjectMapper mapper) {
        this.mapService = mapService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public Map addMap(@RequestBody SearchMap searchMap) throws JsonProcessingException {
        Map map = mapper.readValue(searchMap.toString(), Map.class);
        return mapService.addMap(map);
    }
}
