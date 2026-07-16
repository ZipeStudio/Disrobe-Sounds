package me.zipestudio.disrobesounds.client;

import me.zipestudio.disrobesounds.DisrobeSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisrobeSoundsClient {

	public static Logger LOGGER = LoggerFactory.getLogger(DisrobeSounds.MOD_NAME + "/Client");

	public static void onInitializeClient() {
		LOGGER.info("{} Client Initialized", DisrobeSounds.MOD_NAME);
	}

}