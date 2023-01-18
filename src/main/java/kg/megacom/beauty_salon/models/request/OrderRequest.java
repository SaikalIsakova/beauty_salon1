package kg.megacom.beauty_salon.models.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderRequest {
    Long clientId;
    Long masterId;
    Date appointmentDate;
}
