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
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kyle
 * Date: 3/3/14
 * Time: 8:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminChannelTest {

    @Test
    public void testAdminChannelIsAClass() {
        AdminChannel systemUnderTest = new AdminChannel();
        Assert.assertTrue(systemUnderTest instanceof JavaPlugin);
    }
}
