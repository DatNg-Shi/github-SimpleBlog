/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.userInfo;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class LoginError implements Serializable{
    private String emailNotCorrect;
    private String accountNotFound;

    public LoginError() {
    }

    /**
     * @return the emailNotCorrect
     */
    public String getEmailNotCorrect() {
        return emailNotCorrect;
    }

    /**
     * @param emailNotCorrect the emailNotCorrect to set
     */
    public void setEmailNotCorrect(String emailNotCorrect) {
        this.emailNotCorrect = emailNotCorrect;
    }

    /**
     * @return the accountNotFound
     */
    public String getAccountNotFound() {
        return accountNotFound;
    }

    /**
     * @param accountNotFound the accountNotFound to set
     */
    public void setAccountNotFound(String accountNotFound) {
        this.accountNotFound = accountNotFound;
    }

    
    
}
