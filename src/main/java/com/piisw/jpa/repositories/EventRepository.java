package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByTimeBetweenAndAnalysisRequired(
            LocalDateTime start,
            LocalDateTime end,
            boolean analysisRequired,
            Pageable pageable
    );

    @Modifying
    @Query("delete from Event e where e.time < :timeBefore")
    void deleteInBulkBeforeDate(
            @Param("timeBefore") LocalDateTime before
    );

    @Modifying
    @Query("UPDATE Event e SET e.analysisRequired = true WHERE TYPE(e) = :clazz AND e.duration > :minDuration")
    void updateInBulkToBeAnalyzedByType(
            @Param("clazz") Class<? extends Event> clazz,
            @Param("minDuration") int minDuration
    );

    @Query("SELECT new com.piisw.jpa.repositories.ServerStatistic(e.server, COUNT(e.server)) "
            + "FROM Event AS e GROUP BY e.server")
    List<ServerStatistic> getServerStatistic();
}
