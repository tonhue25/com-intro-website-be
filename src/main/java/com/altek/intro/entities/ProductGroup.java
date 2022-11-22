package com.altek.intro.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ALT_PRODUCT_GROUP")
public class ProductGroup extends AbstractEntity implements Serializable {

    @Column(name = "NAME")
    private String name;

    List<ProductgroupRecruitment> productgroupRecruitments;
    @OneToMany(mappedBy = "productGroup")
    public List<ProductgroupRecruitment> getProductgroupRecruitments() {
        return productgroupRecruitments;
    }
}
