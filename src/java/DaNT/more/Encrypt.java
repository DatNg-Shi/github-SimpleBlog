/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaNT.more;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nguye
 */
public class Encrypt implements Serializable{
    
    public String encryptPassword(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        
        String hex = String.format("%064x", new BigInteger(1, digest));
        
        return hex;
    }
    
    public String encryptEmail(String email) {
        if (email != null) {
            email = email.replaceAll("@", "GMAIl12221133131311212");
            email = email.replaceAll("\\.", "3847578174dao9999");
            return email;
        }
        return "";
    }
    
    public String decodeEmail(String email) {
        if (email != null) {
            email = email.replaceAll("GMAIl12221133131311212", "@");
            email = email.replaceAll("3847578174dao9999", "\\.");
            return email;
        }
        return "";
    }
    
    public static void main (){
        int a = 0;
        System.out.println(a);
    }
}
