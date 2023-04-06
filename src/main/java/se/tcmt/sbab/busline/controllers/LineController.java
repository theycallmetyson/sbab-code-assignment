package se.tcmt.sbab.busline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.tcmt.sbab.busline.services.LineService;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/lines")
    public Map<String, Collection<?>> getAllLines(@RequestParam(name = "top") Optional<Integer> topRanks) throws IOException {
        return lineService.getAllBusLines(topRanks);
    }
}
