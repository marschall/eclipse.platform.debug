/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.debug.core;

import org.eclipse.debug.core.model.IMemoryBlock;

/**
 * A memory block listener is notified of the addition and removal
 * of memory blocks with the memory block manager. Listeners must
 * register with the memory block manager for notification.
 * <p>
 * Clients may implement this interface.
 * </p>
 * @since 3.1
 */
public interface IMemoryBlockListener {
	
	/**
	 * Notification the given memory blocks have been added to the
	 * memory block manager.
	 * 
	 * @param memory blocks added to the memory block manager
	 */
	void memoryBlocksAdded(IMemoryBlock[] memory);

	/**
	 * Notification the given memory blocks have been removed from
	 * the memory block manager.
	 * 
	 * @param memory blocks removed from the memory block manager
	 */
	void memoryBlocksRemoved(IMemoryBlock[] memory);
	
}