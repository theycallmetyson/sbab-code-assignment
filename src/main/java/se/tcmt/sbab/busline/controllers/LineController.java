package se.tcmt.sbab.busline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.tcmt.sbab.busline.models.JourneyPatternPointOnLine;
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.models.StopPoint;
import se.tcmt.sbab.busline.services.LineService;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/lines")
    public Collection<Line> getAllLines(@RequestParam Optional<Integer> topRanks) throws IOException {
        return lineService.getAllBusLines(topRanks);
    }

    @GetMapping("/stops")
    public Collection<StopPoint> getAllStops() throws IOException {
        return lineService.getAllBusStops();
    }

    @GetMapping("/journeypatterns")
    public Collection<JourneyPatternPointOnLine> getAllJourneyPatterns() throws IOException {
        return lineService.getAllBusJourneyPatterns();
    }
}
