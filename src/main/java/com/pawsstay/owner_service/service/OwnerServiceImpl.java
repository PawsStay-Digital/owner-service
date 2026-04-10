package com.pawsstay.owner_service.service;

import com.pawsstay.owner_service.dto.OwnerCreateRequest;
import com.pawsstay.owner_service.dto.OwnerResponse;
import com.pawsstay.owner_service.dto.OwnerUpdateRequest;
import com.pawsstay.owner_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;


    @Override
    public OwnerResponse createOwner(OwnerCreateRequest req) {
        return null;
    }

    @Override
    public Optional<OwnerResponse> getOwnerByEmail(String email) {

        return Optional.of(OwnerResponse.builder().name("test").id(1L).build());
    }


    @Override
    public OwnerResponse updateOwner(Long id, OwnerUpdateRequest req) {
        return null;
    }
}
