package kg.megacom.beauty_salon.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MasterScheduleDto {
    Long id;
    MasterDto master;
    ScheduleDto schedule;


    public MasterScheduleDto(MasterDto master, ScheduleDto schedule) {
        this.master = master;
        this.schedule=schedule;

    }

}
