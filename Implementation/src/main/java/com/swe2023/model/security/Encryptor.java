package com.swe2023.model.security;

import java.security.PublicKey;

public interface Encryptor {
    /**
     * This is supposed to be using Public Key
     */
    String encrypt(String data, PublicKey key);

    /**
     * This is done using Private Key
     */
    String decrypt(String data);

    PublicKey getPublicKey();
}
