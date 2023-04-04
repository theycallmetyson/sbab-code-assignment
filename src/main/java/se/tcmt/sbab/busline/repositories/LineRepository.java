package se.tcmt.sbab.busline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.tcmt.sbab.busline.models.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, String> {
}
