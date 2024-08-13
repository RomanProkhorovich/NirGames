package com.example.nirgames.controller;

import com.example.nirgames.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final StorageService storageService;

    @GetMapping()
    public ResponseEntity<Resource> getFileByGameName(@RequestParam("path") String title) {
        Resource resource = storageService.loadAsResource( title);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @PostMapping(value = "/upload-file",
    produces = {"application/text"})
    public ResponseEntity uploadFile(@RequestBody MultipartFile file) {
        String name = storageService.store(file);
        return ResponseEntity.ok(null);
    }
}

