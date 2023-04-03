package se.tcmt.sbab.busline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tcmt.sbab.busline.models.Line;
import se.tcmt.sbab.busline.repositories.LineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LineService {

    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    public Optional<Line> getLineByLineNumber(int lineNumber) {
        return lineRepository.findLineByLineNumber(lineNumber);
    }
}
