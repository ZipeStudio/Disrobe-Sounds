package me.zipestudio.ds.entrypoint;

//? if fabric {

import me.zipestudio.ds.DisrobeSoundsServer;

import net.fabricmc.api.ModInitializer;

public class CommonEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		DisrobeSoundsServer.onInitialize();
	}
}

//?} elif neoforge {

/*import me.zipestudio.ds.DisrobeSoundsServer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(DisrobeSoundsServer.MOD_ID)
public class CommonEntrypoint {

    public CommonEntrypoint() {

        DisrobeSoundsServer.onInitialize();

        boolean client =
                //? if >= 1.21.9 {
                /^FMLEnvironment.getDist().isClient();
                ^///?} else {
                FMLEnvironment.dist.isClient();
                //?}

        if (client) {
            ClientEntrypoint.onInitializeClient();
        }

    }

}

*///?} elif forge {

/*import me.zipestudio.ds.DisrobeSoundsServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(DisrobeSoundsServer.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		DisrobeSoundsServer.onInitialize();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientEntrypoint::onInitializeClient);
	}

}

*///?}

