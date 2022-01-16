package com.canalPlus.meetingPlanner.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reunion")
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String name;
    @Column(name = "type_de_reunion", nullable = false)
    private String meetingType;
    @Column(name = "nombre_de_personne", nullable = false)
    private int number;
    @Column(name = "debut", nullable = false)
    private Time startHour;
    @Column(name = "fin", nullable = false)
    private Time endHour;

    @ManyToMany(
                mappedBy = "meetings",
                cascade = CascadeType.ALL
    )
    List<Room> rooms = new ArrayList<>();


    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meetingType='" + meetingType + '\'' +
                ", number=" + number +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", rooms=" + rooms +
                '}';
    }
}
