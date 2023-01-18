package kg.megacom.beauty_salon.dao;

import kg.megacom.beauty_salon.models.entities.MasterSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterScheduleRep extends JpaRepository<MasterSchedule,Long> {

}
