package indi.mofan.entity;

import lombok.Getter;

/**
 * @author mofan
 * @date 2021/4/13 14:24
 */
@Getter
public class PrivateConstructor {
    private int state;
    private String code;

    private PrivateConstructor(int state) {
        this.state = state;
    }

    private PrivateConstructor(Integer state) {
        this.state = state;
    }

    private PrivateConstructor(String code) {
        this.code = code;
    }
}
