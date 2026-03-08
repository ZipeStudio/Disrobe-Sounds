package me.zipestudio.ds.client;

import me.zipestudio.ds.DisrobeSoundsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisrobeSoundsClient {

	public static Logger LOGGER = LoggerFactory.getLogger(DisrobeSoundsServer.MOD_NAME + "/Client");

	public static void onInitializeClient() {
		LOGGER.info("{} Client Initialized", DisrobeSoundsServer.MOD_NAME);
	}

}