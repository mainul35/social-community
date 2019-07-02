package com.mainul35.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Visibility {
    PUBLIC, PRIVATE;

    private static Map<Visibility, String> getVisibilityMap(){
        Map<Visibility, String> map = new HashMap<>();
        map.put(Visibility.PUBLIC, "Public");
        map.put(Visibility.PRIVATE, "Private");
        return map;
    }

    public static List<Visibility> getVisibilityList(){
        List<Visibility> visibilities = new ArrayList<>();
        visibilities.add(Visibility.PUBLIC);
        visibilities.add(Visibility.PRIVATE);
        return visibilities;
    }

    public static String valueOf(Visibility visibility){
        return getVisibilityMap().get(visibility);
    }
}
