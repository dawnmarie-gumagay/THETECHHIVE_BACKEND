package com.it332.wildcatonetap.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class VerificationService {

    @Autowired
    UserRepository userRepository;

    // Verify the reset code
    public boolean verifyResetCode(String idNumber, String resetCode) throws Exception {
        UserEntity user = userRepository.findByIdNumber(idNumber);
        if (user == null) {
            throw new Exception("User not found");
        }

        if (user.getResetCode() == null || !user.getResetCode().equals(resetCode)) {
            throw new Exception("Invalid reset code");
        }

        if (user.getResetCodeExpiration().isBefore(LocalDateTime.now())) {
            throw new Exception("Reset code has expired");
        }

        return true;
    }
}
