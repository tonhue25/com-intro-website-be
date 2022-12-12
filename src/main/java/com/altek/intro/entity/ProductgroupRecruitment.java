package com.altek.intro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PRODUCT_GROUP_RECRUITMENT")
public class ProductgroupRecruitment extends AbstractEntity implements Serializable {

    private ProductGroup productGroup;
    private Recruitment recruitment;

    public ProductgroupRecruitment(Integer status, ProductGroup productGroup, Recruitment recruitment) {
        super(status);
        this.productGroup = productGroup;
        this.recruitment = recruitment;
    }

    @ManyToOne
    @JoinColumn(name = "product_group_id", nullable = false)
    public ProductGroup getProductGroup() {
        return productGroup;
    }

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    public Recruitment getRecruitment() {
        return recruitment;
    }
}
