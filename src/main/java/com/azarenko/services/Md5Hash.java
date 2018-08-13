package com.azarenko.services;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Hash {
    public Md5Hash() {
    }
    public String getMd5Hash(String text){
        return DigestUtils.md5Hex(text);
    }
}
