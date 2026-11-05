package me.zipestudio.disrobesounds.entrypoint;

//? if fabric {

import me.zipestudio.disrobesounds.client.DisrobeSoundsClient;

import net.fabricmc.api.ClientModInitializer;

public class ClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		DisrobeSoundsClient.onInitializeClient();
	}

}

//?} elif neoforge {
/*import me.zipestudio.disrobesounds.DisrobeSounds;
import me.zipestudio.disrobesounds.client.DisrobeSoundsClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = DisrobeSounds.MOD_ID, dist = Dist.CLIENT)
public class ClientEntrypoint {

	public ClientEntrypoint() {
		DisrobeSoundsClient.onInitializeClient();
	}

}

*///?} elif forge {

/*import me.zipestudio.disrobesounds.client.DisrobeSoundsClient;

public class ClientEntrypoint {

	public static void onInitializeClient() {
		DisrobeSoundsClient.onInitializeClient();
	}

}

*///?}
