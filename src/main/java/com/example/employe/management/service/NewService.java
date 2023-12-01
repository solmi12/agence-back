package com.example.employe.management.service;

import com.example.employe.management.dto.NewDto;
import com.example.employe.management.model.New;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NewService {
    New addNew(New newI) throws IOException;
    List<New> getAllNew();
    New getByNewId(Long id);
    void deleteNew(Long id);
    New updateNew(Long id, NewDto newDto, MultipartFile imageFile) throws IOException;
}
