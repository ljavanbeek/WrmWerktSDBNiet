package me.wouter.waaromwerktsdbniet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	Economy econ;

	@Override
	public void onEnable() {
		getLogger().info("WaaromWerktSDBNiet v1.0 geladen.");
		WereldData.getInstance().setup();
	}

	private boolean setupEconomy() {
		try {
			RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
					.getRegistration(net.milkbowl.vault.economy.Economy.class);
			if (economyProvider != null) {
				econ = economyProvider.getProvider();
			}

			return (econ != null);
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 0 && (args[0].equalsIgnoreCase("vault") || args[0].equalsIgnoreCase("economy")
				|| args[0].equalsIgnoreCase("java") || args[0].equalsIgnoreCase("worldedit")
					 || args[0].equalsIgnoreCase("lwc") 
				|| args[0].equalsIgnoreCase("worldguard") || args[0].equalsIgnoreCase("minetopiasdb"))) {
			if (sender instanceof Player) {
				clearPlayer(((Player) sender));
			}
			switch (args[0].toLowerCase()) {
			case "java":
				sender.sendMessage(cc("&3Bij de meeste hosts kan je ergens Java 8 selecteren. "
						+ "\n\n&3Bij server.pro bijvoorbeeld kan dit bij &b'My Server' &3daarna &b'Type'&3 en dan klik je op &b'Update Availible' &3of &b'Change'. &3Daar moet je &bJava 8 &3selecteren en een spigot versie naar keuze."));
				sender.sendMessage(" ");
				sender.sendMessage(cc(
						"&3Indien je niet bij &bserver.pro &3host kan je het op de homepagina bij &bde .jar selectie &3selecteren. Kan dit niet of kom je er niet uit? Contacteer je host of join de discord!"));
				sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
				break;
			case "vault":
				sender.sendMessage(cc("&3Zorg dat je de plugin &bVault &3geinstalleerd hebt. "));
				sender.sendMessage(cc("&3Download: &bhttp://minetopiasdb.nl/vault"));

				sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
				break;
			case "worldedit":
				sender.sendMessage(cc("&3Bij zowel &bWorldEdit &3en &bWorldGuard is de versie belangrijk. "));
				sender.sendMessage(cc("&3Download: &bhttp://MinetopiaSDB.nl/worldedit"));
				sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
				break;
			case "worldguard":
				sender.sendMessage(cc("&3Bij zowel WorldEdit en WorldGuard is de versie belangrijk. "));
				sender.sendMessage(cc("&3Download: &bhttp://minetopiasdb.nl/worldguard"));
				sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
				break;
			case "economy":
				sender.sendMessage(cc("&3Zorg dat je de plugin &bVault &3geinstalleerd hebt. "));
				sender.sendMessage(cc("&3Download: &bhttp://minetopiasdb.nl/vault"));
				sender.sendMessage(" ");
				sender.sendMessage(cc("&3Zorg ook dat je de plugin &bEssentials &3geinstalleerd hebt. "));
				sender.sendMessage(cc(
						"&3Download: &bhttp://minetopiasdb.nl/essentials"));
				sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
				break;
			case "minetopiasdb":
				sender.sendMessage(cc("&3Zorg dat je de plugin &bMinetopiaSDB &3geinstalleerd hebt. "));
				sender.sendMessage(cc("&3Download: &bhttp://minetopiasdb.nl/"));
				break;
			case "lwc":
				sender.sendMessage(cc("&3Zorg dat je de plugin &bEntityLWC &3geinstalleerd hebt in plaats van de normale LWC!"));
				sender.sendMessage(cc("&3Download: &bhttp://minetopiasdb.nl/lwc"));
				break;
			default:

			}
		} else {
			clearPlayer(sender);
			sender.sendMessage(cc("&3-=-=[&bWaaromWerktSDBNiet&3]=-=-"));
			sender.sendMessage(cc("&bJavaversie: " + getJava()));
			sender.sendMessage(cc("&bVault: " + getVault()));
			sender.sendMessage(cc("&bVault economy hook: " + getEcon()));
			sender.sendMessage(cc("&bWorldEdit: " + getWE()));
			sender.sendMessage(cc("&bWorldGuard: " + getWG()));
			if (Bukkit.getPluginManager().isPluginEnabled("LWC")) { 
				sender.sendMessage(cc("&eLWC: " + getLWC()));
			}
			sender.sendMessage(cc("&bMinetopiaSDB: " + getSDB(sender)));
			sender.sendMessage(cc("&3Voor persoonlijke hulp join de discord &bhttp://minetopiasdb.nl/discord"));
		}
		return true;
	}

	public String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public String getJava() {
		if (System.getProperty("java.version").startsWith("1.8.")) {
			return cc("&3Goede versie! &b(&3" + System.getProperty("java.version") + "&b)");
		}else if (System.getProperty("java.version").startsWith("1.9.")) { 
			return cc("&3Goede versie! &b(&3" + System.getProperty("java.version") + "&b)&3. Toch problemen? Probeer Java 8!");
		}
		return cc("&cFoute versie! (&4" + System.getProperty("java.version")
				+ "&4) \n&cOm op te lossen /wrmwerktsdbniet Java");
	}

	public String getVault() {
		try {
			if (Bukkit.getPluginManager().getPlugin("Vault") != null
					&& Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
				return cc("&3Goede versie! &b(&3"
						+ Bukkit.getPluginManager().getPlugin("Vault").getDescription().getVersion() + "&b)");
			}
			return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet vault");
		} catch (Exception ex) {
			return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet vault");
		}
	}

	public String getEcon() {
		if (!getVault().contains("Fout") && !getVault().contains("niet")) {
			if (setupEconomy()) {
				return cc("&3Goede versie! &b(&3" + econ.getName() + "&b)");
			}
			return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet economy");
		} else {
			return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet economy");
		}
	}

	public String getWE() {
		if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null
				&& Bukkit.getPluginManager().getPlugin("WorldEdit").isEnabled()) {
			return cc("&3Goede versie! &b(&3"
					+ Bukkit.getPluginManager().getPlugin("WorldEdit").getDescription().getVersion() + "&b)");
		}
		return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet WorldEdit");
	}

	public String getWG() {
		if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null
				&& Bukkit.getPluginManager().getPlugin("WorldGuard").isEnabled()) {
			return cc("&3Goede versie! &b(&3"
					+ Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion() + "&b)");
		}
		return cc("&cFoute versie of niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet WorldGuard");
	}

	public String getSDB(CommandSender s) {
		if (Bukkit.getPluginManager().getPlugin("MinetopiaSDB") != null) {
			String ver = "?";
			if (Bukkit.getPluginManager().getPlugin("MinetopiaSDB").isEnabled()) {
				ver = Bukkit.getPluginManager().getPlugin("MinetopiaSDB").getDescription().getVersion();
			}
			if (WereldData.getInstance().getWereldData().getKeys(false).isEmpty()) {
				String world = "<Wereld>";
				if (s instanceof Player) {
					world = ((Player) s).getWorld().getName();
				}
				return cc("&cVoeg een wereld toe met /wereld add " + world);
			} else {
				return cc("&3Geinstalleerd &b(&3" + ver + "&b)");
			}
		}
		return cc("&cMinetopiaSDB is niet geinstalleerd!\n&cOm op te lossen /wrmwerktsdbniet MinetopiaSDB");
	}
	
	public String getLWC() {
		PluginManager pm = Bukkit.getPluginManager();
		if (pm.getPlugin("LWC").getDescription().getAuthors().contains("Me_Goes_RAWR")) {
			return cc("&eGoede versie! Problemen met WorldGuard? Verwijder het mapje 'libs' in plugins -> LWC");
		} else {
			return cc("&cFoute versie!\n&cOm op te lossen /wrmwerktsdbniet LWC");
		}
	}

	public void clearPlayer(CommandSender p) {
		int i = 0;
		while (i < 200) {
			p.sendMessage(" ");
			i++;
		}
	}
}
