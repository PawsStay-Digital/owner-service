package com.pawsstay.owner_service.service;

import com.pawsstay.owner_service.dto.OwnerCreateRequest;
import com.pawsstay.owner_service.dto.OwnerResponse;
import com.pawsstay.owner_service.entity.Owner;
import com.pawsstay.owner_service.repository.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest {
    @Mock
    private OwnerRepository ownerRepository;

    // 將上面的假 Repository 注入到真正的 Service 中
    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Test
    @DisplayName("createOwner_Success")
    void createOwner_Success() {
        // [Arrange]
        OwnerCreateRequest req = new OwnerCreateRequest();
        req.setEmail("test@gmail.com");
        req.setName("Jack Wang");

        Owner savedOwner = new Owner();
        savedOwner.setId(1L);
        savedOwner.setEmail("test@gmail.com");
        savedOwner.setName("Jack Wang");
        when(ownerRepository.save(any(Owner.class))).thenReturn(savedOwner);

        // [Act]
        OwnerResponse result = ownerService.createOwner(req);

        // [Assert]
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test@gmail.com", result.getEmail());
        assertEquals("Jack Wang", result.getName());

        //
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    @DisplayName("createOwner_DuplicateEmail_ThrowsException")
    void createOwner_DuplicateEmail_ThrowsException() {
        // [Arrange]
        OwnerCreateRequest req = new OwnerCreateRequest();
        req.setEmail("duplicate@gmail.com");

        when(ownerRepository.save(any(Owner.class)))
                .thenThrow(new DataIntegrityViolationException("Email already exists"));

        // [Act & Assert] when createOwner ，then throw DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> {
            ownerService.createOwner(req);
        });

        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

}
