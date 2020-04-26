/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatNT.comment;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CommentDTO implements Serializable{
    private int id;
    private String comment;
    private String emailUser;
    private String tittleComment;

    public CommentDTO() {
    }

    public CommentDTO(int id, String comment, String emailUser, String tittleComment) {
        this.id = id;
        this.comment = comment;
        this.emailUser = emailUser;
        this.tittleComment = tittleComment;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the emailUser
     */
    public String getEmailUser() {
        return emailUser;
    }

    /**
     * @param emailUser the emailUser to set
     */
    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    /**
     * @return the tittleComment
     */
    public String getTittleComment() {
        return tittleComment;
    }

    /**
     * @param tittleComment the tittleComment to set
     */
    public void setTittleComment(String tittleComment) {
        this.tittleComment = tittleComment;
    }
    
    
    
}
