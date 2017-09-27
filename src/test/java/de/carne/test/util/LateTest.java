/*
 * Copyright (c) 2016-2017 Holger de Carne and contributors, All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.carne.test.util;

import org.junit.Assert;
import org.junit.Test;

import de.carne.util.Late;

/**
 * Test {@link Late} class.
 */
public class LateTest {

	/**
	 * Test initialized access.
	 */
	@Test
	public void testInitializedAccess() {
		Late<LateTest> late = new Late<>();

		Assert.assertEquals("<not initialized>", late.toString());

		late.set(this);

		Assert.assertEquals(this, late.get());
		Assert.assertEquals(toString(), late.toString());
	}

	/**
	 * Test uninitialized access.
	 */
	@Test(expected = IllegalStateException.class)
	public void testInvalidAccess() {
		Late<LateTest> late = new Late<>();

		Assert.assertEquals("<not initialized>", late.toString());

		late.get();
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}