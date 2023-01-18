package kg.megacom.beauty_salon.dao;

import kg.megacom.beauty_salon.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRep extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s inner join MasterSchedule m on s.id=m.schedule.id where m.master.id=:id")
    List<Schedule> findScheduleByMasterId(Long id);


}
