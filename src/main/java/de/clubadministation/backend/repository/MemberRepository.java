package de.clubadministation.backend.repository;

import de.clubadministation.backend.entity.Group;
import de.clubadministation.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m " +
            "where lower(m.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(m.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Member> findByName(@Param("searchTerm") String searchTerm);
}
