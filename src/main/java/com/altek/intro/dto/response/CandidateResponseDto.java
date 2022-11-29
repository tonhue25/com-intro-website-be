package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDto extends AbstractResponseDto {

<<<<<<< HEAD
<<<<<<< HEAD
    private String name;
=======
    private Long id;
    private Integer status;
    private String fullName;
>>>>>>> tonhue
=======
    private Long id;
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
