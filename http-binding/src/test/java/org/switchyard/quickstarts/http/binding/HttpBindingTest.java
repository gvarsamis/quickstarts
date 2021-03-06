/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */

package org.switchyard.quickstarts.http.binding;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;
import org.switchyard.test.mixins.CDIMixIn;
import org.switchyard.test.mixins.HTTPMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

/**
 * Tests for Camel CXFRS binding.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML, 
        mixins = {CDIMixIn.class, HTTPMixIn.class},
        scanners = {BeanSwitchYardScanner.class, TransformSwitchYardScanner.class})
@RunWith(SwitchYardRunner.class)
public class HttpBindingTest {

    private HTTPMixIn http;

    /**
     * Ignore until this is fixed http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7189193
     */
    @Ignore
    @Test
    public void stockServiceEndpoint() throws Exception {
        String response = null;
        http.setContentType("text/plain");
        response = http.sendString(BASE_URL + "/quote", "vineyard", HTTPMixIn.HTTP_POST);
        Assert.assertEquals("136.5", response);
    }

    @Test
    public void symbolServiceEndpoint() throws Exception {
        String response = null;
        response = http.sendString(BASE_URL + "/quote", "rum", HTTPMixIn.HTTP_POST);
        Assert.assertEquals("0.0", response);
        http.setContentType("text/plain");
        response = http.sendString(BASE_URL + "/symbol", "vineyard", HTTPMixIn.HTTP_POST);
        Assert.assertEquals("WINE", response);
        response = http.sendString(BASE_URL + "/symbol", "rum", HTTPMixIn.HTTP_POST);
        Assert.assertEquals("", response);
    }

    private static final String BASE_URL = "http://localhost:8080/http-binding";
}
