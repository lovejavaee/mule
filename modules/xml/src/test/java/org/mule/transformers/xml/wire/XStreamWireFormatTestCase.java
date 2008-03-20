/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transformers.xml.wire;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.wire.WireFormat;
import org.mule.module.xml.transformer.ObjectToXml;
import org.mule.module.xml.transformer.XmlToObject;
import org.mule.module.xml.transformer.wire.XStreamWireFormat;
import org.mule.tck.testmodels.fruit.Orange;
import org.mule.transformer.wire.AbstractMuleMessageWireFormatTestCase;

import java.util.Properties;

public class XStreamWireFormatTestCase extends AbstractMuleMessageWireFormatTestCase
{

    protected WireFormat getWireFormat() throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        return new XStreamWireFormat();
    }

    public void testGetDefaultInboundTransformer()
        throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        assertEquals(XmlToObject.class, ((XStreamWireFormat) getWireFormat()).getInboundTransformer().getClass());

    }

    public void testGetDefaultOutboundTransformer()
        throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        assertEquals(ObjectToXml.class, ((XStreamWireFormat) getWireFormat()).getOutboundTransformer().getClass());
    }

    public void testWriteReadPayload() throws Exception
    {
        // Create orange to send over the wire
        Properties messageProerties = new Properties();
        messageProerties.put("key1", "val1");
        Orange inOrange = new Orange();
        inOrange.setBrand("Walmart");
        inOrange.setMapProperties(messageProerties);

        Object outObject = readWrite(inOrange);

        // Test deserialized Fruit
        // TODO This wire-format wraps desrialized payloads in a message. See
        // MULE-3118
        // See test implementation in AbstractMuleMessageWireFormatTestCase.
        assertTrue(outObject instanceof MuleMessage);
        assertEquals("Walmart", ((Orange) ((MuleMessage) outObject).getPayload()).getBrand());
        assertEquals("val1", ((Orange) ((MuleMessage) outObject).getPayload()).getMapProperties().get("key1"));
    }

}
