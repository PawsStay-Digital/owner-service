package com.pawsstay.owner_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerUpdateRequest {
    private String name;
    private String phone;
    private String address;
    private String photoUrl;
    private LocalDate birthday;
}
