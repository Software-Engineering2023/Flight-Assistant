package com.swe2023.model.security;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

/**
 * Public key is sent as data will be encrypted by it.
 * Private key is used to decrypt.
 */
public class DataEncryption {
    private static final short PASSWORD_LENGTH= 2048;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Cipher RSACipher;


    public static void main(String[] args) throws UnsupportedEncodingException {
        DataEncryption enc= new DataEncryption();
        String s= "Hello World";
        byte[] encrypted= enc.encrypt(s.getBytes(StandardCharsets.UTF_8), enc.getPublicKey());
        String xx= Base64.getEncoder().encodeToString(encrypted);
        byte[] decrypt= enc.decrypt(Base64.getDecoder().decode(xx));
        System.out.println(s);
        System.out.println(new String(encrypted, StandardCharsets.UTF_8));
        System.out.println(new String(decrypt));
    }
    public DataEncryption(){
        try {
            RSACipher = Cipher.getInstance("RSA");
            setKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator gn = KeyPairGenerator.getInstance("RSA");
        gn.initialize(PASSWORD_LENGTH);
        KeyPair pair= gn.generateKeyPair();
        publicKey= pair.getPublic();
        privateKey= pair.getPrivate();
    }


    public byte[] encrypt(byte[] data, PublicKey key){
        try {
            RSACipher.init(Cipher.ENCRYPT_MODE, key);
            return RSACipher.doFinal(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public byte[] decrypt(byte[] data){
        try{
            RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
            return RSACipher.doFinal(data);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
