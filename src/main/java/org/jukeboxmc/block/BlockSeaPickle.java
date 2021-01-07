package org.jukeboxmc.block;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockSeaPickle extends Block {

    public BlockSeaPickle() {
        super( "minecraft:sea_pickle" );
    }

    public void clusterCount( int value ) { //0-3
        this.setState( "cluster_count", value );
    }

    public int getClusterCount() {
        return this.stateExists( "cluster_count" ) ? this.getIntState( "cluster_count" ) : 0;
    }

    public void setDead( boolean value ) {
        this.setState( "dead_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isDead() {
        return this.stateExists( "dead_bit" ) && this.getByteState( "dead_bit" ) == 1;
    }
}
