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
package org.wso2.carbon.esb.proxyservice.test.secureProxy;

import org.apache.axiom.om.OMElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.esb.ESBIntegrationTest;
import org.wso2.carbon.esb.util.SecureServiceClient;
import org.wso2.carbon.security.mgt.stub.config.SecurityAdminServiceSecurityConfigExceptionException;

import javax.xml.namespace.QName;
import java.rmi.RemoteException;

public class ProxyServiceSecurityTestCase extends ESBIntegrationTest {

    private final String serviceName = "SecureStockQuoteProxy";

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        super.init();
        loadESBConfigurationFromClasspath("/artifacts/ESB/proxyconfig/proxy/secureProxy/stockquote_pass_through_proxy.xml");

    }

    @AfterClass(alwaysRun = true)
    public void destroy() throws Exception {

        super.cleanup();
    }

    @Test(groups = {"wso2.esb"}, description = "Provides Authentication. Clients have Username Tokens")
    public void securityPolicy1() throws Exception {
        final int policyId = 1;

        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceSecuredURL(serviceName), policyId, "Secured");
            verifyResponse(response);

        }
        log.info("UsernameToken verified");
    }


    @Test(groups = {"wso2.esb"})
    public void securityPolicy2() throws Exception {
        final int policyId = 2;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Non-repudiation verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy3() throws Exception {
        final int policyId = 3;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Integrity verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy4() throws Exception {
        final int policyId = 4;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Confidentiality verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy5() throws Exception {
        final int policyId = 5;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Sign and encrypt - X509 Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy6() throws Exception {
        final int policyId = 6;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Sign and Encrypt - Anonymous clients verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy7() throws Exception {
        final int policyId = 7;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Encrypt only - Username Token Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy8() throws Exception {
        final int policyId = 8;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("Sign and Encrypt - Username Token Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy9() throws Exception {
        final int policyId = 9;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Sign only - Service as STS - Bootstrap policy - Sign and Encrypt ," +
                 " X509 Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy10() throws Exception {
        final int policyId = 10;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Encrypt only - Service as STS - Bootstrap policy - Sign and Encrypt ," +
                 " X509 Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy11() throws Exception {
        final int policyId = 11;
        this.secureService(policyId);
        Thread.sleep(5000);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Sign and Encrypt - Service as STS - Bootstrap policy - Sign and Encrypt , X509 Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy12() throws Exception {
        final int policyId = 12;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Sign Only - Service as STS - Bootstrap policy - Sign and Encrypt ," +
                 " Anonymous clients verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy13() throws Exception {
        final int policyId = 13;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Sign and Encrypt - Service as STS - Bootstrap policy - " +
                 "Sign and Encrypt , Anonymous clients verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy14() throws Exception {
        final int policyId = 14;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Encrypt Only - Service as STS - Bootstrap policy - " +
                 "Sign and Encrypt , Username Token Authentication verified");
    }

    @Test(groups = {"wso2.esb"})
    public void securityPolicy15() throws Exception {
        final int policyId = 15;
        this.secureService(policyId);
        SecureServiceClient secureAxisServiceClient = new SecureServiceClient();
        OMElement response;
        for (int i = 0; i < 5; i++) {
            response = secureAxisServiceClient.sendSecuredStockQuoteRequest(userInfo, getProxyServiceURL(serviceName), policyId, "Secured");
            verifyResponse(response);
        }
        log.info("SecureConversation - Sign and Encrypt - Service as STS - Bootstrap policy - " +
                 "Sign and Encrypt , Username Token Authentication verified");
    }

    //@Test(dependsOnMethods = {"uploadArtifactTest"})
    //    public void securityPolicy16() {
    //        this.secureService(16);
    //        SecureAxisServiceClient secureAxisServiceClient = new SecureAxisServiceClient();
    //        serviceEndPoint = DataServiceUtility.getServiceEndpointHttp(sessionCookie, dssBackEndUrl, serviceName);
    //        OMElement response;
    //        for (int i = 0; i < 5; i++) {
    //            response = secureAxisServiceClient.sendReceive(userInfo.getUserName(), userInfo.getPassword(), serviceEndPoint, "showAllOffices", getPayload(), 16);
    //            Assert.assertTrue("Expected Result not Found", (response.toString().indexOf("<Office>") > 1));
    //            Assert.assertTrue("Expected Result not Found", (response.toString().indexOf("</Office>") > 1));
    //        }
    //    log.info("Kerberos Authentication - Sign - Sign based on a Kerberos Token verified");
    //    }


    private void secureService(int policyId)
            throws SecurityAdminServiceSecurityConfigExceptionException, RemoteException,
                   InterruptedException {

        applySecurity(serviceName, policyId, getUserRole(userInfo.getUserId()));

    }

    private void verifyResponse(OMElement response) {
        String symbol = response.getFirstElement().getFirstChildWithName(
                new QName("http://services.samples/xsd", "symbol", "ax21")).getText();
        Assert.assertEquals(symbol, "Secured", "Symbol name mismatched");
    }

}