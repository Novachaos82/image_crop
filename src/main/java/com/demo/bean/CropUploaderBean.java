package com.demo.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.extensions.event.ImageAreaSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@NamedEvent
@SessionScoped
public class CropUploaderBean implements Serializable {



    private UploadedFile originalImageFile;


    public UploadedFile getOriginalImageFile() {
        return originalImageFile;
    }

    public void handleFileUpload(FileUploadEvent event) {
        this.originalImageFile = null;

        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            this.originalImageFile = file;
            FacesMessage msg = new FacesMessage("Successful", this.originalImageFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }



    public StreamedContent getImage() {
        return DefaultStreamedContent.builder()
                .contentType(originalImageFile == null ? null : originalImageFile.getContentType())
                .stream(() -> {
                    if (originalImageFile == null
                            || originalImageFile.getContent() == null
                            || originalImageFile.getContent().length == 0) {
                        return null;
                    }

                    try {
                        return new ByteArrayInputStream(originalImageFile.getContent());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }
    
    public void selectEndListener(final ImageAreaSelectEvent e) {
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area selected",
                    "X1: " + e.getX1() + ", X2: " + e.getX2() + ", Y1: " + e.getY1() + ", Y2: " + e.getY2()
                                + ", Image width: " + e.getImgWidth() + ", Image height: " + e.getImgHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    



}