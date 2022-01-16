package com.canalPlus.meetingPlanner.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "salle")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String name;
    @Column(name = "capacite_maximale", nullable = false)
    private int capacity;
    @Column(name = "equipement",nullable = false)
    private String equipment;

    @ManyToMany(
                fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.MERGE,
                        CascadeType.PERSIST
                }
    )
            @JoinTable(
                    name = "salle_reunion",
                    joinColumns = @JoinColumn(name = "room_id"),
                    inverseJoinColumns = @JoinColumn(name = "meeting_id")
            )
    private List<Meeting> meetings = new ArrayList<>();

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", equipment='" + equipment + '\'' +
                ", meetings=" + meetings +
                '}';
    }
}
