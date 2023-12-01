package com.example.employe.management.service;


import com.example.employe.management.Repo.HotelsRepository;
import com.example.employe.management.dto.HoteldDto;
import com.example.employe.management.dto.ReservationOptionDto;
import com.example.employe.management.dto.RoomTypeDto;
import com.example.employe.management.model.Hotels;
import com.example.employe.management.model.ReservationOption;
import com.example.employe.management.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelsService {

    private final HotelsRepository hotelsRepository;

    @Autowired
    public HotelsService(HotelsRepository hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    public List<Hotels> getAllHotels() {
        return hotelsRepository.findAll();
    }

    public Optional<Hotels> getHotelById(Long hotelId) {
        return hotelsRepository.findById(hotelId);
    }

    public boolean doesHotelExist(String hotelName) {
        return hotelsRepository.existsByHotelName(hotelName);
    }

    public Hotels addHotel(HoteldDto hotelDto) {
        Hotels hotel = new Hotels();
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setAdresse(hotelDto.getAdresse());
        hotel.setNumberOfStars((hotelDto.getNumberOfStars()));
        hotel.setLocation(hotelDto.getLocation());
        hotel.setPriceAb(hotelDto.getPriceAb());
        hotel.setImage6(decodeBase64Image(hotelDto.getImage6()));
        hotel.setImage8(decodeBase64Image(hotelDto.getImage8()));
        hotel.setImage10(decodeBase64Image(hotelDto.getImage10()));
        hotel.setImage9(decodeBase64Image(hotelDto.getImage9()));
        hotel.setImage7(decodeBase64Image(hotelDto.getImage7()));
        hotel.setImage5(decodeBase64Image(hotelDto.getImage5()));
        hotel.setImage1(decodeBase64Image(hotelDto.getImage1()));
        hotel.setImage2(decodeBase64Image(hotelDto.getImage2()));
        hotel.setImage3(decodeBase64Image(hotelDto.getImage3()));
        hotel.setImage4(decodeBase64Image(hotelDto.getImage4()));
        List<RoomType> roomTypes = hotelDto.getRoomTypes().stream()
                .map(this::convertToRoomType)
                .collect(Collectors.toList());
        roomTypes.forEach(roomType -> roomType.setHotel(hotel));
        hotel.setRoomTypes(roomTypes);

        List<ReservationOption> reservationOptions = hotelDto.getReservationOptions().stream()
                .map(this::convertToReservationOption)
                .collect(Collectors.toList());
        reservationOptions.forEach(option -> option.setHotel(hotel));
        hotel.setReservationOptions(reservationOptions);

        return hotelsRepository.save(hotel);
    }

    private RoomType convertToRoomType(RoomTypeDto dto) {
        RoomType roomType = new RoomType();
        roomType.setTypeRoom(dto.getTypeRoom());
        roomType.setMaxOccupancy(dto.getMaxOccupancy());
        roomType.setPriceChild(dto.getPriceChild());
        roomType.setPriceAdulte(dto.getPriceAdulte());
        roomType.setPriceChildB(dto.getPriceChildB());
        roomType.setViewOfMakkah(dto.getViewOfMakkah());
        return roomType;
    }

    private ReservationOption convertToReservationOption(ReservationOptionDto dto) {
        ReservationOption option = new ReservationOption();
        option.setOptionName(dto.getOptionName());
        option.setPrice(dto.getPrice());
        return option;
    }

    public Hotels updateHotel(Long hotelId, Hotels updatedHotel) {
        if (hotelsRepository.existsById(hotelId)) {
            updatedHotel.setHotelId(hotelId); // Set the ID of the updatedHotel
            return hotelsRepository.save(updatedHotel);
        } else {
            throw new IllegalArgumentException("Hotel with ID " + hotelId + " not found.");
        }
    }

    private byte[] decodeBase64Image(String base64Image) {
        if (base64Image != null) {
            return Base64.getDecoder().decode(base64Image);
        }
        return null;
    }

    public void deleteHotel(Long hotelId) {
        if (hotelsRepository.existsById(hotelId)) {
            hotelsRepository.deleteById(hotelId);
        } else {
            throw new IllegalArgumentException("Hotel with ID " + hotelId + " not found.");
        }
    }
}