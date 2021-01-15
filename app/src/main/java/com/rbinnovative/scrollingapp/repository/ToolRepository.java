package com.rbinnovative.scrollingapp.repository;

import com.rbinnovative.scrollingapp.model.Tools;

import java.util.Arrays;
import java.util.List;

import dagger.Component;

@Component
public class ToolRepository {

    private static List<Tools> tools = Arrays.asList(new Tools(), new Tools() , new Tools());

    public static List<Tools> getTools() {
        return tools;
    }
}
