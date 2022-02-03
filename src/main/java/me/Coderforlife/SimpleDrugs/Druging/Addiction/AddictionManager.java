package me.Coderforlife.SimpleDrugs.Druging.Addiction;

import java.util.HashMap;
import java.util.UUID;


public class AddictionManager {
    public AddictionManager(){

    }
    private HashMap<UUID,Double> addiction = new HashMap<>();

    /**
     * @apiNote Uses Wrapper for Double
     * @return Player and Addiction Level
     */
    public HashMap<UUID,Double> addictionMap(){
        return addiction;
    }
}
