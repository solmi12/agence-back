package com.example.employe.management.service;


import com.example.employe.management.Repo.ReservationOptionRepository;
import com.example.employe.management.Repo.ReservationRepository;
import com.example.employe.management.Repo.RoomTypeRepository;
import com.example.employe.management.dto.ReservationOptionDto;
import com.example.employe.management.dto.RoomReservationChoiceDto;
import com.example.employe.management.dto.RoomTypeDto;
import com.example.employe.management.model.ReservationOption;
import com.example.employe.management.model.RoomReservationChoice;
import com.example.employe.management.model.RoomType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationOptionRepository reservationOptionRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationOptionRepository reservationOptionRepository, RoomTypeRepository roomTypeRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationOptionRepository = reservationOptionRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomReservationChoice> addReservations(List<RoomReservationChoiceDto> reservationDtos) {
        return reservationDtos.stream()
                .map(dto -> convertDtoToEntities(dto))
                .flatMap(List::stream) // Flatten the list of RoomReservationChoices
                .map(reservationRepository::save)
                .collect(Collectors.toList());
    }

    private List<RoomReservationChoice> convertDtoToEntities(RoomReservationChoiceDto reservationDto) {
        List<RoomReservationChoice> reservations = new ArrayList<>();

        for (RoomTypeDto roomTypeDto : reservationDto.getRoomTypes()) {
            for (ReservationOptionDto reservationOptionDto : reservationDto.getReservationOptions()) {
                RoomReservationChoice reservation = new RoomReservationChoice();
                reservation.setNumberOfAdults(reservationDto.getNumberOfAdults());
                reservation.setNumberOfChildren(reservationDto.getNumberOfChildren());
                reservation.setNumberOfB(reservationDto.getNumberOfB());
                reservation.setViewOfMakkah(reservationDto.isViewOfMakkah());
                reservation.setNumbnuit(reservationDto.getNumbnuit());
                reservation.setDateRes(reservationDto.getDateRes());
                reservation.setHotelNamee(reservationDto.getHotelNamee());
                // Create and set the selected RoomType
                RoomType roomType = new RoomType();
                roomType.setId(roomTypeDto.getId()); // Set the ID

                // Populate roomType properties using its ID from a database query
                roomType = roomTypeRepository.findById(roomType.getId()).orElse(null); // Assuming you have a roomTypeRepository

                if (roomType != null) {
                    reservation.getRoomTypes().add(roomType);
                } else {
                    // Handle the case where roomType with the specified ID is not found
                    // You can log an error or handle it as appropriate for your use case
                }

                // Create and set the selected ReservationOption
                ReservationOption reservationOption = new ReservationOption();
                reservationOption.setId(reservationOptionDto.getId()); // Set the ID

                // Populate reservationOption properties using its ID from a database query
                reservationOption = reservationOptionRepository.findById(reservationOption.getId()).orElse(null); // Assuming you have a reservationOptionRepository

                if (reservationOption != null) {
                    reservation.getReservationOptions().add(reservationOption);
                } else {
                    // Handle the case where reservationOption with the specified ID is not found
                    // You can log an error or handle it as appropriate for your use case
                }

                reservation.setSessionIdentifier(reservationDto.getSessionIdentifier());

                double totalPriceRes = calculateTotalPrice(reservationDto);
                reservation.setTotalpriceRes(totalPriceRes);
                reservations.add(reservation);
            }
        }
        return reservations;
    }


    private double calculateTotalPrice(RoomReservationChoiceDto reservationDto) {
        if (reservationDto.getRoomTypes().isEmpty() || reservationDto.getReservationOptions().isEmpty()) {
            return 0.0; // Return 0 if lists are empty to avoid NullPointerException
        }

        RoomTypeDto roomTypeDto = reservationDto.getRoomTypes().get(0);
        ReservationOptionDto reservationOptionDto = reservationDto.getReservationOptions().get(0);

        // Retrieve RoomType entity from the database using the ID
        RoomType roomType = roomTypeRepository.findById(roomTypeDto.getId()).orElse(null);

        if (roomType == null) {
            // Handle the case where roomType with the specified ID is not found
            // You can log an error or handle it as appropriate for your use case
            return 0.0;
        }

        // Retrieve ReservationOption entity from the database using the ID
        ReservationOption reservationOption = reservationOptionRepository.findById(reservationOptionDto.getId()).orElse(null);

        if (reservationOption == null) {
            // Handle the case where reservationOption with the specified ID is not found
            // You can log an error or handle it as appropriate for your use case
            return 0.0;
        }

        double viewOfMakkah = 0.0; // Initialize it as 0 by default

        if (reservationDto.isViewOfMakkah()) {
            // If isViewOfMakkah is true, use the actual value from RoomType
            viewOfMakkah = roomType.getViewOfMakkah() != null ? roomType.getViewOfMakkah() : 0.0;
        }

        // Add print statements to see the values
        System.out.println("numberOfAdults: " + reservationDto.getNumberOfAdults());
        System.out.println("numberOfChildren: " + reservationDto.getNumberOfChildren());
        System.out.println("numberOfB: " + reservationDto.getNumberOfB());
        System.out.println("isViewOfMakkah: " + reservationDto.isViewOfMakkah());
        System.out.println("viewOfMakkah: " + viewOfMakkah);
        System. out. println("Price Adulte: " + roomType.getPriceAdulte());
        System. out. println("Price Child: " + roomType.getPriceChild());
        System. out. println("Price ChildB: " + roomType.getPriceChildB());
        System. out. println("Reservation Option Price: " + reservationOption.getPrice());
        System. out. println("Numbnuit: " + reservationDto.getNumbnuit());

        double totalPriceRes = (
                (reservationDto.getNumberOfAdults() * roomType.getPriceAdulte()) +
                        (reservationDto.getNumberOfChildren() * roomType.getPriceChild()) +
                        (reservationDto.getNumberOfB() * roomType.getPriceChildB()) +
                        viewOfMakkah + // Add the calculated viewOfMakkah value
                        (reservationOption.getPrice() *
                                (reservationDto.getNumberOfB() + reservationDto.getNumberOfAdults() + reservationDto.getNumberOfChildren()))
        ) * reservationDto.getNumbnuit();

        return totalPriceRes;
    }


    public List<RoomReservationChoice> getAllRoomReservationChoices() {
        return reservationRepository.findAll();
    }

    public RoomReservationChoice getRoomReservationChoiceById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<RoomReservationChoice> getRoomReservationChoicesBySessionIdentifier(String sessionIdentifier) {
        return reservationRepository.findBySessionIdentifier(sessionIdentifier);
    }
    @Transactional
    public void deleteRoomReservationChoiceBySessionIdentifier(String sessionIdentifier) {
        // Use the custom delete method by sessionIdentifier
        reservationRepository.deleteBySessionIdentifier(sessionIdentifier);
    }
}

