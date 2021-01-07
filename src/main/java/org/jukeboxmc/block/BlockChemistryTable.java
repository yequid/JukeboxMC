package org.jukeboxmc.block;

import org.jukeboxmc.block.direction.Direction;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockChemistryTable extends Block {

    public BlockChemistryTable() {
        super( "minecraft:chemistry_table" );
    }

    public void setChemistryTableType( ChemistryTableType chemistryTableType ) {
        this.setState( "chemistry_table_type", chemistryTableType.name().toLowerCase() );
    }

    public ChemistryTableType getChemistryTableType() {
        return this.stateExists( "chemistry_table_type" ) ? ChemistryTableType.valueOf( this.getStringState( "chemistry_table_type" ).toUpperCase() ) : ChemistryTableType.COMPUND_CREATOR;
    }

    public void setDirection( Direction direction ) {
        switch ( direction ) {
            case SOUTH:
                this.setState( "direction", 0 );
                break;
            case WEST:
                this.setState( "direction", 1 );
                break;
            case NORTH:
                this.setState( "direction", 2 );
                break;
            case EAST:
                this.setState( "direction", 3 );
                break;
        }
    }

    public Direction getDirection() {
        int value = this.stateExists( "direction" ) ? this.getIntState( "direction" ) : 0;
        switch ( value ) {
            case 0:
                return Direction.SOUTH;
            case 1:
                return Direction.WEST;
            case 2:
                return Direction.NORTH;
            default:
                return Direction.EAST;
        }
    }

    public enum ChemistryTableType {
        COMPUND_CREATOR,
        MATERIAL_REDUCER,
        ELEMENT_CONSTRUCTOR,
        LAB_TABLE
    }
}
