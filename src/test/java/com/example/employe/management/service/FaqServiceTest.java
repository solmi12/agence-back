package com.example.employe.management.service;

import com.example.employe.management.Repo.FaqRepository;
import com.example.employe.management.dto.FaqDto;
import com.example.employe.management.exception.FaqFoundException;
import com.example.employe.management.model.Faq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqService faqService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFaq_WithNonExistingTitle_ShouldReturnSavedFaq() throws Exception {
        // Arrange
        FaqDto faqDto = new FaqDto();
        faqDto.setTitleFaq("New Title");
        faqDto.setDescriptionFaq("Description");
        faqDto.setCategoriefaq("Category");

        when(faqRepository.findAllByTitleFaq("New Title")).thenReturn(Collections.emptyList());
        when(faqRepository.save(any(Faq.class))).thenReturn(new Faq());

        // Act
        Faq savedFaq = faqService.addFaq(faqDto);

        // Assert
        assertNotNull(savedFaq);
        verify(faqRepository, times(1)).save(any(Faq.class));
    }

    @Test
    void addFaq_WithExistingTitle_ShouldThrowException() {
        // Arrange
        FaqDto faqDto = new FaqDto();
        faqDto.setTitleFaq("Existing Title");

        when(faqRepository.findAllByTitleFaq("Existing Title")).thenReturn(Arrays.asList(new Faq()));

        // Act & Assert
        assertThrows(FaqFoundException.class, () -> faqService.addFaq(faqDto));
        verify(faqRepository, never()).save(any(Faq.class));
    }


    @Test
    public void testUpdateFaq(){


        Faq existingFaq = new Faq();
        existingFaq.setFaqId(1);
        existingFaq.setTitleFaq("existing title");

        FaqDto updatedFaqDto = new FaqDto();
        updatedFaqDto.setTitleFaq("updated Faq");
        when(faqRepository.findById(1)).thenReturn(Optional.of(existingFaq));
        when(faqRepository.save(any(Faq.class))).thenReturn(existingFaq);

        Faq updatedFaq = faqService.updateFaq(1, updatedFaqDto);

        assertNotNull(updatedFaq);
        assertEquals(updatedFaq.getTitleFaq(),"updated Faq");
    }

    @Test
    public void testGetAllFaqs() {
        // Mock data
        List<Faq> faqList = new ArrayList<>();
        faqList.add(new Faq());
        faqList.add(new Faq());

        // Mocking repository
        when(faqRepository.findAll()).thenReturn(faqList);

        // Call the service method
        List<Faq> allFaqs = faqService.getAllFaqs();

        // Assertions
        assertNotNull(allFaqs);
        assertEquals(2, allFaqs.size());
    }
    @Test
    public void testDeleteFaq() {
        // Mock data
        Faq existingFaq = new Faq();
        existingFaq.setFaqId(1);
        existingFaq.setTitleFaq("Existing FAQ");

        when(faqRepository.findById(1)).thenReturn(java.util.Optional.of(existingFaq));

        boolean result = faqService.deleteFaq(1);

        assertTrue(result);
    }
}