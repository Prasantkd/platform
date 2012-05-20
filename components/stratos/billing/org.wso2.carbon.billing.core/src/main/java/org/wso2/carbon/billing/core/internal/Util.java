/*
 *  Copyright (c) 2005-2008, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.billing.core.internal;

import org.osgi.framework.BundleContext;
import org.wso2.carbon.billing.core.BillingConstants;
import org.wso2.carbon.billing.core.BillingException;
import org.wso2.carbon.billing.core.BillingManager;
import org.wso2.carbon.billing.core.conf.BillingConfiguration;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.carbon.rule.kernel.config.RuleEngineConfigService;

import java.io.File;

public class Util {
    private static RegistryService registryService;
    private static RealmService realmService;
    private static RuleEngineConfigService ruleEngineConfigService;

    public static synchronized void setRegistryService(RegistryService service) {
        if (registryService == null) {
            registryService = service;
        }
    }

    public static synchronized void setRealmService(RealmService service) {
        if (realmService == null) {
            realmService = service;
        }
    }

    public static RealmService getRealmService(){
        return Util.realmService;
    }

    public static void setRuleEngineConfigService(RuleEngineConfigService ruleServerManagerService) {
        Util.ruleEngineConfigService = ruleServerManagerService;
    }

    public static RuleEngineConfigService getRuleEngineConfigService() {
        return Util.ruleEngineConfigService;
    }

    public static void initBillingManager(BundleContext bundleContext) throws BillingException {
        // load the configuration and initialize the billing engine + do the
        // necessary scheduling.
        String configFile =
                CarbonUtils.getCarbonConfigDirPath() + File.separator + BillingConstants.BILLING_CONFIG;
        BillingConfiguration billingConfiguration = new BillingConfiguration(configFile);
        BillingManager billingManager = new BillingManager(billingConfiguration);

        bundleContext.registerService(BillingManager.class.getName(), billingManager, null);
    }

    public static void cleanBillingManager() {
        BillingManager.destroyInstance();
    }
}
