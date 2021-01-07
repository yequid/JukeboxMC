package org.jukeboxmc.block;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockHayBlock extends Block {

    public BlockHayBlock() {
        super( "minecraft:hay_block" );
    }

    public void setDeprecated( int value ) { //0-3 Idk what it is
        this.setState( "deprecated", value );
    }

    public int getDeprecated() {
        return this.stateExists( "deprecated" ) ? this.getIntState( "deprecated" ) : 0;
    }

    public void setAxis( Axis axis ) {
        this.setState( "pillar_axis", axis.name().toLowerCase() );
    }

    public Axis getAxis() {
        return this.stateExists( "pillar_axis" ) ? Axis.valueOf( this.getStringState( "pillar_axis" ).toUpperCase() ) : Axis.Y;
    }

    public enum Axis {
        Y,
        X,
        Z
    }
}
