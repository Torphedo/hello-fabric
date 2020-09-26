package io.github.cybertelx.hello;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

import java.time.format.DateTimeFormatter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;    

import java.util.Map;
import java.util.Properties;
import java.util.HashMap; // import the HashMap class

import org.apache.commons.lang3.text.StrSubstitutor;

public class HelloMod implements ClientModInitializer {
	
	static Properties prop;
	
	@Override
	public void onInitializeClient() {
		// on initialize client
		/*
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

		    input = new FileInputStream("./gradle.properties");

		    // load a properties file
		    prop.load(input);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}*/

		System.out.println("Hello! initialized - Developed by cybertelx");
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}
	
	public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "hello";
    public static final String MOD_NAME = "Hello! Mod";

    public static boolean isLoading = false;
    private static boolean isInGame = false;

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
    
    @SuppressWarnings("unchecked")
	private static String resolveString(MinecraftClient client, String message) {
    	DateTimeFormatter hour12 = DateTimeFormatter.ofPattern("hh:mm a");  
		DateTimeFormatter hour24 = DateTimeFormatter.ofPattern("HH:mm");  
		ZonedDateTime now = ZonedDateTime.now();  
		   
		Map valuesMap = new HashMap<String, String>();
		// Username
		
		String username = client.player.getGameProfile().getName();
		valuesMap.put("username", username);
		valuesMap.put("name", username);
		
		// Servers
		// Removed due to weird bug thingymajig.
		/*
		String ip = client.world.getServer().getServerIp();
		String port = Integer.toString(client.world.getServer().getServerPort());
		
		valuesMap.put("ip", ip);
		valuesMap.put("port", port);
		valuesMap.put("server", ip.concat(":").concat(port));*/
		
		// Time
		valuesMap.put("time", hour12.format(now));
		valuesMap.put("24time", hour24.format(now));
		
		// Minecraft info
		valuesMap.put("mc_version", client.getGame().getVersion().getName());
		
		// Misc
		valuesMap.put("os", System.getProperty("os.name"));
		valuesMap.put("java_version", System.getProperty("java.version"));

		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolved = sub.replace(message);
		
		return resolved;
    }

	public static void gameJoined(MinecraftClient client){
        isLoading = false;
        isInGame = true;
        
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        
        if (config.toggle == true) {
        	if (client.isInSingleplayer() == true) {
        		String resolved = resolveString(client, config.message);
        		if (config.sendSP == true) {
        			client.player.sendChatMessage(resolved);
        		}
        	} else {
        		String resolved = resolveString(client, config.message);
        		log(Level.DEBUG, config.message);
        		log(Level.DEBUG, resolved);
        		client.player.sendChatMessage(resolved);
        	}
        	
        }
    }

    public static void gameLeft(){
        isInGame = false;
    }
}
