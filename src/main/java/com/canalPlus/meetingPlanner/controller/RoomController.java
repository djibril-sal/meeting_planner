package com.canalPlus.meetingPlanner.controller;

import com.canalPlus.meetingPlanner.exception.MeetingNotFoundException;
import com.canalPlus.meetingPlanner.exception.RoomNotFoundException;
import com.canalPlus.meetingPlanner.model.Meeting;
import com.canalPlus.meetingPlanner.model.Room;
import com.canalPlus.meetingPlanner.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getALLRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) throws RoomNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("La salle de l'id " +id+ " est introuvable !"));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException("La salle de l'id " +id+ " est introuvable !"));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomToUpdate) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException("La salle de l'id " +id+ " est introuvable !"));
        room.setName(roomToUpdate.getName());
        room.setCapacity(roomToUpdate.getCapacity());
        room.setEquipment(roomToUpdate.getEquipment());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);

    }




}
