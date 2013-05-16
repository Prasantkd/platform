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

package org.wso2.carbon.transport.adaptor.core.config;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class contain the configuration details of the transport
 */

public class TransportAdaptorConfiguration implements InputTransportAdaptorConfiguration, OutputTransportAdaptorConfiguration {


    private String name;

    private String type;

    private InternalTransportAdaptorConfiguration inputTransportAdaptorConfiguration = null;

    private InternalTransportAdaptorConfiguration outputTransportAdaptorConfiguration = null;

    private Map<String,String> transportAdaptorCommonProperties = null;

    public InternalTransportAdaptorConfiguration getInputTransportAdaptorConfiguration() {
        return inputTransportAdaptorConfiguration;
    }

    public void setInputTransportAdaptorConfiguration(
            InternalTransportAdaptorConfiguration inputTransportAdaptorConfiguration) {
        this.inputTransportAdaptorConfiguration = inputTransportAdaptorConfiguration;
    }

    public InternalTransportAdaptorConfiguration getOutputTransportAdaptorConfiguration() {
        return outputTransportAdaptorConfiguration;
    }

    public void setOutputTransportAdaptorConfiguration(
            InternalTransportAdaptorConfiguration outputTransportAdaptorConfiguration) {
        this.outputTransportAdaptorConfiguration = outputTransportAdaptorConfiguration;
    }

    public Map<String,String> getTransportAdaptorCommonProperties() {
        return transportAdaptorCommonProperties;
    }

    public void setTransportAdaptorCommonProperties(
            Map<String,String> transportAdaptorCommonProperties) {
        this.transportAdaptorCommonProperties = transportAdaptorCommonProperties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}