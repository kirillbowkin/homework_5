package com.sample.drivers.screen.driver;

import com.sample.drivers.entity.Document;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
    @Autowired
    private InstanceContainer<Driver> driverDc;
    @Autowired
    private Image<byte[]> photoImg;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        Driver driver = driverDc.getItem();
        if (driver.getPhoto() == null) {
            photoImg.setVisible(false);
        }
    }

    @Subscribe("photoUpload")
    public void onPhotoUploadFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        photoImg.setVisible(true);
    }

    @Install(to = "documentsTable.documentLink", subject = "columnGenerator")
    private Component documentsTableDocumentLinkColumnGenerator(Document document) {
        if (document.getFileRef() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);
            linkButton.setCaption(document.getFileRef().getFileName());
            linkButton.addClickListener(clickEvent -> {
                downloader.download(document.getFileRef());
            });
            return linkButton;
        }

        return null;
    }


}