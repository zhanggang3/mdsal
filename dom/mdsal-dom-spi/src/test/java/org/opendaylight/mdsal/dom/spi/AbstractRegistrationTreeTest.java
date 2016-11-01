/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.mdsal.dom.spi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;

public class AbstractRegistrationTreeTest extends AbstractRegistrationTree<Object> {

    @Test
    public void basicTest() throws Exception {
        final PathArgument pathArgument = mock(PathArgument.class);
        final RegistrationTreeNode<Object> registrationTreeNodeParent = new RegistrationTreeNode<>(null, pathArgument);
        final RegistrationTreeNode<Object> registrationTreeNode =
                new RegistrationTreeNode<>(registrationTreeNodeParent, pathArgument);

        final Object registration = new Object();
        this.takeLock();
        this.addRegistration(registrationTreeNode, registration);
        assertTrue(registrationTreeNode.getRegistrations().contains(registration));
        this.releaseLock();

        this.removeRegistration(registrationTreeNode, registration);
        assertFalse(registrationTreeNode.getRegistrations().contains(registration));

        assertNotNull(this.findNodeFor(ImmutableList.of(pathArgument)));
        assertNotNull(this.takeSnapshot());
    }

    @Test(expected = IllegalMonitorStateException.class)
    public void unlockTest() throws Exception {
        this.releaseLock();
    }
}