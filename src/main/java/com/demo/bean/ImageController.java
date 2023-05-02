package com.demo.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;


import org.primefaces.extensions.event.ImageAreaSelectEvent;


@NamedEvent
@ViewScoped
public class ImageController implements Serializable {

    private static final long serialVersionUID = 20111020L;

    private String imageUrl;

    public ImageController() {
        imageUrl = "/resources/images/testImage1.png";
    }

    public void selectEndListener(final ImageAreaSelectEvent e) {
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area selected",
                    "X1: " + e.getX1() + ", X2: " + e.getX2() + ", Y1: " + e.getY1() + ", Y2: " + e.getY2()
                                + ", Image width: " + e.getImgWidth() + ", Image height: " + e.getImgHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
}