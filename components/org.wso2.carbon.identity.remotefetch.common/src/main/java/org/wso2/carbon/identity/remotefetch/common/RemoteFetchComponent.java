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

package org.wso2.carbon.identity.remotefetch.common;

import org.wso2.carbon.identity.remotefetch.common.ui.UIField;

import java.util.List;

/**
 * Interface to implement to common gunctionalities of the component.
 */
public interface RemoteFetchComponent {

    /**
     * Returns a string of the unique identifier of the component.
     *
     * @return ID
     */
    String getIdentifier();

    /**
     * Returns screen name of the component.
     *
     * @return Name
     */
    String getName();

    /**
     * Returns list of UIFields to configure UI.
     *
     * @return List of UI fields.
     */
    List<UIField> getUIFields();
}
