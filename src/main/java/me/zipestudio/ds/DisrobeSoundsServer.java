package me.zipestudio.ds;

import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisrobeSoundsServer {

	public static final String MOD_NAME = /*$ mod_name*/ "Disrobe Sounds";
	public static final String MOD_ID = /*$ mod_id*/ "disrobe_sounds";

	public static Logger LOGGER = LoggerFactory.getLogger(DisrobeSoundsServer.MOD_NAME);

	public static ResourceLocation id(String path) {
		//? if >=1.21 {
		/*return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
		*///?} else {
		return ResourceLocation.tryBuild(MOD_ID, path);
		 //?}
	}

	public static ResourceLocation parseId(String path) {
		//? if >=1.21 {
		/*return ResourceLocation.parse(path);
		*///?} else {
		return new ResourceLocation(path);
		 //?}
	}

	public static MutableComponent text(String path, Object... args) {
		return Component.translatable(String.format("%s.%s", MOD_ID, path), args);
	}

	public static void onInitialize() {
		LOGGER.info("{} Initialized", MOD_NAME);
	}
}