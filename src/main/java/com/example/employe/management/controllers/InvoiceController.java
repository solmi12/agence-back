        package com.example.employe.management.controllers;

        import com.example.employe.management.dto.InvoiceDTO;
        import com.example.employe.management.model.Invoice;
        import com.example.employe.management.service.InvoiceService;
        import com.example.employe.management.service.PaypalService;
        import com.paypal.api.payments.Payment;
        import com.paypal.base.rest.PayPalRESTException;

        import jakarta.validation.Valid;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;


        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;

        import java.math.BigDecimal;
        import java.math.RoundingMode;
        import java.util.Collections;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        @RestController
        @RequestMapping("/invoices")
        public class InvoiceController {

            @Autowired
            private InvoiceService invoiceService;
            @Autowired
            private PaypalService paypalService;

            @PostMapping("/create-and-pay")
            public ResponseEntity<Map<String, String>> createAndPayInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, HttpServletRequest request) {
                Map<String, String> response = new HashMap<>();

                try {
                    String paymentMethod = invoiceDTO.getPaymentMethod();

                    if ("paypal".equalsIgnoreCase(paymentMethod)) {
                        Invoice createdInvoice = invoiceService.createInvoice(invoiceDTO);

                        BigDecimal totalPrice = createdInvoice.getTotalPrice();
                        String totalPriceString = totalPrice.setScale(2, RoundingMode.HALF_UP).toString();

                        String cancelUrl = "http://localhost:4200/home";
                        String successUrl = "http://localhost:4200/home";

                        Payment payment = paypalService.createPayment(totalPriceString, cancelUrl, successUrl);

                        String approvalUrl = payment.getLinks().stream()
                                .filter(link -> link.getRel().equalsIgnoreCase("approval_url"))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("No PayPal approval URL found"))
                                .getHref();

                        HttpSession session = request.getSession();
                        session.setAttribute("paymentId", payment.getId());

                        // Payment method is PayPal
                        response.put("message", "Payment method is PayPal");
                        return ResponseEntity.ok(response);
                    } else {
                        // For other payment methods (e.g., in place, transfer), simply create and add the invoice to the database
                        Invoice createdInvoice = invoiceService.createInvoice(invoiceDTO);

                        // Payment method is other than PayPal
                        response.put("message", "Payment method other than PayPal");
                        return ResponseEntity.ok(response);
                    }
                } catch (PayPalRESTException e) {
                    response.put("message", e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            }


            @GetMapping("/success")
            public ResponseEntity<String> successPayment(HttpServletRequest request, @RequestParam("PayerID") String payerId) {
                try {
                    // Get the payment ID from the session
                    HttpSession session = request.getSession();
                    String paymentId = (String) session.getAttribute("paymentId");

                    // Execute the PayPal payment
                    Payment payment = paypalService.executePayment(paymentId, payerId);

                    // Handle successful payment (e.g., update the order status)
                    // ...

                    return new ResponseEntity<>("Payment successful", HttpStatus.OK);
                } catch (PayPalRESTException e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


            @GetMapping
            public ResponseEntity<List<Invoice>> getAllInvoices() {
                List<Invoice> invoices = invoiceService.getAllInvoices();
                return new ResponseEntity<>(invoices, HttpStatus.OK);
            }

            @GetMapping("/{invId}")
            public ResponseEntity<Invoice> getInvoice(@PathVariable Long invId) {
                Invoice invoice = invoiceService.getInvoiceById(invId);
                if (invoice != null) {
                    return new ResponseEntity<>(invoice, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            @DeleteMapping("/{invId}")
            public ResponseEntity<Map<String, String>> deleteInvoice(@PathVariable Long invId) {
                try {
                    invoiceService.deleteInvoice(invId);

                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Invoice deleted successfully");

                    return ResponseEntity.ok(response);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Collections.singletonMap("error", "Error deleting invoice"));
                }
            }

        }
