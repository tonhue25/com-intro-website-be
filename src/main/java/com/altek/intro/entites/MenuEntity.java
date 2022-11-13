package com.altek.intro.entites;

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
@Table(name = "ALT_MENU")
public class MenuEntity extends AbstractEntity implements Serializable {

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LINK")
    private String link;
}