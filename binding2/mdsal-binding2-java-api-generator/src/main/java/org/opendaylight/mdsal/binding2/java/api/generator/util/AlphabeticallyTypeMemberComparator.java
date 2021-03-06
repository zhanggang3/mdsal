/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.mdsal.binding2.java.api.generator.util;

import java.util.Comparator;
import org.opendaylight.mdsal.binding2.model.api.TypeMember;

/**
 * Alphabetically type member {@link Comparator} which provides sorting by name for type members
 * (variables and methods) in generated class.
 *
 * @param <T>
 */
public class AlphabeticallyTypeMemberComparator<T extends TypeMember> implements Comparator<T>{

    @Override
    public int compare(T member1, T member2) {
        return member1.getName().compareTo(member2.getName());
    }
}
