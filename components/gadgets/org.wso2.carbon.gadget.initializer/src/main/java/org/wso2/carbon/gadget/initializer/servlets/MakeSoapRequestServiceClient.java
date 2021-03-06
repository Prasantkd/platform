/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.gadget.initializer.servlets;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.wso2.carbon.gadget.initializer.GadgetInitializerException;

import javax.xml.stream.XMLStreamException;

/**
 * The client is used by the MakeSOAPRequestServlet do service calls
 */
public class MakeSoapRequestServiceClient {

    private ServiceClient serviceClient;

    public MakeSoapRequestServiceClient(ConfigurationContext cfgCtx) throws GadgetInitializerException {
        try {
            serviceClient = new ServiceClient(cfgCtx, null);
        } catch (AxisFault axisFault) {
            throw new GadgetInitializerException("Error while initializing the MakeSoapRequest service client", axisFault);
        }
    }

    public OMElement makeRequest(String endPointURL, String operation, String xmlPayload) throws GadgetInitializerException {
        // Transforming the payload and making the request to the actual service endpoint.
        try {
            Options opts = new Options();
            opts.setTo(new EndpointReference(endPointURL));
            opts.setAction(operation);
            serviceClient.setOptions(opts);

            OMElement payload = AXIOMUtil.stringToOM(xmlPayload);

            OMElement response = serviceClient.sendReceive(payload);
            serviceClient.cleanupTransport();
            return response;

        } catch (XMLStreamException e) {
            throw new GadgetInitializerException("An error occurred while relaying payload.", e);
        } catch (AxisFault axisFault) {
            throw new GadgetInitializerException("An error occurred while relaying payload.", axisFault);
        }
    }
}
