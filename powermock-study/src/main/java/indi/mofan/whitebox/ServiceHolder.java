package indi.mofan.whitebox;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mofan
 * @date 2021/4/12 17:01
 */
public class ServiceHolder {

    private final Set<Object> objectSet = new HashSet<>();

    public void addService(Object service) {
        objectSet.add(service);
    }

    public void removeService(Object service) {
        objectSet.remove(service);
    }
}
