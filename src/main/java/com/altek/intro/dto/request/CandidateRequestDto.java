package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequestDto{
    private Long id;
<<<<<<< HEAD
    private String name;
=======
    private Integer status;
    private String fullName;

>>>>>>> tonhue
    private String email;

    private String phoneNumber;
    private byte[] cv;
    private String gender;
    private String address;
    private String dateOfBirth;
    private Long recruitmentId;
}
