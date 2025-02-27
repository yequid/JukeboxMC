package org.jukeboxmc;

import lombok.Getter;
import lombok.Setter;
import org.jukeboxmc.network.handler.PacketHandler;
import org.jukeboxmc.network.packet.Packet;
import org.jukeboxmc.network.packet.PacketRegistry;
import org.jukeboxmc.network.packet.PlayerListPacket;
import org.jukeboxmc.network.raknet.Connection;
import org.jukeboxmc.network.raknet.Listener;
import org.jukeboxmc.network.raknet.event.intern.PlayerCloseConnectionEvent;
import org.jukeboxmc.network.raknet.event.intern.PlayerConnectionSuccessEvent;
import org.jukeboxmc.network.raknet.event.intern.ReciveMinecraftPacketEvent;
import org.jukeboxmc.player.GameMode;
import org.jukeboxmc.player.Player;
import org.jukeboxmc.world.World;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class Server {

    @Getter
    @Setter
    private static Server instance;

    private InetSocketAddress address;
    private Listener listener;

    private World defaultWorld;

    private int viewDistance = 32;

    private boolean isShutdown = false;

    private Map<InetSocketAddress, Player> players = new HashMap<>();
    private Map<UUID, PlayerListPacket.Entry> playerListEntry = new HashMap<>();

    public Server( InetSocketAddress address ) {
        Server.setInstance( this );

        this.address = address;

        this.listener = new Listener();
        if ( !this.listener.listen( address ) ) {
            System.out.println( "Der Server konnte nicht starten, läuft er bereits auf dem gleichen Port?" );
            return;
        }

        this.listener.getRakNetEventManager().onEvent( ReciveMinecraftPacketEvent.class, (Consumer<ReciveMinecraftPacketEvent>) event -> {
            Connection connection = event.getConnection();
            Packet packet = event.getPacket();
            Player player = this.players.get( connection.getSender() );
            PacketHandler handler = PacketRegistry.getHandler( packet.getClass() );
            if ( handler != null ) {
                handler.handle( packet, player );
            } else {
                System.out.println( "Handler for packet: " + packet.getClass().getSimpleName() + " is missing");
            }
         } );

        this.listener.getRakNetEventManager().onEvent( PlayerConnectionSuccessEvent.class, (Consumer<PlayerConnectionSuccessEvent>) event -> {
            Connection connection = event.getConnection();

            Player player = new Player( this, connection );
            this.players.put( player.getAddress(), player );
            this.setOnlinePlayers( this.players.size() );
        } );

        this.listener.getRakNetEventManager().onEvent( PlayerCloseConnectionEvent.class, (Consumer<PlayerCloseConnectionEvent>) event -> {
            Connection connection = event.getConnection();
            Player player = this.players.get( connection.getSender() );
            if ( player != null ) {
                player.getPlayerConnection().leaveGame();
            }
        } );

        //Load worlds
        this.defaultWorld = this.getWorld( "world" );

        AtomicLong startTime = new AtomicLong();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate( () -> {
            try {
                this.defaultWorld.update( startTime.getAndIncrement() );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }, 0, 50, TimeUnit.MILLISECONDS );
    }

    public void setOnlinePlayers( int onlinePlayers ) {
        this.listener.getServerInfo().setOnlinePlayers( onlinePlayers );
    }

    public void setMaxPlayers( int maxPlayers ) {
        this.listener.getServerInfo().setMaxPlayers( maxPlayers );
    }

    public void setMotd( String motd ) {
        this.listener.getServerInfo().setMotd( motd );
    }

    public void setDefaultGamemode( GameMode defaultGamemode ) {
        this.listener.getServerInfo().setGameMode( defaultGamemode );
    }

    public GameMode getDefaultGamemode() {
        return this.listener.getServerInfo().getGameMode();
    }

    public String getMotd() {
        return this.listener.getServerInfo().getMotd();
    }

    public int getPort() {
        return this.address.getPort();
    }

    public String getHostname() {
        return this.address.getHostName();
    }

    public World getWorld( String name ) {
        return new World( name );
    }

    public World getDefaultWorld() {
        return this.defaultWorld;
    }

    public void setViewDistance( int viewDistance ) {
        this.viewDistance = viewDistance;
    }

    public int getViewDistance() {
        return this.viewDistance;
    }

    public boolean isShutdown() {
        return this.isShutdown;
    }

    public void shutdown() {
        this.isShutdown = true;
    }

    public Collection<Player> getOnlinePlayers() {
        return this.players.values();
    }

    public void removePlayer( InetSocketAddress address ) {
        this.players.remove( address );
    }

    public Map<UUID, PlayerListPacket.Entry> getPlayerListEntry() {
        return this.playerListEntry;
    }

    public void setPlayerListEntry( Map<UUID, PlayerListPacket.Entry> playerListEntry ) {
        this.playerListEntry = playerListEntry;
    }

    public void broadcastMessage( String message ) {
        for ( Player onlinePlayers : this.players.values() ) {
            onlinePlayers.sendMessage( message );
        }
        System.out.println( "[Broadcast] " + message );
    }

    public void broadcastPacket( Packet packet ) {
        for ( Player onlinePlayers : this.players.values() ) {
            onlinePlayers.getPlayerConnection().sendPacket( packet );
        }
    }

}
