package kg.megacom.beauty_salon.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.megacom.beauty_salon.models.enums.MasterWorkTypeEnum;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ScheduleDto {
    @JsonIgnore
    Long id;
    @Temporal(value = TemporalType.TIME)
    LocalTime startTime;
    @Temporal(value = TemporalType.TIME)
    LocalTime endTime;
    WorkDayEnum workDay;

    public ScheduleDto(LocalTime startTime, LocalTime endTime, WorkDayEnum workDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workDay = workDay;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", workDay=" + workDay +
                '}';
    }
}
