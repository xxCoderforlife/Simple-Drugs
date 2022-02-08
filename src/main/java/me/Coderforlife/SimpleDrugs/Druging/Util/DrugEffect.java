package me.Coderforlife.SimpleDrugs.Druging.Util;

import org.bukkit.potion.PotionEffectType;

public class DrugEffect {

    private PotionEffectType effect;
    private int time;
    private int intensity;

    public DrugEffect(PotionEffectType effect, int time, int intensity) {
        setEffect(effect);
        setTime(time);
        setIntensity(intensity);
    }

    public PotionEffectType getEffect() {
        return effect;
    }

    public void setEffect(PotionEffectType effect) {
        this.effect = effect;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time * 20;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
