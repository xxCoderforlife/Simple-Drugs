package me.Coderforlife.SimpleDrugs.Druging.Addiction;

import java.util.HashMap;
import java.util.UUID;


public class AddictionManager {
    public AddictionManager(){

    }
    private HashMap<UUID,Integer> addiction = new HashMap<>();

    /**
     * 
     * @return Player and Addiction Level
     */
    public HashMap<UUID,Integer> addictionMap(){
        return addiction;
    }
}
