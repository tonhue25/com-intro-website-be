package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ALT_DETAIL_SERVICE")
public class DetailServiceEntity extends AbstractEntity {

    @Column(name = "TECHNOLOGY")
    private String technology;

}
