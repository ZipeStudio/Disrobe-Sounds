package me.zipestudio.ds.entrypoint;

//? if fabric {

import net.fabricmc.api.ClientModInitializer;

import me.zipestudio.ds.client.DisrobeSoundsClient;

public class ClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DisrobeSoundsClient.onInitializeClient();
	}
}

//?} elif neoforge {

/*import me.zipestudio.ds.client.DisrobeSoundsClient;

public class ClientEntrypoint {

	public static void onInitializeClient() {
		DisrobeSoundsClient.onInitializeClient();
	}

}

*///?} elif forge {
/*import me.zipestudio.ds.client.DisrobeSoundsClient;

public class ClientEntrypoint {

	public static void onInitializeClient() {
		DisrobeSoundsClient.onInitializeClient();
	}

}

*///?}