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

import de.carne.util.ApplicationManifestInfo;

/**
 * Test {@link ApplicationManifestInfo} class.
 */
public class ApplicationManifestInfoTest {

	// As defined in our test manifest
	private static final String EXPECTED_APPLICATION_NAME = "Test Application";
	private static final String EXPECTED_APPLICATION_VERSION = "1.0-test";
	private static final String EXPECTED_APPLICATION_BUILD = "<undefined>";

	/**
	 * Test {@link ApplicationManifestInfo} attributes.
	 */
	@Test
	public void testInfos() {
		Assert.assertEquals(EXPECTED_APPLICATION_NAME, ApplicationManifestInfo.APPLICATION_NAME);
		Assert.assertEquals(EXPECTED_APPLICATION_VERSION, ApplicationManifestInfo.APPLICATION_VERSION);
		Assert.assertEquals(EXPECTED_APPLICATION_BUILD, ApplicationManifestInfo.APPLICATION_BUILD);
	}

}