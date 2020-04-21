package com.kuir.library.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Jieshu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bookname;
    private Integer sid;
    private String isbn;
    private Integer loan;
    private Integer bookid;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
        this.loan = loan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Jieshu{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", sid=" + sid +
                ", isbn='" + isbn + '\'' +
                ", loan=" + loan +
                ", bookid=" + bookid +
                ", date=" + date +
                '}';
    }
}
