package com.luan.saladereuniao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luan.saladereuniao.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
