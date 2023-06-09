package indi.mofan.entity;

import lombok.Getter;

/**
 * @author mofan
 * @date 2021/4/12 17:23
 */
@Getter
public class Cat extends Animal{
    private String name;

    public String getSuperName() {
        return super.getName();
    }
}
