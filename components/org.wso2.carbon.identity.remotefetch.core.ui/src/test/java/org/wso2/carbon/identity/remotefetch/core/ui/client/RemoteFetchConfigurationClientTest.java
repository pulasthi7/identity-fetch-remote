/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.remotefetch.core.ui.client;

import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.remotefetch.common.BasicRemoteFetchConfiguration;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchComponentRegistry;

import org.wso2.carbon.identity.remotefetch.common.actionlistener.ActionListenerComponent;
import org.wso2.carbon.identity.remotefetch.common.configdeployer.ConfigDeployerComponent;
import org.wso2.carbon.identity.remotefetch.common.repomanager.RepositoryManagerComponent;
import org.wso2.carbon.identity.remotefetch.core.RemoteFetchComponentRegistryImpl;
import org.wso2.carbon.identity.remotefetch.core.impl.deployers.config.ServiceProviderConfigDeployerComponent;
import org.wso2.carbon.identity.remotefetch.core.impl.handlers.action.polling.PollingActionListenerComponent;
import org.wso2.carbon.identity.remotefetch.core.impl.handlers.repository.GitRepositoryManagerComponent;
import org.wso2.carbon.identity.remotefetch.core.ui.dto.RemoteFetchConfigurationRowDTO;
import org.wso2.carbon.identity.remotefetch.core.ui.internal.RemotefetchCoreUIComponentDataHolder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Unit test covering for RemoteFetchConfigurationClient.
 */
public class RemoteFetchConfigurationClientTest extends PowerMockTestCase {

    private static final String REMOTE_FETCH_ID = "00000000-0000-0000-0000-d29bed62f7bd";
    private static final String REPOSITORY_MANAGER_TYPE = "GIT";
    private static final String ACTION_LISTENER_TYPE = "POLLING";
    private static final String CONFIG_DEPLOYER_TYPE = "SP";
    private static final String REMOTE_FETCH_NAME = "TEST";

    @ObjectFactory
    public IObjectFactory getObjectFactory() {

        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    @Test
    public void testFetchConfigurationToDTO() {

        BasicRemoteFetchConfiguration basicRemoteFetchConfiguration = new BasicRemoteFetchConfiguration
                (REMOTE_FETCH_ID, true, REPOSITORY_MANAGER_TYPE, ACTION_LISTENER_TYPE, CONFIG_DEPLOYER_TYPE,
                        REMOTE_FETCH_NAME, 1, 0);
        RemoteFetchComponentRegistry remoteFetchComponentRegistry = new RemoteFetchComponentRegistryImpl();
        ActionListenerComponent actionListenerComponent = new PollingActionListenerComponent();
        ConfigDeployerComponent configDeployerComponent = new ServiceProviderConfigDeployerComponent();
        RepositoryManagerComponent repositoryManagerComponent = new GitRepositoryManagerComponent();
        remoteFetchComponentRegistry.registerRepositoryManager(repositoryManagerComponent);
        remoteFetchComponentRegistry.registerConfigDeployer(configDeployerComponent);
        remoteFetchComponentRegistry.registerActionListener(actionListenerComponent);
        RemotefetchCoreUIComponentDataHolder.getInstance().setComponentRegistry(remoteFetchComponentRegistry);
        RemoteFetchConfigurationRowDTO remoteFetchConfigurationRowDTO =
                RemoteFetchConfigurationClient.fetchConfigurationToDTO(basicRemoteFetchConfiguration);
        assertNotNull(remoteFetchConfigurationRowDTO);
        assertTrue(remoteFetchConfigurationRowDTO.getIsEnabled());
        assertEquals(remoteFetchConfigurationRowDTO.getRemoteFetchName(), REMOTE_FETCH_NAME);
    }

    @Test
    public void testFetchConfigurationToDTOForNullValues() {

        BasicRemoteFetchConfiguration basicRemoteFetchConfiguration = new BasicRemoteFetchConfiguration
                (REMOTE_FETCH_ID, true, REPOSITORY_MANAGER_TYPE, ACTION_LISTENER_TYPE, CONFIG_DEPLOYER_TYPE,
                        REMOTE_FETCH_NAME, 1, 0);
        RemoteFetchComponentRegistry remoteFetchComponentRegistry = new RemoteFetchComponentRegistryImpl();
        RemotefetchCoreUIComponentDataHolder.getInstance().setComponentRegistry(remoteFetchComponentRegistry);
        RemoteFetchConfigurationRowDTO remoteFetchConfigurationRowDTO =
                RemoteFetchConfigurationClient.fetchConfigurationToDTO(basicRemoteFetchConfiguration);
        assertNotNull(remoteFetchConfigurationRowDTO);
    }
}
