package com.comintro.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "services")
public class ServicesEntity extends AbstractEntities{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String descriptionHeader;
    @NotBlank
    private String descriptionBody;
    @NotBlank
    private String img;

    public ServicesEntity(Long id, @NotBlank @Size(max = 50) String title, @NotBlank String descriptionHeader, @NotBlank String descriptionBody, @NotBlank String img) {
        this.id = id;
        this.title = title;
        this.descriptionHeader = descriptionHeader;
        this.descriptionBody = descriptionBody;
        this.img = img;
    }

    public ServicesEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionHeader() {
        return descriptionHeader;
    }

    public void setDescriptionHeader(String descriptionHeader) {
        this.descriptionHeader = descriptionHeader;
    }

    public String getDescriptionBody() {
        return descriptionBody;
    }

    public void setDescriptionBody(String descriptionBody) {
        this.descriptionBody = descriptionBody;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
