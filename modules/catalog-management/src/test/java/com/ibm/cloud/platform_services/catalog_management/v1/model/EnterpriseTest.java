/*
 * (C) Copyright IBM Corp. 2020.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.ibm.cloud.platform_services.catalog_management.v1.model;

import com.ibm.cloud.platform_services.catalog_management.v1.model.AccountGroup;
import com.ibm.cloud.platform_services.catalog_management.v1.model.CategoryFilter;
import com.ibm.cloud.platform_services.catalog_management.v1.model.Enterprise;
import com.ibm.cloud.platform_services.catalog_management.v1.model.EnterpriseAccountGroups;
import com.ibm.cloud.platform_services.catalog_management.v1.model.FilterTerms;
import com.ibm.cloud.platform_services.catalog_management.v1.model.Filters;
import com.ibm.cloud.platform_services.catalog_management.v1.model.IDFilter;
import com.ibm.cloud.platform_services.catalog_management.v1.utils.TestUtilities;

import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Enterprise model.
 */
public class EnterpriseTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testEnterprise() throws Throwable {
    FilterTerms filterTermsModel = new FilterTerms.Builder()
      .filterTerms(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
      .build();
    assertEquals(filterTermsModel.filterTerms(), new java.util.ArrayList<String>(java.util.Arrays.asList("testString")));

    CategoryFilter categoryFilterModel = new CategoryFilter.Builder()
      .include(true)
      .filter(filterTermsModel)
      .build();
    assertEquals(categoryFilterModel.include(), Boolean.valueOf(true));
    assertEquals(categoryFilterModel.filter(), filterTermsModel);

    IDFilter idFilterModel = new IDFilter.Builder()
      .include(filterTermsModel)
      .exclude(filterTermsModel)
      .build();
    assertEquals(idFilterModel.include(), filterTermsModel);
    assertEquals(idFilterModel.exclude(), filterTermsModel);

    Filters filtersModel = new Filters.Builder()
      .includeAll(true)
      .categoryFilters(new java.util.HashMap<String,CategoryFilter>(){{put("foo", categoryFilterModel); }})
      .idFilters(idFilterModel)
      .build();
    assertEquals(filtersModel.includeAll(), Boolean.valueOf(true));
    assertEquals(filtersModel.categoryFilters(), new java.util.HashMap<String,CategoryFilter>(){{put("foo", categoryFilterModel); }});
    assertEquals(filtersModel.idFilters(), idFilterModel);

    AccountGroup accountGroupModel = new AccountGroup.Builder()
      .id("testString")
      .accountFilters(filtersModel)
      .build();
    assertEquals(accountGroupModel.id(), "testString");
    assertEquals(accountGroupModel.accountFilters(), filtersModel);

    EnterpriseAccountGroups enterpriseAccountGroupsModel = new EnterpriseAccountGroups.Builder()
      .keys(accountGroupModel)
      .build();
    assertEquals(enterpriseAccountGroupsModel.keys(), accountGroupModel);

    Enterprise enterpriseModel = new Enterprise.Builder()
      .id("testString")
      .rev("testString")
      .accountFilters(filtersModel)
      .accountGroups(enterpriseAccountGroupsModel)
      .build();
    assertEquals(enterpriseModel.id(), "testString");
    assertEquals(enterpriseModel.rev(), "testString");
    assertEquals(enterpriseModel.accountFilters(), filtersModel);
    assertEquals(enterpriseModel.accountGroups(), enterpriseAccountGroupsModel);

    String json = TestUtilities.serialize(enterpriseModel);

    Enterprise enterpriseModelNew = TestUtilities.deserialize(json, Enterprise.class);
    assertTrue(enterpriseModelNew instanceof Enterprise);
    assertEquals(enterpriseModelNew.id(), "testString");
    assertEquals(enterpriseModelNew.rev(), "testString");
    assertEquals(enterpriseModelNew.accountFilters().toString(), filtersModel.toString());
    assertEquals(enterpriseModelNew.accountGroups().toString(), enterpriseAccountGroupsModel.toString());
  }
}