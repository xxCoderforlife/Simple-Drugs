package me.Coderforlife.Drugs;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  Drugs D = new Drugs(this);
  @Override
  public void onEnable() {
    this.getServer().getPluginManager().registerEvents(new Items(this), this);
    this.getCommand("drugs").setExecutor(new KillerCommands(this));
    D.WeedRecipe();
    D.AcidRecipe();
    D.CokeRecipe();
    D.HeroinRecipe();
    D.PercocetRecipe();
    D.MollyRecipe();

  }

  @Override
  public void onDisable() {

  }
}
