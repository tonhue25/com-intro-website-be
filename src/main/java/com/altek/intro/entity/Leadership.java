package com.altek.intro.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_LEADERSHIP")
public class Leadership extends AbstractEntity implements Serializable {

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "LINKED_IN")
    private String linkedIn;
}
