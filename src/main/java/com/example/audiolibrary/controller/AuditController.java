package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.AuditLog;
import com.example.audiolibrary.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<AuditLog> getAuditLogs(@PathVariable String username,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return auditService.getAuditLogsForUser(username, PageRequest.of(page, size));
    }
}
