package com.comintro.dto;

public class ServicesDTO extends AbstractDTO{
    private Long id;
    private String title;
    private String descriptionHeader;
    private String descriptionBody;
    private String image;

    public ServicesDTO() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
