package io.github.cybertelx.operatorjoinchatmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.cybertelx.operatorjoinchatmod.OperatorJoinChatMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientNetworkMixin {
    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "onGameJoin", at=@At("TAIL"))
    private void onClientGameStart(CallbackInfo ci){
        OperatorJoinChatMod.gameJoined(client);
    }

    @Inject(method="clearWorld", at=@At("HEAD"))
    private void onClientGameLeave(CallbackInfo ci){
        OperatorJoinChatMod.gameLeft();
    }
}