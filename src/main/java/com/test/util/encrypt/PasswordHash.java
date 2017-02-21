package com.test.util.encrypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * Created by rmiao on 2/21/2017.
 */
public class PasswordHash
{
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTES = 18;
    public static final int HASH_BYTES = 54;
    public static final int PBKDF2_ITERATIONS = 1023;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return createHash(password.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password    the password to check
     * @param   goodHash    the hash of the valid password
     * @return              true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
        return validatePassword(password.toCharArray(), goodHash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password    the password to check
     * @param   goodHash    the hash of the valid password
     * @return              true if the password is correct, false if not
     */
    public static boolean validatePassword(char[] password, String goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
        // Decode the hash into its parameters
        String[] params = goodHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param   a       the first byte array
     * @param   b       the second byte array
     * @return          true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    /**
     *  Computes the PBKDF2 hash of a password.
     *
     * @param   password    the password to hash.
     * @param   salt        the salt
     * @param   iterations  the iteration count (slowness factor)
     * @param   bytes       the length of the hash to compute in bytes
     * @return              the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param   hex         the hex string
     * @return              the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) throws DecoderException {
        byte[] bytes = Hex.decodeHex(hex.toCharArray());
        return bytes;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param   array       the byte array to convert
     * @return              a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array)
    {
        char[] chars = Hex.encodeHex(array);
        return new String(chars);
    }

    /**
     * Tests the basic functionality of the PasswordHash class
     *
     * @param   args        ignored
     */
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, DecoderException {
        String password = "mrf";
        String p1 = PasswordHash.createHash(password);
        String p2 = PasswordHash.createHash(password);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.length());
        String salt = p1.split(":")[1];
        String p = p1.split(":")[2];
        System.out.println(salt.length());
        System.out.println(p.length());
        boolean result = PasswordHash.validatePassword(password, p1);
        System.out.println(result);
        boolean result2 = PasswordHash.validatePassword(password, p2);
        System.out.println(result2);

    }

}
