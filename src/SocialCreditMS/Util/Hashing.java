package SocialCreditMS.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hashing {
    public static String createHash(String value, String salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            return value;
        }
        md.update(salt.getBytes());
        var bs = md.digest(value.getBytes(StandardCharsets.UTF_8));
        return new String(bs, StandardCharsets.UTF_8);
    }
}
