package com.pawsstay.owner_service.service;

import com.pawsstay.owner_service.dto.OwnerCreateRequest;
import com.pawsstay.owner_service.dto.OwnerResponse;
import com.pawsstay.owner_service.dto.OwnerUpdateRequest;

import java.util.Optional;

public interface OwnerService {
    OwnerResponse createOwner(OwnerCreateRequest req);
    Optional<OwnerResponse> getOwnerByEmail(String email);
    OwnerResponse updateOwner(Long id, OwnerUpdateRequest req);
}
