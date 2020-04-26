/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.articles;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author nguye
 */
public class ArticlesDTO implements Serializable{
    private String tittle;
    private String shortDescription;
    private String content;
    private String author;
    private Timestamp dateOfPost;
    private String status;

    public ArticlesDTO() {
    }

    public ArticlesDTO(String tittle, String shortDescription, String content, String author, Timestamp dateOfPost, String status) {
        this.tittle = tittle;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.dateOfPost = dateOfPost;
        this.status = status;
    }

    /**
     * @return the tittle
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * @param tittle the tittle to set
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * @return the shorDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shorDescription the shorDescription to set
     */
    public void setShortDescription(String shorDescription) {
        this.shortDescription = shorDescription;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    
    /**
     * @return the dateOfPost
     */
    public Timestamp getDateOfPost() {
        return dateOfPost;
    }

    /**
     * @param dateOfPost the dateOfPost to set
     */
    public void setDateOfPost(Timestamp dateOfPost) {
        this.dateOfPost = dateOfPost;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
