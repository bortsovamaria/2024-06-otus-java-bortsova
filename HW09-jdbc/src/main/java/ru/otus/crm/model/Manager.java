package ru.otus.crm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.otus.crm.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Manager {

    @Id
    private Long no;
    private String label;
    private String param1;

    public Manager(String label, String param1) {
        this.label = label;
        this.param1 = param1;
    }

    public Manager(Long no, String label, String param1) {
        this.no = no;
        this.label = label;
        this.param1 = param1;
    }
}
