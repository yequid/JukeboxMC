package org.jukeboxmc.block;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockTripwire extends Block {

    public BlockTripwire() {
        super( "minecraft:tripwire" );
    }

    public void setPowered( boolean value ) {
        this.setState( "powered_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isPowered() {
        return this.stateExists( "powered_bit" ) && this.getByteState( "powered_bit" ) == 1;
    }

    public void setSuspended( boolean value ) {
        this.setState( "suspended_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isSuspended() {
        return this.stateExists( "suspended_bit" ) && this.getByteState( "suspended_bit" ) == 1;
    }

    public void setDisarmed( boolean value ) {
        this.setState( "disarmed_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isDisarmed() {
        return this.stateExists( "disarmed_bit" ) && this.getByteState( "disarmed_bit" ) == 1;
    }

    public void setAttached( boolean value ) {
        this.setState( "attached_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isAttached() {
        return this.stateExists( "attached_bit" ) && this.getByteState( "attached_bit" ) == 1;
    }
}
