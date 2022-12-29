package com.luan.saladereuniao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.saladereuniao.models.Room;
import com.luan.saladereuniao.repositories.RoomRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/rooms")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;

	@GetMapping
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoomId(@PathVariable Long id) throws ResourceNotFoundException {
		Room room = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping
	public Room createRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room room)
			throws ResourceNotFoundException {
		Room roomOptional = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
		roomOptional.setId(id);
		roomOptional.setName(room.getName());
		roomOptional.setDate(room.getDate());
		roomOptional.setStartHour(room.getStartHour());
		roomOptional.setEndHour(room.getEndHour());
		final Room updateRoom = roomRepository.save(roomOptional);
		return ResponseEntity.ok(updateRoom);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Room> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException {
		Room room = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found: " + id));
		roomRepository.delete(room);
		return ResponseEntity.noContent().build();
	}
}
