package de.clubadministation.backend.repository;

import de.clubadministation.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from _group g where lower(g.groupName) like(concat('%', :searchTerm, '%') ) ")
    List<Group> findByGroupName(@Param("searchTerm") String searchTerm);
}
