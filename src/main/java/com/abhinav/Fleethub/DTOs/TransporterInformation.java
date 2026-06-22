package com.abhinav.Fleethub.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransporterInformation {
    String username;
    String phoneNumber;
}
