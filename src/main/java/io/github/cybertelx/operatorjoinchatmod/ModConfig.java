package io.github.cybertelx.operatorjoinchatmod;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "opjoinchatmod")
class ModConfig implements ConfigData {
	@Comment("Toggle the mod on or off.")
	Boolean toggle = true;
	
	@Comment("The message you want to send when you join a server.")
    String message = "Connected with OPJoinChatMod. Change this message in Mod Menu. https://bit.ly/2Fo387C";
}