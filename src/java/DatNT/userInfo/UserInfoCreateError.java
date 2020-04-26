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
public class UserInfoCreateError implements Serializable{
    private String emailFormatErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatchPassword;
    private String emailIsExisted;

    public UserInfoCreateError() {
    }

    /**
     * @return the emailFormatErr
     */
    public String getEmailFormatErr() {
        return emailFormatErr;
    }

    /**
     * @param emailFormatErr the emailFormatErr to set
     */
    public void setEmailFormatErr(String emailFormatErr) {
        this.emailFormatErr = emailFormatErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMatchPassword
     */
    public String getConfirmNotMatchPassword() {
        return confirmNotMatchPassword;
    }

    /**
     * @param confirmNotMatchPassword the confirmNotMatchPassword to set
     */
    public void setConfirmNotMatchPassword(String confirmNotMatchPassword) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
    }

    /**
     * @return the emailIsExisted
     */
    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    /**
     * @param emailIsExisted the emailIsExisted to set
     */
    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    
    
}
