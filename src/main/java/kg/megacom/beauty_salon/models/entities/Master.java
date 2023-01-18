package kg.megacom.beauty_salon.models.entities;

import kg.megacom.beauty_salon.models.enums.MasterWorkTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="tb_master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    boolean active;

    @ManyToOne
    @JoinColumn(name="salon_id")
    Salon salon;

    @Enumerated(EnumType.STRING)
    MasterWorkTypeEnum workType;

    @PrePersist
    protected void onCreate() {
        active = true;
    }

    }
