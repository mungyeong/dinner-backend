package com.github.gyeong5961.dinner.controller;


import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.service.MapService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    private final MapService mapService;

    private final Gson gson;

    public MapController(MapService mapService, Gson gson) {
        this.mapService = mapService;
        this.gson = gson;
    }


    @GetMapping(value = "/{id}")
    public String getMaps(@PathVariable(value = "id") Long id) {
        return gson.toJson(mapService.find(id));
    }

    @GetMapping(path = "/")
    public @ResponseBody String getAllMaps() {
        return gson.toJson(mapService.findAll());
    }

    @PostMapping(path = "/")
    public @ResponseBody String postMaps(@RequestBody String info) {
        Map Maps = gson.fromJson(info, Map.class);
        return gson.toJson(mapService.insert(Maps)) ;
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody String putMaps(@Validated Map Maps, @PathVariable(value = "id") Long id) {
        return gson.toJson(mapService.update(Maps));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMaps(@PathVariable(value = "id") Long id) {
        mapService.delete(id);
    }

    @DeleteMapping(value = "/")
    public void deleteMaps(@RequestBody String list) {
        List<Map> Maps = gson.fromJson(list,new TypeToken<List<Map>>(){}.getType());
        mapService.delete(Maps);
    }
}
