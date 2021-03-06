package org.wso2.carbon.mediator.tests.cache;
/*
*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

import org.apache.axiom.om.OMElement;
import org.testng.annotations.Test;
import org.wso2.esb.integration.ESBIntegrationTestCase;
import org.wso2.esb.integration.axis2.SampleAxis2Server;
import org.wso2.esb.integration.axis2.StockQuoteClient;

import javax.xml.namespace.QName;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;
/**
 * This class will test cache mediator which has Message_Size with blank value
 * in it's configuration
 */
public class MessageSizeNullTestCase extends ESBIntegrationTestCase {
    private StockQuoteClient axis2Client;

    public void init() throws Exception {
        axis2Client = new StockQuoteClient();
        launchBackendAxis2Service(SampleAxis2Server.SIMPLE_STOCK_QUOTE_SERVICE);
        String filePath = "/mediators/cache/message_size_null.xml";
        loadESBConfigurationFromClasspath(filePath);
    }

    @Test(groups = {"wso2.esb"},expectedExceptions = org.apache.axis2.AxisFault.class)
    public void testMessageSizeProperty() throws RemoteException, InterruptedException {

        OMElement response = axis2Client.sendSimpleStockQuoteRequest(
                null, getMainSequenceURL(), "MSFT");

        String tradingTime = response.getFirstElement().getFirstChildWithName(new QName
                ("http://services.samples/xsd", "lastTradeTimestamp")).getText();

        System.out.println("message  :" + tradingTime);

        if(tradingTime.contains("IST")){
            assertTrue(false, "Response getting while Message size not specified ");
        }

    }

    @Override
    protected void cleanup() {
        super.cleanup();
        axis2Client.destroy();
    }
}
