package com.mucix.mucix_backend.audio.controller;

import com.mucix.mucix_backend.audio.dto.AudioUploadResponse;
import com.mucix.mucix_backend.audio.service.AudioService;
import com.mucix.mucix_backend.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/audio")
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<AudioUploadResponse>> upload(@RequestParam("file") MultipartFile file) {
        AudioUploadResponse response = audioService.upload(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Audio uploaded successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AudioUploadResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(audioService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AudioUploadResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(audioService.getAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        audioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
