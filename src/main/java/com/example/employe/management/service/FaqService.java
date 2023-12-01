




package com.example.employe.management.service;


        import com.example.employe.management.Repo.FaqRepository;
        import com.example.employe.management.dto.FaqDto;
        import com.example.employe.management.exception.FaqFoundException;
        import com.example.employe.management.model.Faq;
        import lombok.AllArgsConstructor;
        import lombok.NoArgsConstructor;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;

        import org.springframework.stereotype.Service;

        import java.util.Collections;
        import java.util.List;



@Slf4j

@NoArgsConstructor
@Service
@AllArgsConstructor
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;



    public Faq addFaq (FaqDto faqDto ) throws  Exception{

        List<Faq> faqList = faqRepository.findAllByTitleFaq(faqDto.getTitleFaq());
        if (!faqList.isEmpty()) {
            throw new FaqFoundException("Kontakt(s) with the same title already exist");
        }
        Faq faq = new Faq();
        BeanUtils.copyProperties(faqDto, faq);


        return faqRepository.save(faq);

    }

    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Faq getFaqById(Integer id) {
        return faqRepository.findById(id)
                .orElse(null);
    }

    public List<Faq> getFaqsByCategory(String category) {
        return faqRepository.findByCategoriefaq(category);
    }

    public Faq updateFaq(Integer id, FaqDto updatedFaqDto) {
        Faq existingFaq = faqRepository.findById(id)
                .orElse(null);

        if (existingFaq != null) {
            // Update the existing FAQ with the new data
            existingFaq.setTitleFaq(updatedFaqDto.getTitleFaq());
            existingFaq.setDescriptionFaq(updatedFaqDto.getDescriptionFaq());
            existingFaq.setCategoriefaq(updatedFaqDto.getCategoriefaq());

            return faqRepository.save(existingFaq);
        }
        return null; // FAQ not found
    }

    public boolean deleteFaq(Integer id) {
        Faq existingFaq = faqRepository.findById(id)
                .orElse(null);

        if (existingFaq != null) {
            faqRepository.delete(existingFaq);
            return true; // Successfully deleted
        }
        return false; // FAQ not found
    }
}
