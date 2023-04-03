package se.tcmt.sbab.busline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.services.LineService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/lines", produces = "application/json")
public class LineController {

    private final LineService lineService;

    @Autowired
    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping
    public List<Line> getAllLines() {
        return lineService.getAllLines();
    }

    @GetMapping("/{lineNumber}")
    public Optional<Line> getLineByLineNumber(@PathVariable int lineNumber) {
        return lineService.getLineByLineNumber(lineNumber);
    }
}
