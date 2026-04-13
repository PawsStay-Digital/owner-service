package com.pawsstay.owner_service.controller;

import com.pawsstay.owner_service.dto.OwnerCreateRequest;
import com.pawsstay.owner_service.dto.OwnerResponse;
import com.pawsstay.owner_service.dto.OwnerUpdateRequest;
import com.pawsstay.owner_service.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService ownerService;
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Owner Service is running on Virtual Threads!");
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<OwnerResponse> findOwner(@PathVariable String email){
        if(email == null || email.isBlank()){
            log.warn("email blank");
            return ResponseEntity.badRequest().build();
        }
        Optional<OwnerResponse> ownerByEmail = ownerService.getOwnerByEmail(email);
        return ownerByEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerCreateRequest request){
        OwnerResponse owner = ownerService.createOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable Long id,
            @Valid @RequestBody OwnerUpdateRequest req) {
        OwnerResponse updatedOwner = ownerService.updateOwner(id, req);
        return ResponseEntity.ok(updatedOwner);
    }
    @GetMapping("/me")
    public ResponseEntity<OwnerResponse> getMyProfile(@RequestHeader("X-Owner-Id") Long ownerId) {
        Optional<OwnerResponse> ownerByEmail = ownerService.findById(ownerId);
        return ownerByEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
