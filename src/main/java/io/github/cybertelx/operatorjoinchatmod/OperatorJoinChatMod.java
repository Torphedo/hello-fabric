package io.github.cybertelx.operatorjoinchatmod;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class OperatorJoinChatMod implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		// on initialize client

		System.out.println("OPJoinChatMod initialized! - Developed by OperatorTheDope/cybertelx");
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}
	
	public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "opjoinchatmod";
    public static final String MOD_NAME = "Operator's Join Chat Mod";

    public static boolean isLoading = false;
    private static boolean isInGame = false;

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static void gameJoined(MinecraftClient client){
        isLoading = false;
        isInGame = true;
        
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        
        if (config.toggle == true) {
        	// "/me &bis haha &eOperator &cand is a bad coder"
        	client.player.sendChatMessage(config.message);
        }
        /*for (int i = 0; i < 5; i++){
            client.player.sendMessage(Text.of("Test spam message"), false);
        }*/
    }

    public static void gameLeft(){
        isInGame = false;
    }
}
