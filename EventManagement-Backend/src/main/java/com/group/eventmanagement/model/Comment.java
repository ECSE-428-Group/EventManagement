package com.group.eventmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment
{

    private Long id;
    @Id
    @GeneratedValue
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String content;
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    ///////////////////////////////////////////////////////////////////////////

    private Post post;
    @ManyToOne(optional = false)
    public Post getPost() {
        return this.post;
    }
    public void setPost(Post post) {
        this.post = post;
    }

    ///////////////////////////////////////////////////////////////////////////

    private User commenter;
    @ManyToOne(optional = false)
    public User getCommenter() {
        return this.commenter;
    }
    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }


}
