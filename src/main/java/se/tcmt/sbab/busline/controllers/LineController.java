package se.tcmt.sbab.busline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.services.LineService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/lines", produces = "application/json")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping
    public List<Line> getAllLines() {
        List<Line> lines = lineService.getAllLines();
        return lines;
    }
}
