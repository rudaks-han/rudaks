package kr.co.rudaks.web.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryForm implements Serializable
{
    private int id;
    private String category;
    private String name;
    private String deleteFlag;
    private String publicFlag;
    private int sortOrder;
    private String description;
    private String createdDate;
    private String updatedDate;
}
