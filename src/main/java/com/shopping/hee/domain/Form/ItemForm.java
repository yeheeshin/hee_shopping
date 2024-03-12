package com.shopping.hee.domain.Form;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Enum.ItemColor;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ItemForm {

    private String name;
    private int price;
    private int count;
    private MultipartFile img;
    private int size;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Enumerated(EnumType.STRING)
    private ItemColor color;

    @Enumerated(EnumType.STRING)
    private Category category;
}
