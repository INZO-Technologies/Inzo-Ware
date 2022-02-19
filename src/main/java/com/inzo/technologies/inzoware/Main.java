package com.inzo.technologies.inzoware;

import static com.inzo.technologies.inzoware.Main.*;

import com.inzo.technologies.inzoware.utils.*;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class Main {
    public static final String MOD_ID = "inzoware";
    public static final String MOD_NAME = "Inzo-Ware";
    public static final String MOD_VERSION = "2.11.1";

        @EventHandler
        public void init(FMLInitializationEvent event){   
            Client.Initialize();
        }
}