/*
    AdminChannel Plugin for Minecraft Bukkit Servers
    Copyright (C) 2014 Kyle Smith

    This file is part of ChunkClaim.

    ChunkClaim is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ChunkClaim is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ChunkClaim.  If not, see <http://www.gnu.org/licenses/>.

    Contact: Kyle Smith <kMaiSmith@gmail.com>
 */

package com.kmaismith.adminchannel;

import junit.framework.Assert;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatHandlerTest {

    private ChatHandler systemUnderTest;
    private Player admin1, admin2, nonAdmin;
    private Set<Player> allPlayers;

    @Before
    public void setupTestCase() {
        Set<Player> admins = new HashSet<Player>();
        allPlayers = new HashSet<Player>();
        admin1 = mock(Player.class);
        when(admin1.getDisplayName()).thenReturn("Admin1");
        admin2 = mock(Player.class);
        nonAdmin = mock(Player.class);

        admins.add(admin1);
        admins.add(admin2);

        allPlayers.add(admin1);
        allPlayers.add(admin2);
        allPlayers.add(nonAdmin);

        systemUnderTest = new ChatHandler(admins);
    }

    @Test
    public void testChatHandlerSendsAdminChatsToAdmins() {
        AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(false, admin1, "Hello admins!", allPlayers);

        systemUnderTest.onAsyncPlayerChat(event);

        Assert.assertFalse(event.isCancelled());
        Assert.assertFalse(allPlayers.contains(nonAdmin));
        Assert.assertTrue(allPlayers.contains(admin1));
        Assert.assertTrue(allPlayers.contains(admin2));
    }

    @Test
    public void testChatHandlerSendsNormalChatsToEveryone() {
        AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(false, nonAdmin, "Hello All!", allPlayers);

        systemUnderTest.onAsyncPlayerChat(event);

        Assert.assertFalse(event.isCancelled());
        Assert.assertTrue(allPlayers.contains(nonAdmin));
        Assert.assertTrue(allPlayers.contains(admin1));
        Assert.assertTrue(allPlayers.contains(admin2));
    }

    @Test
    public void testChatHandlerFormatsTheAdminChatsSoThatTheyAreDistinguishable() {
        AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(false, admin1, "Hello Admins!", allPlayers);

        systemUnderTest.onAsyncPlayerChat(event);

        Assert.assertEquals(event.getFormat(), "[AdminChat]<Admin1> Hello Admins!");
    }
}
