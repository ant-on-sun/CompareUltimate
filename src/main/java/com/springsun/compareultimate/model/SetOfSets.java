package com.springsun.compareultimate.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetOfSets {
    final static Logger logger = LogManager.getLogger(SetOfSets.class);

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
        logger.info("SetOfSets has been cleared");
    }
}
