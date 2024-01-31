package ru.appline.controller;


import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Compas;
import ru.appline.logic.SideOfCompas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class Controller {

    private static Compas compas = null;

    @PostMapping(value = "/createCompas", consumes = "application/json", produces = "application/json")
    public String createCompas(@RequestBody Map<String, String> sideAndDegrees) {

        List<SideOfCompas> listSideOfCompas = new ArrayList<>();

        //проход по мапе, парсинг реквеста(json) и создание экземпляра компаса
        for (Map.Entry<String, String> entry : sideAndDegrees.entrySet()) {
            List<String> degreesOfSide = List.of(entry.getValue().split("-"));
            int start = Integer.parseInt(degreesOfSide.get(0));
            int end = Integer.parseInt(degreesOfSide.get(1));
            SideOfCompas sideOfCompas = new SideOfCompas(entry.getKey(), start, end);
            listSideOfCompas.add(sideOfCompas);
        }

        compas = new Compas(listSideOfCompas);
        return "{\"message\":\"Компас успешно создан!\"}";

    }

    @GetMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
    public String getSide(@RequestBody Map<String, Integer> degree) {

        return "\n\"Side\": " + compas.getSideOfCompas(degree.get("Degree"));

    }


}

