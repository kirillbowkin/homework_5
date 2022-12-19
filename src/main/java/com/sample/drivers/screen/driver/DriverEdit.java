package com.sample.drivers.screen.driver;

import io.jmix.ui.component.FileUploadField;
import io.jmix.ui.component.Image;
import io.jmix.ui.component.SingleFileUploadField;
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

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        Driver driver = driverDc.getItem();
        if(driver.getPhoto() == null) {
            photoImg.setVisible(false);
        }
    }

    @Subscribe("photoUpload")
    public void onPhotoUploadFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        photoImg.setVisible(true);
    }





}