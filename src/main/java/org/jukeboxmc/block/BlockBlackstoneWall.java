package org.jukeboxmc.block;

public class BlockBlackstoneWall extends Block {

    public BlockBlackstoneWall() {
        super("minecraft:blackstone_wall");
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

    public enum WallConnectionType {
        NONE,
        SHORT,
        TALL
    }
}