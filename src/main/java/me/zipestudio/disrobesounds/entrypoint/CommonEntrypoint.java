package me.zipestudio.disrobesounds.entrypoint;

//? if fabric {

import me.zipestudio.disrobesounds.DisrobeSounds;

import net.fabricmc.api.ModInitializer;

public class CommonEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		DisrobeSounds.onInitialize();
	}

}

//?} elif neoforge {
/*import me.zipestudio.disrobesounds.DisrobeSounds;
import net.neoforged.fml.common.Mod;

@Mod(DisrobeSounds.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		DisrobeSounds.onInitialize();
	}

}

*///?} elif forge {

/*import me.zipestudio.disrobesounds.DisrobeSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(DisrobeSounds.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		DisrobeSounds.onInitialize();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientEntrypoint::onInitializeClient);
	}

}

*///?}
