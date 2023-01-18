package kg.megacom.beauty_salon.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    boolean active;

    @Column(unique = true)
    String phoneNumber;

    @Column(unique = true)
    String email;

    @PrePersist
    protected void onCreate() {
        active = true;
    }

}
