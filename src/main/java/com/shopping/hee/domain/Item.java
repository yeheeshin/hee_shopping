package com.shopping.hee.domain;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Enum.ItemColor;
import com.shopping.hee.domain.Enum.YesNo;
import com.shopping.hee.domain.Form.ItemForm;
import com.shopping.hee.utils.MyPath;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iseq;

    private String name;
    private int price;
    @Column(columnDefinition = "TEXT")
    private String detail;
    private int count;
    private String img;
    private int size;

    @Enumerated(EnumType.STRING)
    private YesNo deleteStatus;

    @Enumerated(EnumType.STRING)
    private ItemColor color;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static Item createItem(ItemForm itemForm) throws IOException {
        Item item = new Item();

        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setDetail(itemForm.getDetail());
        item.setCount(itemForm.getCount());
        item.setSize(itemForm.getSize());
        item.setColor(itemForm.getColor());
        item.setCategory(itemForm.getCategory());
        item.setDeleteStatus(YesNo.NO);

        String imgName = itemForm.getImg().getOriginalFilename();
        Path imgPath = Paths.get(MyPath.imgPath + imgName);
        Files.write(imgPath, itemForm.getImg().getBytes());

        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + imgName;
        item.setImg(imgFileName);

        return item;
    }

}
