package com.springsun.compareultimate.model;

import java.util.ArrayList;
import java.util.List;

public class SetOfSets {

    static {
        instance = new SetOfSets();
    }

    private static SetOfSets instance;
    private List<SetOfPixels> setOfSetsList;

    private SetOfSets(){
        setOfSetsList = new ArrayList<>();
    }

    public static SetOfSets getInstance(){
        return instance;
    }

    public void addSetOfPixels(SetOfPixels setOfPixels){
        setOfSetsList.add(setOfPixels);
    }

    public List<SetOfPixels> getSetOfSetsList(){
        return setOfSetsList;
    }

    public void clear(){
        setOfSetsList.clear();
    }
}
