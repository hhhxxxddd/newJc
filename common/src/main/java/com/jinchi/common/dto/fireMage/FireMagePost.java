package com.jinchi.common.dto.fireMage;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FireMagePost {
    private List<String> batches;
    private List<String> itemNames;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<String> getBatches() {
        return batches;
    }

    public void setBatches(List<String> batches) {
        this.batches = batches;
    }

    public List<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }
}
