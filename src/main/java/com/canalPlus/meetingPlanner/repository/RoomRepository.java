package com.canalPlus.meetingPlanner.repository;

import com.canalPlus.meetingPlanner.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
