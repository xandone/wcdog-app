package com.xandone.dog.wcapp.model.bean;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/16 8:58
 */

public class CommentBean {
    private String commentId;
    private String jokeId;
    private String commentUserId;
    private String commentDetails;
    private String commentDate;

    private String commentNick;
    private String commentIcon;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentNick() {
        return commentNick;
    }

    public void setCommentNick(String commentNick) {
        this.commentNick = commentNick;
    }

    public String getCommentIcon() {
        return commentIcon;
    }

    public void setCommentIcon(String commentIcon) {
        this.commentIcon = commentIcon;
    }
}
