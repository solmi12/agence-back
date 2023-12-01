package com.example.employe.management.service;

import com.example.employe.management.Repo.InvoiceRepository;
import com.example.employe.management.dto.InvoiceDTO;
import com.example.employe.management.model.Invoice;
import com.example.employe.management.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        // Convert the DTO to an Invoice entity
        Invoice invoice = new Invoice();
        invoice.setHajName(invoiceDTO.getHajName());
        invoice.setFirstName(invoiceDTO.getFirstName());
        invoice.setLastName(invoiceDTO.getLastName());

        invoice.setDateRes(invoiceDTO.getDateRes());
        invoice.setTypeRoom(invoiceDTO.getTypeRoom());
        invoice.setOptionName(invoiceDTO.getOptionName());
        invoice.setHotelName(invoiceDTO.getHotelName());
        invoice.setViewOfMakkah(invoiceDTO.isViewOfMakkah());
        invoice.setNumbnuit(invoiceDTO.getNumbnuit());
        invoice.setNumberOfAdults(invoiceDTO.getNumberOfAdults());
        invoice.setNumberOfB(invoiceDTO.getNumberOfB());
        invoice.setNumberOfChildren(invoiceDTO.getNumberOfChildren());

        invoice.setCompanyName(invoiceDTO.getCompanyName());
        invoice.setCountry(invoiceDTO.getCountry());
        invoice.setStreet(invoiceDTO.getStreet());
        invoice.setApartment(invoiceDTO.getApartment());
        invoice.setCity(invoiceDTO.getCity());
        invoice.setState(invoiceDTO.getState());
        invoice.setCodePostal(invoiceDTO.getCodePostal());
        invoice.setNumber(invoiceDTO.getNumber());
        invoice.setEmail(invoiceDTO.getEmail());
        invoice.setQuantityAd(invoiceDTO.getQuantityAd());
        invoice.setQuantityVen(invoiceDTO.getQuantityVen());
        invoice.setTotalPrice(invoiceDTO.getTotalPrice());
        PaymentMethod paymentMethod = PaymentMethod.valueOf(invoiceDTO.getPaymentMethod().toUpperCase());
        invoice.setPaymentMethod(paymentMethod);
        // Save the invoice to the database
        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Long invId) {
        return invoiceRepository.findById(invId).orElse(null);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public void deleteInvoice(Long invId) {
        invoiceRepository.deleteById(invId);
    }
}
