/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.test.management.agents;

import static org.mule.test.management.agents.DefaultJmxSupportAgentTestCase.doTestHostPropertyEnablesClientSocketFactory;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

import org.junit.Test;

public class DefaultJmxSupportAgentDefaultDomainTestCase extends MuleArtifactFunctionalTestCase {

  @Override
  protected String getConfigFile() {
    return "agent/jmx-agent-app-config.xml";
  }

  @Test
  public void testHostPropertyEnablesClientSocketFactory() throws Exception {
    doTestHostPropertyEnablesClientSocketFactory(muleContext);
  }
}