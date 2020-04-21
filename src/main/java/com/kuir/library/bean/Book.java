package com.kuir.library.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 图书信息
 * id
 * 出版社
 * isbn
 * 作者
 * 简介
 * 封面
 * 总数
 * 借出
 * 价钱
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bookname;
    private String publishing;
    private String isbn;
    private String writer;
    private String intro;
    private String cover;
    private Integer sum;
    private Integer money;
    private Integer loan;
    private String leixing;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
        this.loan = loan;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", publishing='" + publishing + '\'' +
                ", isbn='" + isbn + '\'' +
                ", writer='" + writer + '\'' +
                ", intro='" + intro + '\'' +
                ", cover='" + cover + '\'' +
                ", sum=" + sum +
                ", money=" + money +
                ", loan=" + loan +
                ", leixing='" + leixing + '\'' +
                ", date=" + date +
                '}';
    }
}
