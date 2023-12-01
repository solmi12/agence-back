package com.example.employe.management.controllers;

import com.example.employe.management.dto.NewDto;
import com.example.employe.management.exception.HajFoundException;
import com.example.employe.management.exception.NewFoundException;
import com.example.employe.management.model.New;
import com.example.employe.management.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewController {

    @Autowired
    private NewService newService;

    @GetMapping
    public List<New> getAllNews() {
        return newService.getAllNew();
    }

    @GetMapping("/{id}")
    public ResponseEntity<New> getNewById(@PathVariable Long id) {
        New news = newService.getByNewId(id);
        return ResponseEntity.ok(news);
    }

    @PostMapping(value = "/add" ,  consumes = { "multipart/form-data" })
    public ResponseEntity<?> createNew(    @RequestPart(name = "image", required = true) MultipartFile file,
                                           @RequestParam(name = "newName", required = true) String newName,
                                           @RequestParam(name = "newDescription", required = true) String newDescription)  {

        try {
            New newNew = new New();
            newNew.setNewName(newName);
            newNew.setNewDescription(newDescription);
            newNew.setImage(file.getBytes());

            newNew = newService.addNew(newNew);

            return new ResponseEntity<>(newNew, HttpStatus.CREATED);
        }
        catch (NewFoundException ex) {
            return new ResponseEntity<>("New with the same title already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("An error occurred while adding New", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<New> updateNew(@PathVariable Long id, @RequestBody NewDto newDto, @RequestParam("image") MultipartFile imageFile) throws IOException {
        New updatedNew = newService.updateNew(id, newDto, imageFile);
        return ResponseEntity.ok(updatedNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNew(@PathVariable Long id) {
        newService.deleteNew(id);
        return ResponseEntity.noContent().build();
    }
}
