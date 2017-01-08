package pl.devone.bookstore.service.dto;

import java.util.Date;

/**
 * Created by Lukasz Jedrzynski on 07.01.2017.
 */
public class BookDTO {

  private Long id;

  private String title;

  private String author;

  private Double price;

  private String publisher;

  private Date publicationDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  @Override
  public String toString() {
    return "BookDTO{" +
      "title='" + title + '\'' +
      ", author='" + author + '\'' +
      ", price=" + price +
      ", publisher='" + publisher + '\'' +
      ", publicationDate=" + publicationDate +
      '}';
  }
}
