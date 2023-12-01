package com.example.employe.management.service;

import com.example.employe.management.Repo.NewRepository;
import com.example.employe.management.dto.NewDto;
import com.example.employe.management.exception.HajFoundException;
import com.example.employe.management.exception.NewFoundException;
import com.example.employe.management.model.Haj;
import com.example.employe.management.model.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@Service
public class NewServiceImpl implements NewService {

    private final NewRepository newRepository;

    public NewServiceImpl(NewRepository newRepository) {
        this.newRepository = newRepository;
    }

    public New addNew(New newI) {
        try {
            return newRepository.save(newI);
        } catch (Exception ex) {
            throw new NewFoundException("Haj with the same title already exists");
        }
    }

    @Override
    public List<New> getAllNew() {
        return newRepository.findAll();
    }

    @Override
    public New getByNewId(Long id) {
        return newRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteNew(Long id) {
        newRepository.deleteById(id);
    }

    @Override
    public New updateNew(Long id, NewDto newDto, MultipartFile imageFile) throws IOException {
        New existingNew = newRepository.findById(id).orElse(null);
        if (existingNew != null) {
            existingNew.setNewName(newDto.getNewName());
            existingNew.setNewDescription(newDto.getNewDescription());
            existingNew.setImage(imageFile.getBytes()); // Update the image data
            return newRepository.save(existingNew);
        }
        return null;
    }
}