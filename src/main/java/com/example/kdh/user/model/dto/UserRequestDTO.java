package com.example.kdh.user.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seq_id")
    private Long seqId;

    @Column(length = 100)
    @NotBlank(message = "사용자명은 공백일 수 없습니다.")
    private String name;

    @Column(length = 100)
    @NotBlank(message = "사용자 이메일은 공백일 수 없습니다.")
    @Email(message = "사용자 이메일이 이메일 형식이 아닙니다.")
    private String email;

    @CreatedBy
    @Column(updatable = false, name="reg_seq_id")
    private Long regId;

    @LastModifiedBy
    @Column(name="mod_seq_id")
    private Long modId;
}
