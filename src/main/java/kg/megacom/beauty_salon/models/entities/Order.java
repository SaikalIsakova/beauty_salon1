package kg.megacom.beauty_salon.models.entities;

import kg.megacom.beauty_salon.models.entities.Client;
import kg.megacom.beauty_salon.models.entities.Master;
import kg.megacom.beauty_salon.models.enums.OrderStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date addDate;
    Date updateDate;
    Date appointmentDate;
    boolean active;
    int confirmCode;

    @Enumerated(EnumType.STRING)
    OrderStatusEnum status;

    @ManyToOne
    @JoinColumn(name="client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name="master_id")
    Master master;

    @PrePersist
    protected void onCreate() {
        addDate=new Date();
        status = OrderStatusEnum.SUSPEND;
        updateDate = new Date();
        active = true;
    }

    @PreUpdate
    protected void OnUpdate(){
        updateDate=new Date();
    }


}
