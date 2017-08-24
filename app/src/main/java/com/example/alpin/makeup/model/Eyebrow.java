package com.example.alpin.makeup.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by alpin on 24/08/17.
 */

@Table(name = "Eyebrow")
public class Eyebrow extends Model {
    @Column(name = "Title")
    private String title;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Price")
    private String price;
    @Column(name = "Image")
    private String image;

    public Eyebrow(String title, String name, String price, String image) {
        super();
        this.title = title;
        this.nama = name;
        this.price = price;
        this.image = image;
    }


    public Eyebrow() {
        super();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return nama;
    }

    public void setName(String name) {
        this.nama = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static List<Eyebrow> getAll() {
        return new Select().from(Eyebrow.class)
                .execute();
    }

    @Override
    public String toString() {
        return "Eyebrow{" +
                "title='" + title + '\'' +
                ", name='" + nama + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
