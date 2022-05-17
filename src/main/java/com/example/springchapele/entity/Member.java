package com.example.springchapele.entity;

import com.example.springchapele.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
@Builder
public class Member extends DefaultEntity {

    @Column(name = "nick_name")
    private String nickName;

    private String email;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Integer age;

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

}
