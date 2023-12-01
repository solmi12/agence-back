package com.example.employe.management.controllers;


import com.example.employe.management.dto.RoomReservationChoiceDto;
import com.example.employe.management.model.RoomReservationChoice;
import com.example.employe.management.service.ReservationService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private HttpServletRequest request;
   // private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @PostMapping("/create")
    public ResponseEntity<List<RoomReservationChoice>> createReservations(
            @RequestBody List<RoomReservationChoiceDto> reservationDtos
    ) {
        try {
            HttpSession session = request.getSession();
            String sessionIdentifier = session.getId();

            // Set the sessionIdentifier in each reservationDto
            reservationDtos.forEach(dto -> dto.setSessionIdentifier(sessionIdentifier));

            // logger.info("Received reservationDtos: {}", reservationDtos);
            // Call the service to create reservations
            List<RoomReservationChoice> createdReservations = reservationService.addReservations(reservationDtos);

            return ResponseEntity.ok(createdReservations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList()); // Return an empty list in case of an error
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteBySessionIdentifier(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String sessionIdentifier = session.getId();
            reservationService.deleteRoomReservationChoiceBySessionIdentifier(sessionIdentifier);

            Map<String, String> response = new HashMap<>();
            response.put("message", "RoomReservationChoice deleted successfully");

            response.put("sessionIdentifier", sessionIdentifier);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error deleting Room Reservation"));
        }
    }


    @GetMapping("/session")
    public List<RoomReservationChoice> getRoomReservationChoicesBySessionIdentifier() {
        HttpSession session = request.getSession();
        String sessionIdentifier = session.getId();

        return reservationService.getRoomReservationChoicesBySessionIdentifier(sessionIdentifier);
    }

}
