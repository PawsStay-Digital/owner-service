package com.pawsstay.owner_service.service;

import com.pawsstay.owner_service.dto.OwnerCreateRequest;
import com.pawsstay.owner_service.dto.OwnerResponse;
import com.pawsstay.owner_service.dto.OwnerUpdateRequest;
import com.pawsstay.owner_service.entity.Owner;
import com.pawsstay.owner_service.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwnerResponse createOwner(OwnerCreateRequest req) {
        Owner owner = Owner.builder().name(req.getName()).email(req.getEmail()).build();
        Owner savedOwner = ownerRepository.save(owner);

        return convertToRes(savedOwner);
    }


    @Override
    public Optional<OwnerResponse> getOwnerByEmail(String email) {
        Optional<Owner> ownerOptional = ownerRepository.findByEmail(email);
        return ownerOptional.map(this::convertToRes);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwnerResponse updateOwner(Long id, OwnerUpdateRequest req) {
        Owner owner = ownerRepository.findOwnerById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        owner.setName(req.getName());
        owner.setPhone(req.getPhone());
        owner.setAddress(req.getAddress());
        owner.setBirthday(req.getBirthday());
        owner.setPhotoUrl(req.getPhotoUrl());
        Owner ownerUpdate = ownerRepository.save(owner);
        return convertToRes(ownerUpdate);
    }
    private OwnerResponse convertToRes(Owner owner) {
        return OwnerResponse.builder().id(owner.getId())
                .name(owner.getName())
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                .birthday(owner.getBirthday())
                .photoUrl(owner.getPhotoUrl()).build();
    }
}
