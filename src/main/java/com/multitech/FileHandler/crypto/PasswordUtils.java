package com.multitech.FileHandler.crypto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtils {
    private static final Integer ITERATIONS = 60000;
    private static final Integer KEY_LENGTH = 256;

    /***
     * Generates a new salt for hashing passwords
     * @return a new random 16 byte array
     */
    public static byte[] nextSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /***
     * Returns a salted and hashed password using the provided hash.
     * @param password  the password to be hashed
     * @param salt      a 16 byte salt
     * @return          the hashed password
     */
    public static byte[] hashPassword(char[] password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Returns true if the given password and salt match the hashed value, false otherwise
     * @param password      password to check
     * @param salt          salt used to hash the password
     * @param expectedHash  expected hashed value of the password
     * @return true if the given password and salt match the expectedHash, false otherwise
     */
    public static boolean validatePassword(String password, byte[] salt, byte[] expectedHash) {
        byte[] testPassword = hashPassword(password.toCharArray(), salt);
        return MessageDigest.isEqual(testPassword, expectedHash);
    }
}
