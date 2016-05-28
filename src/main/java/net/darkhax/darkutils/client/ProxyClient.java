package net.darkhax.darkutils.client;

import net.darkhax.darkutils.addons.AddonHandler;
import net.darkhax.darkutils.blocks.BlockFilter;
import net.darkhax.darkutils.blocks.BlockTrapEffect;
import net.darkhax.darkutils.client.renderer.PotionColorHandler;
import net.darkhax.darkutils.client.statemap.StateMapSneaky;
import net.darkhax.darkutils.common.ProxyCommon;
import net.darkhax.darkutils.handler.ContentHandler;
import net.darkhax.darkutils.items.ItemMaterial;
import net.darkhax.darkutils.items.ItemMysteriousPotion;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient extends ProxyCommon {
    
    private static final StateMapperBase SNEAKY_MAP = new StateMapSneaky();
    
    @Override
    public void onPreInit () {
        
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        
        Item item;
        
        item = Item.getItemFromBlock(ContentHandler.blockTrap);
        
        for (final BlockTrapEffect.EnumType varient : BlockTrapEffect.EnumType.values())
            ModelLoader.setCustomModelResourceLocation(item, varient.meta, new ModelResourceLocation("darkutils:trap_" + varient.type, "inventory"));
            
        item = Item.getItemFromBlock(ContentHandler.blockEnderTether);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:ender_tether", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockTrapMovement);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:trap_move", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockTrapMovementFast);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:trap_move_fast", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockTrapMovementHyper);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:trap_move_hyper", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockGrate);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:grate", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockFilter);
        for (final BlockFilter.EnumType varient : BlockFilter.EnumType.values())
            ModelLoader.setCustomModelResourceLocation(item, varient.meta, new ModelResourceLocation("darkutils:filter_" + varient.type, "inventory"));
            
        item = Item.getItemFromBlock(ContentHandler.blockTimer);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:timer", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockAntiSlime);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:anti_slime", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockDetector);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:detector", "inventory"));
        
        item = Item.getItemFromBlock(ContentHandler.blockCakeEPlus);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:cake_eplus", "inventory"));
        
        for (int materialMeta = 0; materialMeta < ItemMaterial.varients.length; materialMeta++)
            ModelLoader.setCustomModelResourceLocation(ContentHandler.itemMaterial, materialMeta, new ModelResourceLocation("darkutils:material_" + ItemMaterial.varients[materialMeta], "inventory"));
            
        for (int meta = 0; meta < ItemMysteriousPotion.varients.length; meta++)
            ModelLoader.setCustomModelResourceLocation(ContentHandler.itemPotion, meta, new ModelResourceLocation("bottle_drinkable", "inventory"));
            
        item = Item.getItemFromBlock(ContentHandler.blockFeeder);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:feeder_empty", "inventory"));
        ModelLoader.setCustomModelResourceLocation(item, 10, new ModelResourceLocation("darkutils:feeder_full", "inventory"));
        
        for (int meta = 1; meta < 10; meta++)
            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("darkutils:feeder_partial", "inventory"));
            
        registerSneakyModel(ContentHandler.blockSneakyBlock, "sneaky_default", false);
        registerSneakyModel(ContentHandler.blockSneakyLever, "sneaky_lever", false);
        registerSneakyModel(ContentHandler.blockSneakyGhost, "sneaky_default", true);
        registerSneakyModel(ContentHandler.blockSneakyTorch, "sneaky_torch", false);
        registerSneakyModel(ContentHandler.blockSneakyObsidian, "sneaky_default", true);
        registerSneakyModel(ContentHandler.blockSneakyPlate, "sneaky_plate", false);
        
        AddonHandler.onClientPreInit();
    }
    
    @Override
    public void onInit () {
        
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new PotionColorHandler(), ContentHandler.itemPotion);
    }
    
    @Override
    public void onPostInit () {
    
    }
    
    private void registerSneakyModel (Block block, String name, boolean useDefault) {
        
        final Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:" + name, "inventory"));
        
        if (!useDefault)
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("darkutils:" + name, "normal"));
            
        ModelLoader.setCustomStateMapper(block, SNEAKY_MAP);
    }
}