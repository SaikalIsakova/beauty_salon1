package kg.megacom.beauty_salon.models.dto;

import kg.megacom.beauty_salon.models.entities.Client;
import kg.megacom.beauty_salon.models.entities.Master;
import kg.megacom.beauty_salon.models.enums.OrderStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long id;
    Date addDate;
    Date updateDate;
    Date appointmentDate;
    boolean active;
    int confirmCode;
    OrderStatusEnum status;
    ClientDto client;
    MasterDto master;

}
