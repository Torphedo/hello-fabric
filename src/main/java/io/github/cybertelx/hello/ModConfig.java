package io.github.cybertelx.hello;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "hello")
class ModConfig implements ConfigData {
	@Comment("Toggle the mod on or off.")
	Boolean toggle = true;
	
	@Comment("Toggle the mod in singleplayer (e.g. Do you want to send a message in singleplayer?)")
	Boolean sendSP = false;
	
	@Comment("The message you want to send when you join a server.")
    String message = "Hello! This is a default message, change it in mod menu.";
}