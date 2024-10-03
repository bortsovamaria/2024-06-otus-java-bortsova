package ru.otus.crm.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    private String phone;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Phone(Long id, String phone){
        this.id = id;
        this.phone = phone;
    }

}
