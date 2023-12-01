package com.example.employe.management.service;

import com.example.employe.management.Repo.HajRepository;
import com.example.employe.management.dto.HajDto;
import com.example.employe.management.exception.HajFoundException;
import com.example.employe.management.model.Haj;
import com.example.employe.management.model.HajCategorie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HajService {

    @Autowired
    private HajRepository hajRepository;

    public Haj addHaj(Haj haj) {
        try {
            return hajRepository.save(haj);
        } catch (Exception ex) {
            throw new HajFoundException("Haj with the same title already exists");
        }
    }

    public List<Haj> getAllHajByCategory(HajCategorie hajCategorie) {
        return hajRepository.findByHajCategorie(hajCategorie);
    }

    public List<HajDto> getAllHajDtos() {
        List<Haj> hajList = hajRepository.findAll();
        // Convert Haj entities to HajDto objects here
        List<HajDto> hajDtoList = convertHajListToHajDtoList(hajList);
        return hajDtoList;
    }
    public List<HajDto> getAllHajWithShowNowTrue() {
        List<Haj> hajList = hajRepository.findByShowNow(true);

        List<HajDto> hajDtoList=convertHajListToHajDtoList(hajList);
        return hajDtoList;

    }
    public List<Haj> getHajByCategory(HajCategorie category) {
        return hajRepository.findByHajCategorie(category);
    }

    public List<HajDto> getAllHajWithShowNowFalse() {
        boolean showNowValue = true;
        List<Haj> hajList = hajRepository.findByShowNowAndNumbNotNullOrderByNumb(false);

        List<HajDto> hajDtoList = convertHajListToHajDtoList(hajList);
        return hajDtoList;
    }



    public Optional<HajDto> getHajById(Long hajId) {
        Optional<Haj> haj = hajRepository.findByHajId(hajId);
        return haj.map(this::convertHajToHajDto);
    }
    public void deleteHaj(Long hajId) {
        Optional<Haj> haj = hajRepository.findByHajId(hajId);
        if (haj.isPresent()) {
            hajRepository.delete(haj.get());
        } else {
            throw new HajFoundException("Haj with ID " + hajId + " not found");
        }
    }

    public Haj updateHaj(Haj updatedHaj) {
        Optional<Haj> existingHaj = hajRepository.findByHajId(updatedHaj.getHajId());

        if (existingHaj.isPresent()) {
            Haj hajToUpdate = existingHaj.get();

            // Update all properties with values from updatedHaj
            hajToUpdate.setHajName(updatedHaj.getHajName());
            hajToUpdate.setHajDescription(updatedHaj.getHajDescription());
            hajToUpdate.setDepartureAirport(updatedHaj.getDepartureAirport());
            hajToUpdate.setRetrnAirport(updatedHaj.getRetrnAirport());
            hajToUpdate.setGoing(updatedHaj.getGoing());
            hajToUpdate.setReservationNumber(updatedHaj.getReservationNumber());
            hajToUpdate.setNumb(updatedHaj.getNumb());
            hajToUpdate.setPriceVen(updatedHaj.getPriceVen());
            hajToUpdate.setComing(updatedHaj.getComing());
            hajToUpdate.setAirline(updatedHaj.getAirline());
            hajToUpdate.setPrice(updatedHaj.getPrice());
            hajToUpdate.setDistanceMadina(updatedHaj.getDistanceMadina());
            hajToUpdate.setDistanceMakka(updatedHaj.getDistanceMakka());
            hajToUpdate.setNbrDays(updatedHaj.getNbrDays());
            hajToUpdate.setOffre1(updatedHaj.getOffre1());
            hajToUpdate.setOffre2(updatedHaj.getOffre2());
            hajToUpdate.setOffre3(updatedHaj.getOffre3());
            hajToUpdate.setTypeRoom((updatedHaj.getTypeRoom()));
            hajToUpdate.setOffre4(updatedHaj.getOffre4());
            hajToUpdate.setOffre5(updatedHaj.getOffre5());
            hajToUpdate.setOffre6(updatedHaj.getOffre6());
            hajToUpdate.setOffre7(updatedHaj.getOffre7());
            hajToUpdate.setOffre8(updatedHaj.getOffre8());
            hajToUpdate.setOffre9(updatedHaj.getOffre9());
            hajToUpdate.setOffre10(updatedHaj.getOffre10());
            hajToUpdate.setPriceAd(updatedHaj.getPriceAd());
            hajToUpdate.setAirfare(updatedHaj.isAirfare());
            hajToUpdate.setLocalTransportation(updatedHaj.isLocalTransportation());
            hajToUpdate.setTourGuide(updatedHaj.isTourGuide());
            hajToUpdate.setAccommodation(updatedHaj.isAccommodation());
            hajToUpdate.setEntranceFees(updatedHaj.isEntranceFees());
            hajToUpdate.setLunch(updatedHaj.isLunch());
            hajToUpdate.setShowNow(updatedHaj.isShowNow());
            hajToUpdate.setDinner(updatedHaj.isDinner());
            hajToUpdate.setGuideGratuity(updatedHaj.isGuideGratuity());
            hajToUpdate.setHajDescription2(updatedHaj.getHajDescription2());
            hajToUpdate.setHajCategorie(updatedHaj.getHajCategorie());
            // Update the 'image' property if a new image is provided
            if (updatedHaj.getImage() != null) {
                hajToUpdate.setImage(updatedHaj.getImage());
            }

            // Save the updated Haj entity
            return hajRepository.save(hajToUpdate);
        } else {
            throw new HajFoundException("Haj with ID " + updatedHaj.getHajId() + " not found");
        }
    }

    public Optional<Haj> getHajByIdForCart(Long hajId) {
        return hajRepository.findByHajId(hajId);
    }


    private List<HajDto> convertHajListToHajDtoList(List<Haj> hajList) {
        return hajList.stream()
                .map(this::convertHajToHajDto)
                .collect(Collectors.toList());
    }

    public HajDto convertHajToHajDto(Haj haj) {
        HajDto hajDto = new HajDto();
        hajDto.setHajId(haj.getHajId());
        hajDto.setHajName(haj.getHajName());
        hajDto.setHajDescription(haj.getHajDescription());
        hajDto.setDepartureAirport(haj.getDepartureAirport());
        hajDto.setRetrnAirport(haj.getRetrnAirport());
        hajDto.setGoing(haj.getGoing());
        hajDto.setReservationNumber(haj.getReservationNumber());
        hajDto.setNumb(haj.getNumb());
        hajDto.setComing(haj.getComing());
        hajDto.setAirline(haj.getAirline());
        hajDto.setPriceVen(haj.getPriceVen());
        hajDto.setPrice(haj.getPrice());
        hajDto.setOffre1(haj.getOffre1());
        hajDto.setDistanceMadina(haj.getDistanceMadina());
        hajDto.setDistanceMakka(haj.getDistanceMakka());
        hajDto.setOffre2(haj.getOffre2());
        hajDto.setTypeRoom(haj.getTypeRoom());
        hajDto.setOffre3(haj.getOffre3());
        hajDto.setOffre4(haj.getOffre4());
        hajDto.setOffre5(haj.getOffre5());
        hajDto.setOffre6(haj.getOffre6());
        hajDto.setOffre7(haj.getOffre7());
        hajDto.setOffre8(haj.getOffre8());
        hajDto.setOffre9(haj.getOffre9());
        hajDto.setOffre10(haj.getOffre10());
        hajDto.setPriceAd(haj.getPriceAd());
        hajDto.setNbrDays(haj.getNbrDays());
        hajDto.setAirfare(haj.isAirfare());
        hajDto.setLocalTransportation(haj.isLocalTransportation());
        hajDto.setTourGuide(haj.isTourGuide());
        hajDto.setAccommodation(haj.isAccommodation());
        hajDto.setEntranceFees(haj.isEntranceFees());
        hajDto.setLunch(haj.isLunch());
        hajDto.setDinner(haj.isDinner());
        hajDto.setShowNow(haj.isShowNow());
        hajDto.setGuideGratuity(haj.isGuideGratuity());
        hajDto.setHajDescription2(haj.getHajDescription2());
        hajDto.setHajCategorie(haj.getHajCategorie());

        // Convert and set image data
        if (haj.getImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(haj.getImage());
            hajDto.setImageData(base64Image);
        }

        return hajDto;
    }
}
