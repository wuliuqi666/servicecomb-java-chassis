/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.servicecomb.config.collect.ConfigCenterDefaultDeploymentProvider;
import org.apache.servicecomb.deployment.Deployment;
import org.apache.servicecomb.foundation.vertx.VertxConst;

import com.netflix.config.ConcurrentCompositeConfiguration;

public final class ConfigCenterConfig {
  public static final ConfigCenterConfig INSTANCE = new ConfigCenterConfig();

  public static final String SSL_TAG = "cc.consumer";

  private static ConcurrentCompositeConfiguration finalConfig;

  private static final String AUTO_DISCOVERY_ENABLED = "servicecomb.service.registry.autodiscovery";

  private static final String REFRESH_MODE = "servicecomb.config.client.refreshMode";

  private static final String REFRESH_PORT = "servicecomb.config.client.refreshPort";

  private static final String TENANT_NAME = "servicecomb.config.client.tenantName";

  private static final String DOMAIN_NAME = "servicecomb.config.client.domainName";

  private static final String TOKEN_NAME = "servicecomb.config.client.token";

  private static final String URI_API_VERSION = "servicecomb.config.client.api.version";

  private static final String REFRESH_INTERVAL = "servicecomb.config.client.refresh_interval";

  private static final String FIRST_REFRESH_INTERVAL = "servicecomb.config.client.first_refresh_interval";

  private static final String FIRST_PULL_REQUIRED = "servicecomb.config.client.firstPullRequired";

  public static final String CONNECTION_TIME_OUT = "servicecomb.config.client.timeout.connection";

  public static final String EVENT_LOOP_SIZE = "servicecomb.config.client.eventLoopSize";

  public static final String FILE_SOURCE = "servicecomb.config.client.fileSource";

  public static final String VERTICAL_INSTANCE_COUNT = "servicecomb.config.client.verticalInstanceCount";

  public static final String IDLE_TIMEOUT_IN_SECONDES = "servicecomb.config.client.idleTimeoutInSeconds";

  private static final int DEFAULT_REFRESH_MODE = 0;

  private static final int DEFAULT_REFRESH_PORT = 30104;

  private static final int DEFAULT_REFRESH_INTERVAL = 30000;

  private static final int DEFAULT_FIRST_REFRESH_INTERVAL = 0;

  private ConfigCenterConfig() {
  }

  public static void setConcurrentCompositeConfiguration(ConcurrentCompositeConfiguration config) {
    finalConfig = config;
  }

  public static ConcurrentCompositeConfiguration getConcurrentCompositeConfiguration() {
    return finalConfig;
  }

  public int getRefreshMode() {
    return finalConfig.getInt(REFRESH_MODE, DEFAULT_REFRESH_MODE);
  }

  public int getRefreshPort() {
    return finalConfig.getInt(REFRESH_PORT, DEFAULT_REFRESH_PORT);
  }

  public String getTenantName() {
    return finalConfig.getString(TENANT_NAME, "default");
  }

  public String getDomainName() {
    return finalConfig.getString(DOMAIN_NAME, "default");
  }

  public String getToken() {
    return finalConfig.getString(TOKEN_NAME, null);
  }

  public String getApiVersion() {
    return finalConfig.getString(URI_API_VERSION, "v3");
  }

  public boolean firstPullRequired() {
    return finalConfig.getBoolean(FIRST_PULL_REQUIRED, false);
  }

  @SuppressWarnings("unchecked")
  public List<String> getFileSources() {
    Object property = finalConfig.getProperty(FILE_SOURCE);
    if (property instanceof String) {
      List<String> result = new ArrayList<>();
      result.add((String) property);
      return result;
    }
    if (property instanceof List) {
      return (List<String>) property;
    }
    return Collections.EMPTY_LIST;
  }

  public int getRefreshInterval() {
    return finalConfig.getInt(REFRESH_INTERVAL, DEFAULT_REFRESH_INTERVAL);
  }

  public int getFirstRefreshInterval() {
    return finalConfig.getInt(FIRST_REFRESH_INTERVAL, DEFAULT_FIRST_REFRESH_INTERVAL);
  }

  public Boolean isProxyEnable() {
    return finalConfig.getBoolean(VertxConst.PROXY_ENABLE, false);
  }

  public String getProxyHost() {
    return finalConfig.getString(VertxConst.PROXY_HOST, "127.0.0.1");
  }

  public int getProxyPort() {
    return finalConfig.getInt(VertxConst.PROXY_PORT, 8080);
  }

  public String getProxyUsername() {
    return finalConfig.getString(VertxConst.PROXY_USERNAME, null);
  }

  public String getProxyPasswd() {
    return finalConfig.getString(VertxConst.PROXY_PASSWD, null);
  }

  public String getServiceName() {
    return BootStrapProperties.readServiceName(finalConfig);
  }

  public String getAppName() {
    return BootStrapProperties.readApplication(finalConfig);
  }

  public String getServiceVersion() {
    return BootStrapProperties.readServiceVersion(finalConfig);
  }

  public List<String> getServerUri() {
    return Deployment.getSystemBootStrapInfo(ConfigCenterDefaultDeploymentProvider.SYSTEM_KEY_CONFIG_CENTER)
        .getAccessURL();
  }

  public boolean getAutoDiscoveryEnabled() {
    return finalConfig.getBoolean(AUTO_DISCOVERY_ENABLED, false);
  }

  public int getConnectionTimeout() {
    return finalConfig.getInt(CONNECTION_TIME_OUT, 1000);
  }

  public int getEventLoopSize() {
    return finalConfig.getInt(EVENT_LOOP_SIZE, 2);
  }

  public int getVerticalInstanceCount() {
    return finalConfig.getInt(VERTICAL_INSTANCE_COUNT, 1);
  }

  public int getIdleTimeoutInSeconds() {
    return finalConfig.getInt(IDLE_TIMEOUT_IN_SECONDES, 60);
  }

  public String getEnvironment() {
    return BootStrapProperties.readServiceEnvironment(finalConfig);
  }
}
