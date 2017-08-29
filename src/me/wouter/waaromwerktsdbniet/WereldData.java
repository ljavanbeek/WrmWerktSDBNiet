package me.wouter.waaromwerktsdbniet;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WereldData {
	static WereldData instance = new WereldData();
	FileConfiguration werelddatafile;
	File WDFile;

	public static WereldData getInstance() {
		return instance;
	}

	public void setup() {
		this.WDFile = new File("plugins/MinetopiaSDB/Data/Wereld.yml");
		if (!this.WDFile.exists()) {
			try {
				this.WDFile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe("Het is niet gelukt om wereld.yml te maken!");
			}
		}
		this.werelddatafile = YamlConfiguration.loadConfiguration(this.WDFile);
	}

	public FileConfiguration getWereldData() {
		reloadWereldData();	
		return this.werelddatafile;
	}

	public void saveWereldData() {
		try {
			this.werelddatafile.save(this.WDFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe("Het is niet gelukt om wereld.yml op te slaan!");
		}
	}

	public void reloadWereldData() {
		this.werelddatafile = YamlConfiguration.loadConfiguration(this.WDFile);
	}
}
