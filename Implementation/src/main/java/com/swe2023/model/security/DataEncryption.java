package com.swe2023.model.security;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * Public key is sent as data will be encrypted by it.
 * Private key is used to decrypt.
 */
public class DataEncryption implements Encryptor{
    private static final short PASSWORD_LENGTH= 2048;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Cipher RSACipher;


    public static void main(String[] args) throws UnsupportedEncodingException {
        DataEncryption enc= new DataEncryption();
        String s= "Hello World";
        String encrypted= enc.encrypt(s, enc.getPublicKey());
        String decrypt= enc.decrypt(encrypted);
        System.out.println(s);
        System.out.println(encrypted);
        System.out.println(decrypt);
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

    @Override
    public String encrypt(String data, PublicKey key){
        try {
            byte[] dataToBytes= data.getBytes(StandardCharsets.UTF_8);
            RSACipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes=RSACipher.doFinal(dataToBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String data){
        try{
            RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dataToDecrypt= Base64.getDecoder().decode(data);
            dataToDecrypt=RSACipher.doFinal(dataToDecrypt);
            return new String(dataToDecrypt, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
