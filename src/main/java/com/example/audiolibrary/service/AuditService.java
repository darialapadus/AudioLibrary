package com.example.audiolibrary.service;

import com.example.audiolibrary.model.AuditLog;
import com.example.audiolibrary.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public Page<AuditLog> getAuditLogsForUser(String username, Pageable pageable) {
        return auditLogRepository.findByUsername(username, pageable);
    }

    public void logAction(String action, String username) {
        AuditLog auditLog = new AuditLog(username, action, LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }
}
