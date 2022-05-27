package com.lerkin.poststealler;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class AnimeScriptInfo {

    private List<String> animeName;
    private HashMap<String, String> founded;
    private List<String> notFounded;

    public AnimeScriptInfo() {
        this.animeName = new ArrayList<>();
        this.founded = new HashMap<>();
        this.notFounded = new ArrayList<>();
    }
}
