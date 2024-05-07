package com.example.spotifind.Utils;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PKCE {

    public static String generateCodeVerifier(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[64];
        secureRandom.nextBytes(codeVerifier);
        return Base64.encodeToString(codeVerifier, Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING);
    }

    public static String deriveCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = codeVerifier.getBytes();
        digest.update(bytes,0, bytes.length);
        byte[] hashedBytes = digest.digest();
        return Base64.encodeToString(hashedBytes, Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING);
    }

}
