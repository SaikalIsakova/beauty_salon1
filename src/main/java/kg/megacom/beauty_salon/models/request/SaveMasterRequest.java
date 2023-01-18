package kg.megacom.beauty_salon.models.request;

import kg.megacom.beauty_salon.models.enums.MasterWorkTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveMasterRequest {
    String name;
    String surname;
    Long saloonId;
    MasterWorkTypeEnum workType;
}
