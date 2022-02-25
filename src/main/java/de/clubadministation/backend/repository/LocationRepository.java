package de.clubadministation.backend.repository;

import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l where lower(l.city) like(concat('%', :searchTerm, '%') ) ")
    List<Location> findByCityName(@Param("searchTerm") String searchTerm);
}
