package com.example.creditstoragebackend.File;

import java.util.ArrayList;
import com.example.creditstoragebackend.appuser.User;
import com.example.creditstoragebackend.Encryption.AES;

public class File {
    private String filename;
    private String data;
    private String originalName;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private double size;
    private User owner;

    public File(String filename, String data, String originalName, User owner) {
        this.filename = filename;
        this.data = data;
        this.originalName = originalName;
        this.owner = owner;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public ArrayList<File> shard(File file) {
        return null;
    }

    public String encrypt(File file) {
        String text = getData();
        String key = "User.getKey()";
        AES encryptor = new AES();
        String encryptedText = encryptor.encrypt(text, key);
        return encryptedText;
    }

    public String decrypt(File file) {
        String text = getData();
        String key = "User.getKey()";
        AES decryptor = new AES();
        String decryptedText = decryptor.decrypt(text, key);
        return decryptedText;
    }
}
