package dto;

import java.time.LocalDateTime;
import java.util.Date;

public class CardDTO {
    private Integer id;
    private Long number;
    private Date exp_date;
    private Integer balance;
    private String status;
    private LocalDateTime createdDate;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Long getNumber() {
        return number;
    }
    public void setNumber(Long number) {
        this.number = number;
    }
    public Date getExp_date() {
        return exp_date;
    }
    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
