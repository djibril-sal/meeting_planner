package com.canalPlus.meetingPlanner.repository;

import com.canalPlus.meetingPlanner.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
