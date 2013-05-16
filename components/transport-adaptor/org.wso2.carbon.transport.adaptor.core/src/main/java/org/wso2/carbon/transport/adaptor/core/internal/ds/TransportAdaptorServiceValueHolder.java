/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.transport.adaptor.core.internal.ds;

import org.wso2.carbon.transport.adaptor.core.TransportAdaptorRegistrationService;
import org.wso2.carbon.transport.adaptor.core.TransportAdaptorService;

/**
 * common place to hold some OSGI bundle references.
 */
public final class TransportAdaptorServiceValueHolder {

    private static TransportAdaptorService carbonTransportAdaptorService;
    private static TransportAdaptorRegistrationService transportAdaptorRegistrationService;

    private TransportAdaptorServiceValueHolder() {
    }

    public static void registerCarbonTransportService(
            TransportAdaptorService carbonTransportAdaptorService) {

        TransportAdaptorServiceValueHolder.carbonTransportAdaptorService = carbonTransportAdaptorService;
    }

    public static TransportAdaptorService getCarbonTransportAdaptorService() {
        return TransportAdaptorServiceValueHolder.carbonTransportAdaptorService;
    }

    public static void registerTransportAdaptorRegistrationService(
            TransportAdaptorRegistrationService transportAdaptorRegistrationService) {

        TransportAdaptorServiceValueHolder.transportAdaptorRegistrationService = transportAdaptorRegistrationService;
    }

    public static TransportAdaptorRegistrationService getTransportAdaptorRegistrationService() {
        return TransportAdaptorServiceValueHolder.transportAdaptorRegistrationService;
    }


}