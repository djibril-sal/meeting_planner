package com.canalPlus.meetingPlanner.controller;

import com.canalPlus.meetingPlanner.exception.CapacityException;
import com.canalPlus.meetingPlanner.exception.MeetingNotFoundException;
import com.canalPlus.meetingPlanner.model.Meeting;
import com.canalPlus.meetingPlanner.model.Room;
import com.canalPlus.meetingPlanner.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MeetingController {

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/meetings")
    public List<Meeting> getALLMeeting() {
        return meetingRepository.findAll();
    }

    @GetMapping("/meetings/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) throws MeetingNotFoundException {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException("Reunion de id " +id+ " est introuvable !"));
        return ResponseEntity.ok().body(meeting);
    }

    @PostMapping("/meetings")
    public Meeting createMeeting(@RequestBody Meeting meeting) {

        return meetingRepository.save(meeting);

    }

    @PutMapping("/meetings/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meetingToUpdate) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException("Reunion de id " +id+ " est introuvable !"));
        meeting.setName(meetingToUpdate.getName());
        meeting.setMeetingType(meetingToUpdate.getMeetingType());
        meeting.setNumber(meetingToUpdate.getNumber());
        meeting.setStartHour(meetingToUpdate.getStartHour());
        meeting.setEndHour(meetingToUpdate.getEndHour());
        final Meeting updateMeeting = meetingRepository.save(meeting);
        return ResponseEntity.ok(updateMeeting);

    }

    @DeleteMapping("/meetings/{id}")
    public Map<String, Boolean> deleteMeeting(@PathVariable Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException("Reunion de id " +id+ " est introuvable !"));
        meetingRepository.delete(meeting);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
