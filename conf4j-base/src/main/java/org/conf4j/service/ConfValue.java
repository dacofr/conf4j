/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.conf4j.service;

import java.util.Arrays;
import java.util.List;

import org.conf4j.EScope;

final class ConfValue {
    private String value;
    private String expandedValue;
    private final ESource source;
    private final String description;
    private final List<EScope> scopes;
    private final boolean devPurposeOnly;
    private int accessCount = 0;

    public enum ESource {
        DEFAULT, OS_PROPERTY, CONFIG_FILE, INSTANCE_FILE, JVM_PROPERTY, CUSTOM, DB;
    }

    public ConfValue(ESource source) {
        this(null, source, null, Arrays.asList(new EScope[] { EScope.undefined }), false);
    }

    public ConfValue(String value, ESource source) {
        this(value, source, null, Arrays.asList(new EScope[] { EScope.undefined }), false);
    }

    public ConfValue(String value, ESource source, String description, List<EScope> scopes, boolean devPurposeOnly) {
        this.value = value;
        this.source = source;
        this.description = description;
        this.scopes = scopes;
        this.devPurposeOnly = devPurposeOnly;
    }

    public String getValue() {
        return getValue(true);
    }

    String getValue(boolean countAccess) {
        try {
            return value == null ? "" : value;
        } finally {
            if (countAccess)
                accessCount++;
        }
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ESource getSource() {
        return source;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public boolean isExpandable() {
        if ((value == null) || (value.length() == 0))
            return false;
        if (expandedValue == null)
            expandedValue = ConfServiceInstance.CONF.getValue(value);
        return !value.equals(expandedValue);
    }

    public List<EScope> getScopes() {
        return scopes;
    }

    public boolean isDevPurposeOnly() {
        return devPurposeOnly;
    }
}
