package kg.megacom.beauty_salon.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalonDto {
    @JsonIgnore
    Long id;
    String name;
    String address;
    boolean active;
    String phoneNumber;
}
