package org.jukeboxmc.item;

import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockAcaciaButton;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ItemAcaciaButton extends Item {

    public ItemAcaciaButton() {
        super( "minecraft:acacia_button", -140 );
    }

    @Override
    public Block getBlock() {
        return new BlockAcaciaButton();
    }
}
