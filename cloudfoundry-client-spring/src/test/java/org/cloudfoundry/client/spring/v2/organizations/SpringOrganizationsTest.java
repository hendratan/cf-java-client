/*
 * Copyright 2013-2015 the original author or authors.
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

package org.cloudfoundry.client.spring.v2.organizations;

import org.cloudfoundry.client.RequestValidationException;
import org.cloudfoundry.client.spring.AbstractRestTest;
import org.cloudfoundry.client.v2.CloudFoundryException;
import org.cloudfoundry.client.v2.organizations.AssociateAuditorRequest;
import org.cloudfoundry.client.v2.organizations.AssociateAuditorResponse;
import org.cloudfoundry.client.v2.organizations.AssociateBillingManagerRequest;
import org.cloudfoundry.client.v2.organizations.AssociateBillingManagerResponse;
import org.cloudfoundry.client.v2.organizations.AssociateSpaceManagerRequest;
import org.cloudfoundry.client.v2.organizations.AssociateSpaceManagerResponse;
import org.cloudfoundry.client.v2.organizations.ListOrganizationsRequest;
import org.cloudfoundry.client.v2.organizations.ListOrganizationsResponse;
import org.cloudfoundry.client.v2.organizations.OrganizationEntity;
import org.junit.Test;
import reactor.rx.Streams;

import static org.cloudfoundry.client.v2.Resource.Metadata;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public final class SpringOrganizationsTest extends AbstractRestTest {

    private final SpringOrganizations organizations = new SpringOrganizations(this.restTemplate, this.root);

    @Test
    public void associateAuditor() {
        mockRequest(new RequestContext()
                .method(PUT).path("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/auditors/uaa-id-71")
                .status(CREATED)
                .responsePayload("v2/organizations/auditors/PUT_{id}_response.json"));

        AssociateAuditorRequest request = AssociateAuditorRequest.builder()
                .auditorId("uaa-id-71")
                .organizationId("83c4fac5-cd9e-41ee-96df-b4f50fff4aef")
                .build();

        AssociateAuditorResponse expected = AssociateAuditorResponse.builder()
                .metadata(Metadata.builder()
                        .id("83c4fac5-cd9e-41ee-96df-b4f50fff4aef")
                        .url("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef")
                        .createdAt("2015-07-27T22:43:10Z")
                        .build())
                .entity(OrganizationEntity.builder()
                        .name("name-187")
                        .billingEnabled(false)
                        .quotaDefinitionId("1d18a00b-4e36-412b-9308-2f5f2402e880")
                        .status("active")
                        .quotaDefinitionUrl("/v2/quota_definitions/1d18a00b-4e36-412b-9308-2f5f2402e880")
                        .spacesUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/spaces")
                        .domainsUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/domains")
                        .privateDomainsUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/private_domains")
                        .usersUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/users")
                        .managersUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/managers")
                        .billingManagersUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/billing_managers")
                        .auditorsUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/auditors")
                        .applicationEventsUrl("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/app_events")
                        .spaceQuotaDefinitionsUrl
                                ("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/space_quota_definitions")
                        .build())
                .build();

        AssociateAuditorResponse actual = Streams.wrap(this.organizations.associateAuditor(request)).next().get();

        assertEquals(expected, actual);
        verify();
    }

    @Test(expected = CloudFoundryException.class)
    public void associateAuditorError() {
        mockRequest(new RequestContext()
                .method(PUT).path("/v2/organizations/83c4fac5-cd9e-41ee-96df-b4f50fff4aef/auditors/uaa-id-71")
                .errorResponse());

        AssociateAuditorRequest request = AssociateAuditorRequest.builder()
                .auditorId("uaa-id-71")
                .organizationId("83c4fac5-cd9e-41ee-96df-b4f50fff4aef")
                .build();

        Streams.wrap(this.organizations.associateAuditor(request)).next().get();
    }

    @Test(expected = RequestValidationException.class)
    public void associateAuditorInvalidRequest() throws Throwable {
        AssociateAuditorRequest request = AssociateAuditorRequest.builder()
                .build();

        Streams.wrap(this.organizations.associateAuditor(request)).next().get();
    }

    @Test
    public void associateBillingManager() {
        mockRequest(new RequestContext()
                .method(PUT).path("v2/organizations/test-id/billing_managers/test-billing-manager-id")
                .status(OK)
                .responsePayload("v2/organizations/PUT_{id}_billing_managers_{billing-manager-id}_response.json"));

        AssociateBillingManagerRequest request = AssociateBillingManagerRequest.builder()
                .billingManagerId("test-billing-manager-id")
                .id("test-id")
                .build();

        AssociateBillingManagerResponse expected = AssociateBillingManagerResponse.builder()
                .entity(OrganizationEntity.builder()
                        .applicationEventsUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/app_events")
                        .auditorsUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/auditors")
                        .billingEnabled(false)
                        .billingManagersUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/billing_managers")
                        .domainsUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/domains")
                        .managersUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/managers")
                        .name("name-200")
                        .privateDomainsUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/private_domains")
                        .quotaDefinitionId("ab51f0d8-1920-4bfc-9401-cd0e978e8c5e")
                        .quotaDefinitionUrl("/v2/quota_definitions/ab51f0d8-1920-4bfc-9401-cd0e978e8c5e")
                        .spaceQuotaDefinitionsUrl
                                ("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/space_quota_definitions")
                        .spacesUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/spaces")
                        .status("active")
                        .usersUrl("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8/users")
                        .build())
                .metadata(Metadata.builder()
                        .createdAt("2015-07-27T22:43:10Z")
                        .id("39ab104d-79f9-4bac-82e0-35b826a236b8")
                        .url("/v2/organizations/39ab104d-79f9-4bac-82e0-35b826a236b8")
                        .build())
                .build();

        AssociateBillingManagerResponse actual = Streams.wrap(this.organizations.associateBillingManager(request))
                .next().get();

        assertEquals(expected, actual);
        verify();
    }

    @Test(expected = CloudFoundryException.class)
    public void associateBillingManagerError() {
        mockRequest(new RequestContext()
                .method(PUT).path("v2/organizations/test-id/billing_managers/test-billing-manager-id")
                .errorResponse());

        AssociateBillingManagerRequest request = AssociateBillingManagerRequest.builder()
                .billingManagerId("test-billing-manager-id")
                .id("test-id")
                .build();

        Streams.wrap(this.organizations.associateBillingManager(request)).next().get();
    }

    @Test(expected = RequestValidationException.class)
    public void associateBillingManagerInvalidRequest() {
        AssociateBillingManagerRequest request = AssociateBillingManagerRequest.builder()
                .build();

        Streams.wrap(this.organizations.associateBillingManager(request)).next().get();
    }

    @Test
    public void associateManager() {
        mockRequest(new RequestContext()
                .method(PUT).path("v2/organizations/test-id/managers/test-manager-id")
                .status(OK)
                .responsePayload("v2/organizations/PUT_{id}_managers_{manager-id}_response.json"));

        AssociateSpaceManagerRequest request = AssociateSpaceManagerRequest.builder()
                .id("test-id")
                .managerId("test-manager-id")
                .build();

        AssociateSpaceManagerResponse expected = AssociateSpaceManagerResponse.builder()
                .metadata(Metadata.builder()
                        .id("cc7c5224-f973-4358-a95a-dd72decbb20f")
                        .url("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f")
                        .createdAt("2015-07-27T22:43:10Z")
                        .build())
                .entity(OrganizationEntity.builder()
                        .name("name-218")
                        .billingEnabled(false)
                        .quotaDefinitionId("57f59bb7-7581-4257-9502-cbd60bb92d99")
                        .status("active")
                        .quotaDefinitionUrl("/v2/quota_definitions/57f59bb7-7581-4257-9502-cbd60bb92d99")
                        .spacesUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/spaces")
                        .domainsUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/domains")
                        .privateDomainsUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/private_domains")
                        .usersUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/users")
                        .managersUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/managers")
                        .billingManagersUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/billing_managers")
                        .auditorsUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/auditors")
                        .applicationEventsUrl("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/app_events")
                        .spaceQuotaDefinitionsUrl
                                ("/v2/organizations/cc7c5224-f973-4358-a95a-dd72decbb20f/space_quota_definitions")
                        .build())
                .build();

        AssociateSpaceManagerResponse actual = Streams.wrap(this.organizations.associateManager(request)).next().get();

        assertEquals(expected, actual);
        verify();
    }

    @Test(expected = CloudFoundryException.class)
    public void associateManagerError() {
        mockRequest(new RequestContext()
                .method(PUT).path("v2/organizations/test-id/managers/test-manager-id")
                .errorResponse());

        AssociateSpaceManagerRequest request = AssociateSpaceManagerRequest.builder()
                .id("test-id")
                .managerId("test-manager-id")
                .build();

        Streams.wrap(this.organizations.associateManager(request)).next().get();
    }

    @Test(expected = RequestValidationException.class)
    public void associateManagerInvalidRequest() {
        AssociateSpaceManagerRequest request = AssociateSpaceManagerRequest.builder()
                .build();

        Streams.wrap(this.organizations.associateManager(request)).next().get();
    }

    @Test
    public void list() {
        mockRequest(new RequestContext()
                .method(GET).path("/v2/organizations?q=name%20IN%20test-name&page=-1")
                .status(OK)
                .responsePayload("v2/organizations/GET_response.json"));

        ListOrganizationsRequest request = ListOrganizationsRequest.builder()
                .name("test-name")
                .page(-1)
                .build();

        ListOrganizationsResponse expected = ListOrganizationsResponse.builder()
                .totalResults(1)
                .totalPages(1)
                .resource(ListOrganizationsResponse.Resource.builder()
                        .metadata(Metadata.builder()
                                .id("deb3c359-2261-45ba-b34f-ee7487acd71a")
                                .url("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a")
                                .createdAt("2015-07-27T22:43:05Z")
                                .build())
                        .entity(OrganizationEntity.builder()
                                .name("the-system_domain-org-name")
                                .billingEnabled(false)
                                .quotaDefinitionId("9b56a1ec-4981-4a1e-9348-0d78eeca842c")
                                .status("active")
                                .quotaDefinitionUrl("/v2/quota_definitions/9b56a1ec-4981-4a1e-9348-0d78eeca842c")
                                .spacesUrl("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/spaces")
                                .domainsUrl("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/domains")
                                .privateDomainsUrl
                                        ("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/private_domains")
                                .usersUrl("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/users")
                                .managersUrl("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/managers")
                                .billingManagersUrl
                                        ("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/billing_managers")
                                .auditorsUrl("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/auditors")
                                .applicationEventsUrl
                                        ("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/app_events")
                                .spaceQuotaDefinitionsUrl
                                        ("/v2/organizations/deb3c359-2261-45ba-b34f-ee7487acd71a/space_quota_definitions")
                                .build())
                        .build())
                .build();

        ListOrganizationsResponse actual = Streams.wrap(this.organizations.list(request)).next().get();

        assertEquals(expected, actual);
        verify();
    }

    @Test(expected = CloudFoundryException.class)
    public void listError() {
        mockRequest(new RequestContext()
                .method(GET).path("/v2/organizations?q=name%20IN%20test-name&page=-1")
                .errorResponse());

        ListOrganizationsRequest request = ListOrganizationsRequest.builder()
                .name("test-name")
                .page(-1)
                .build();

        Streams.wrap(this.organizations.list(request)).next().get();
    }

}