package com.crm.crm.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class EmployeeDto {
   // private Date date;
    private Long id;
    @Size(min=10,max=10,message = "Should be 10 digits")
    private String mobile;
    @NotBlank
    @Size(min=3, message = "At leat 3 characters required")
    private String name;
    @Email
    private String emailId;

//    public Date getDate() {
//        return date;
//    }
//    public void setDate(Date date) {
//        this.date = date;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }

    // getters and setters
}
