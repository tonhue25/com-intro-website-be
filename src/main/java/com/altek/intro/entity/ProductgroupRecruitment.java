package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PRODUCT_GROUP_RECRUITMENT")
public class ProductgroupRecruitment extends AbstractEntity implements Serializable {

    ProductGroup productGroup;
    @ManyToOne
    @JoinColumn(name = "product_group_id", nullable = false)
    public ProductGroup getProductGroup() {
        return productGroup;
    }

    private Recruitment recruitment;
    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    public Recruitment getRecruitment() {
        return recruitment;
    }
}
