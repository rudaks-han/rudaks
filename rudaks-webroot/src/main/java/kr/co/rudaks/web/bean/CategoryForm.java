package kr.co.rudaks.web.bean;

import lombok.Data;

@Data
public class CategoryForm
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
