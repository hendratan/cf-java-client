/*
 * Copyright 2013-2017 the original author or authors.
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

package org.cloudfoundry.client.v2;

import org.cloudfoundry.AbstractIntegrationTest;
import org.cloudfoundry.client.CloudFoundryClient;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.fail;

public final class ServiceUsageEventsTest extends AbstractIntegrationTest {

    @Autowired
    private CloudFoundryClient cloudFoundryClient;

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void get() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void list() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void listAfterServiceUsageEventId() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void listFilterByServiceId() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void listFilterByServiceInstanceType() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void listNoneFound() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

    @Ignore("TODO: awaiting story https://www.pivotaltracker.com/story/show/118387501")
    @Test
    public void purgeAndReseed() {
        fail("TODO: finish story https://www.pivotaltracker.com/story/show/118387501");
    }

}
