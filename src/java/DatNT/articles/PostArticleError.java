/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.articles;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class PostArticleError implements Serializable{
    private String tittleEmptyErr;
    private String shortDescriptionEmptyErr;
    private String contentEmptyErr;
    private String tittleIsExisted;

    public PostArticleError() {
    }

    /**
     * @return the tittleEmptyErr
     */
    public String getTittleEmptyErr() {
        return tittleEmptyErr;
    }

    /**
     * @param tittleEmptyErr the tittleEmptyErr to set
     */
    public void setTittleEmptyErr(String tittleEmptyErr) {
        this.tittleEmptyErr = tittleEmptyErr;
    }

    /**
     * @return the shortDescriptionEmptyErr
     */
    public String getShortDescriptionEmptyErr() {
        return shortDescriptionEmptyErr;
    }

    /**
     * @param shortDescriptionEmptyErr the shortDescriptionEmptyErr to set
     */
    public void setShortDescriptionEmptyErr(String shortDescriptionEmptyErr) {
        this.shortDescriptionEmptyErr = shortDescriptionEmptyErr;
    }

    /**
     * @return the contentEmptyErr
     */
    public String getContentEmptyErr() {
        return contentEmptyErr;
    }

    /**
     * @param contentEmptyErr the contentEmptyErr to set
     */
    public void setContentEmptyErr(String contentEmptyErr) {
        this.contentEmptyErr = contentEmptyErr;
    }

    /**
     * @return the tittleIsExisted
     */
    public String getTittleIsExisted() {
        return tittleIsExisted;
    }

    /**
     * @param tittleIsExisted the tittleIsExisted to set
     */
    public void setTittleIsExisted(String tittleIsExisted) {
        this.tittleIsExisted = tittleIsExisted;
    }
    
    
}
