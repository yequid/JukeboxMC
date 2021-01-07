package org.jukeboxmc.block;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockCobblestoneWall extends Block {

    public BlockCobblestoneWall() {
        super( "minecraft:cobblestone_wall" );
    }

    public void setWallPost( boolean value ) {
        this.setState( "wall_post_bit", value ? (byte) 1 : (byte) 0 );
    }

    public boolean isWallPost() {
        return this.stateExists( "wall_post_bit" ) && this.getByteState( "wall_post_bit" ) == 1;
    }

    public void setWallConnectionTypeEast( WallConnectionType wallConnectionTypeEast ) {
        this.setState( "wall_connection_type_east", wallConnectionTypeEast.name().toLowerCase() );
    }

    public WallConnectionType getWallConnectionTypeEast() {
        return this.stateExists( "wall_connection_type_east" ) ? WallConnectionType.valueOf( this.getStringState( "wall_connection_type_east" ).toUpperCase() ) : WallConnectionType.NONE;
    }

    public void setWallConnectionTypeSouth( WallConnectionType wallConnectionTypeEast ) {
        this.setState( "wall_connection_type_south", wallConnectionTypeEast.name().toLowerCase() );
    }

    public WallConnectionType getWallConnectionTypeSouth() {
        return this.stateExists( "wall_connection_type_south" ) ? WallConnectionType.valueOf( this.getStringState( "wall_connection_type_south" ).toUpperCase() ) : WallConnectionType.NONE;
    }

    public void setWallConnectionTypeWest( WallConnectionType wallConnectionTypeEast ) {
        this.setState( "wall_connection_type_west", wallConnectionTypeEast.name().toLowerCase() );
    }

    public WallConnectionType getWallConnectionTypeWest() {
        return this.stateExists( "wall_connection_type_west" ) ? WallConnectionType.valueOf( this.getStringState( "wall_connection_type_west" ).toUpperCase() ) : WallConnectionType.NONE;
    }

    public void setWallConnectionTypeNorth( WallConnectionType wallConnectionTypeEast ) {
        this.setState( "wall_connection_type_north", wallConnectionTypeEast.name().toLowerCase() );
    }

    public WallConnectionType getWallConnectionTypeNorth() {
        return this.stateExists( "wall_connection_type_north" ) ? WallConnectionType.valueOf( this.getStringState( "wall_connection_type_north" ).toUpperCase() ) : WallConnectionType.NONE;
    }

    public void setWallBlockType( WallBlockType wallBlockType ) {
        this.setState( "wall_block_type", wallBlockType.name().toLowerCase() );
    }

    public WallBlockType getWallConnectionType() {
        return this.stateExists( "wall_block_type" ) ? WallBlockType.valueOf( this.getStringState( "wall_block_type" ).toUpperCase() ) : WallBlockType.COBBLESTONE;
    }

    public enum WallConnectionType {
        NONE,
        SHORT,
        TALL
    }

    public enum WallBlockType {
        COBBLESTONE,
        MOSSY_COBBLESTONE,
        GRANITE,
        DIORITE,
        ANDESITE,
        SANDSTONE,
        BRICK,
        STONE_BRICK,
        MOSSY_STONE_BRICK,
        NETHER_BRICK,
        END_BRICK,
        PRISMARINE,
        RED_SANDSTONE,
        RED_NETHER_BRICK
    }
}
