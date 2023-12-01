package com.example.employe.management.controllers;
import com.example.employe.management.dto.HajDto;
import com.example.employe.management.exception.HajFoundException;
import com.example.employe.management.model.Faq;
import com.example.employe.management.model.HajCategorie;
import com.example.employe.management.service.ShoppingCartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.employe.management.model.Haj;
import com.example.employe.management.service.HajService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/haj")
public class HajController {

    @Autowired
    private HajService hajService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    private static final Logger logger = LoggerFactory.getLogger(HajController.class);


    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public ResponseEntity<?> addHaj(
            @RequestPart(name = "image", required = true) MultipartFile file,
            @RequestParam(name = "hajName", required = true) String hajName,
            @RequestParam(name = "hajDescription", required = true) String hajDescription,
            @RequestParam(name = "departureAirport", required = true) String departureAirport,
            @RequestParam(name = "retrnAirport", required = true) String retrnAirport,
            @RequestParam(name = "going", required = true) String going,
            @RequestParam(name = "reservationNumber", required = true) Integer reservationNumber,
            @RequestParam(name = "numb", required = true) Integer numb,
            @RequestParam(name = "priceVen", required = true) Integer priceVen,
            @RequestParam(name = "coming", required = true) String coming,
            @RequestParam(name = "airline", required = true) String airline,
            @RequestParam(name = "price", required = true) String price,
            @RequestParam(name = "typeRoom", required = true) String typeRoom,
            @RequestParam(name = "distanceMakka", required = true) String distanceMakka,
            @RequestParam(name = "distanceMadina", required = true) String distanceMadina,
            @RequestParam(name = "priceAd", required = true) Integer priceAd,
            @RequestParam(name = "offre1", required = true) String offre1,
            @RequestParam(name = "offre2", required = true) String offre2,
            @RequestParam(name = "offre3", required = true) String offre3,
            @RequestParam(name = "offre4", required = true) String offre4,
            @RequestParam(name = "offre5", required = true) String offre5,
            @RequestParam(name = "offre6", required = true) String offre6,
            @RequestParam(name = "offre7", required = true) String offre7,
            @RequestParam(name = "offre8", required = true) String offre8,
            @RequestParam(name = "offre9", required = true) String offre9,
            @RequestParam(name = "offre10", required = true) String offre10,
            @RequestParam(name = "nbrDays", required = true) String nbrDays,
            @RequestParam(name = "airfare", required = true) boolean airfare,
            @RequestParam(name = "localTransportation", required = true) boolean localTransportation,
            @RequestParam(name = "tourGuide", required = true) boolean tourGuide,
            @RequestParam(name = "accommodation", required = true) boolean accommodation,
            @RequestParam(name = "entranceFees", required = true) boolean entranceFees,
            @RequestParam(name = "lunch", required = true) boolean lunch,
            @RequestParam(name = "dinner", required = true) boolean dinner,
            @RequestParam(name = "showNow", required = true) boolean showNow,
            @RequestParam(name = "guideGratuity", required = true) boolean guideGratuity,
            @RequestParam(name = "hajDescription2", required = true) String hajDescription2,
            @RequestParam(name = "hajCategorie", required = true) HajCategorie hajCategorie
    ) {
        try {
            // Create a Haj entity and populate its properties
            Haj newHaj = new Haj();
            newHaj.setHajName(hajName);
            newHaj.setHajDescription(hajDescription);
            newHaj.setDepartureAirport(departureAirport);
            newHaj.setRetrnAirport(retrnAirport);
            newHaj.setGoing(going);
            newHaj.setPriceVen(priceVen);
            newHaj.setReservationNumber(reservationNumber);
            newHaj.setNumb(numb);
            newHaj.setComing(coming);
            newHaj.setAirline(airline);
            newHaj.setDistanceMakka(distanceMakka);
            newHaj.setDistanceMadina(distanceMadina);
            newHaj.setPrice(price);

            newHaj.setNbrDays(nbrDays);
            newHaj.setOffre1(offre1);
            newHaj.setOffre2(offre2);
            newHaj.setOffre3(offre3);
            newHaj.setOffre4(offre4);
            newHaj.setOffre5(offre5);
            newHaj.setOffre6(offre6);
            newHaj.setOffre7(offre7);
            newHaj.setOffre8(offre8);
            newHaj.setOffre9(offre9);
            newHaj.setOffre10(offre10);
            newHaj.setPriceAd(priceAd);
            newHaj.setAirfare(airfare);
            newHaj.setLocalTransportation(localTransportation);
            newHaj.setTourGuide(tourGuide);
            newHaj.setAccommodation(accommodation);
            newHaj.setEntranceFees(entranceFees);
            newHaj.setLunch(lunch);
            newHaj.setDinner(dinner);
            newHaj.setShowNow(showNow);
            newHaj.setTypeRoom(typeRoom);
            newHaj.setGuideGratuity(guideGratuity);
            newHaj.setHajDescription2(hajDescription2);
            newHaj.setHajCategorie(hajCategorie);
            // Set the 'image' property from the uploaded file
            newHaj.setImage(file.getBytes());

            // Call your service method to save the Haj entity with the image
            newHaj = hajService.addHaj(newHaj);

            return new ResponseEntity<>(newHaj, HttpStatus.CREATED);
        } catch (HajFoundException ex) {
            return new ResponseEntity<>("Haj with the same title already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while adding Haj", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<HajDto> getAllHaj() {
        return hajService.getAllHajDtos();
    }
    @GetMapping("/{hajId}")
    public ResponseEntity<?> getHajById(@PathVariable Long hajId) {
        Optional<HajDto> haj = hajService.getHajById(hajId);

        if (haj.isPresent()) {
            return new ResponseEntity<>(haj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Haj with ID " + hajId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{hajId}")
    public ResponseEntity<?> updateHaj(
            @PathVariable Long hajId,
            @RequestPart(name = "image", required = true) MultipartFile file,
            @RequestParam(name = "hajName", required = true) String hajName,
            @RequestParam(name = "hajDescription", required = true) String hajDescription,
            @RequestParam(name = "departureAirport", required = true) String departureAirport,
            @RequestParam(name = "retrnAirport", required = true) String retrnAirport,
            @RequestParam(name = "going", required = true) String going,
            @RequestParam(name = "reservationNumber", required = true) Integer reservationNumber,
            @RequestParam(name = "numb", required = true) Integer numb,
            @RequestParam(name = "priceVen", required = true) Integer priceVen,
            @RequestParam(name = "coming", required = true) String coming,
            @RequestParam(name = "airline", required = true) String airline,
            @RequestParam(name = "typeRoom", required = true) String typeRoom,
            @RequestParam(name = "price", required = true) String price,
            @RequestParam(name = "distanceMakka", required = true) String distanceMakka,
            @RequestParam(name = "distanceMadina", required = true) String distanceMadina,
            @RequestParam(name = "showNow", required = true) boolean showNow,
            @RequestParam(name = "priceAd", required = true) Integer priceAd,
            @RequestParam(name = "offre1", required = true) String offre1,
            @RequestParam(name = "offre2", required = true) String offre2,
            @RequestParam(name = "offre3", required = true) String offre3,
            @RequestParam(name = "offre4", required = true) String offre4,
            @RequestParam(name = "offre5", required = true) String offre5,
            @RequestParam(name = "offre6", required = true) String offre6,
            @RequestParam(name = "offre7", required = true) String offre7,
            @RequestParam(name = "offre8", required = true) String offre8,
            @RequestParam(name = "offre9", required = true) String offre9,
            @RequestParam(name = "offre10", required = true) String offre10,
            @RequestParam(name = "nbrDays", required = true) String nbrDays,
            @RequestParam(name = "airfare", required = true) boolean airfare,
            @RequestParam(name = "localTransportation", required = true) boolean localTransportation,
            @RequestParam(name = "tourGuide", required = true) boolean tourGuide,
            @RequestParam(name = "accommodation", required = true) boolean accommodation,
            @RequestParam(name = "entranceFees", required = true) boolean entranceFees,
            @RequestParam(name = "lunch", required = true) boolean lunch,
            @RequestParam(name = "dinner", required = true) boolean dinner,
            @RequestParam(name = "guideGratuity", required = true) boolean guideGratuity,
            @RequestParam(name = "hajDescription2", required = true) String hajDescription2,
            @RequestParam(name = "hajCategorie", required = true) HajCategorie hajCategorie
    ) {
        try {
            // Create a Haj entity and populate its properties
            Haj updatedHaj = new Haj();
            updatedHaj.setHajId(hajId);
            updatedHaj.setHajName(hajName);
            updatedHaj.setHajDescription(hajDescription);
            updatedHaj.setDepartureAirport(departureAirport);
            updatedHaj.setRetrnAirport(retrnAirport);
            updatedHaj.setGoing(going);
            updatedHaj.setReservationNumber(reservationNumber);
            updatedHaj.setNumb(numb);
            updatedHaj.setComing(coming);
            updatedHaj.setAirline(airline);
            updatedHaj.setPrice(price);
            updatedHaj.setPriceVen(priceVen);
            updatedHaj.setNbrDays(nbrDays);
            updatedHaj.setOffre1(offre1);
            updatedHaj.setOffre2(offre2);
            updatedHaj.setShowNow(showNow);
            updatedHaj.setDistanceMadina(distanceMadina);
            updatedHaj.setDistanceMakka(distanceMakka);
            updatedHaj.setOffre3(offre3);
            updatedHaj.setOffre4(offre4);
            updatedHaj.setOffre5(offre5);
            updatedHaj.setOffre6(offre6);
            updatedHaj.setOffre7(offre7);
            updatedHaj.setOffre8(offre8);
            updatedHaj.setOffre9(offre9);
            updatedHaj.setOffre10(offre10);
            updatedHaj.setPriceAd(priceAd);
            updatedHaj.setAirfare(airfare);
            updatedHaj.setLocalTransportation(localTransportation);
            updatedHaj.setTourGuide(tourGuide);
            updatedHaj.setAccommodation(accommodation);
            updatedHaj.setEntranceFees(entranceFees);
            updatedHaj.setLunch(lunch);
            updatedHaj.setTypeRoom(typeRoom);
            updatedHaj.setDinner(dinner);
            updatedHaj.setGuideGratuity(guideGratuity);
            updatedHaj.setHajDescription2(hajDescription2);
            updatedHaj.setHajCategorie(hajCategorie);
            // Set the 'image' property from the uploaded file
            updatedHaj.setImage(file.getBytes());

            // Call your service method to update the Haj entity with the image
            Haj updatedHajResult = hajService.updateHaj(updatedHaj);

            return new ResponseEntity<>(updatedHajResult, HttpStatus.OK);
        } catch (HajFoundException ex) {
            return new ResponseEntity<>("Haj with ID " + hajId + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while updating Haj", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/{hajId}")
    public ResponseEntity<?> deleteHaj(@PathVariable Long hajId) {
        logger.info("Received request to delete Haj with ID: {}", hajId);
        try {
            // Delete associated cart_items first
            shoppingCartService.deleteCartItemsByHajId(hajId);

            // Then delete the haj record
            hajService.deleteHaj(hajId);

            return new ResponseEntity<>("Haj with ID " + hajId + " and associated cart_items has been deleted", HttpStatus.OK);
        } catch (HajFoundException ex) {
            return new ResponseEntity<>("Haj with ID " + hajId + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("An error occurred while deleting Haj", ex);
            return new ResponseEntity<>("An error occurred while deleting Haj", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<HajDto>> getHajByCategory(@PathVariable HajCategorie category) {
        List<Haj> hajs = hajService.getHajByCategory(category);
        List<HajDto> hajDtos = hajs.stream().map(hajService::convertHajToHajDto).collect(Collectors.toList());

        return new ResponseEntity<>(hajDtos, HttpStatus.OK);
    }



    @GetMapping("/showNowTrue")
    public List<HajDto> getHajWithShowNowTrue() {
        return hajService.getAllHajWithShowNowTrue();
    }

    @GetMapping("/showNowFalse")
    public List<HajDto> getHajWithShowNowFalse() {
        return hajService.getAllHajWithShowNowFalse();
    }

}
