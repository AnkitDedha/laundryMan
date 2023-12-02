package com.laundryman.laundrymanager.util;

import org.springframework.stereotype.Service;
// Import relevant mail packages

@Service
public class EmailService {

    // Inject any required dependencies

    public void sendEmail(String to, String subject, String content) {
        // Logic to send an email
        // This could include integration with an email service provider
    }

    // Additional email-related methods as needed
}
