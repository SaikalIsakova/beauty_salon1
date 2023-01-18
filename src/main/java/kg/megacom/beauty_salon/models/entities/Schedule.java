package kg.megacom.beauty_salon.models.entities;

import kg.megacom.beauty_salon.models.enums.MasterWorkTypeEnum;
import kg.megacom.beauty_salon.models.enums.WorkDayEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
    LocalTime startTime;
    LocalTime endTime;
    @Enumerated(EnumType.STRING)
    WorkDayEnum workDay;
}
