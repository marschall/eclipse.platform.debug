/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.debug.internal.ui.actions.breakpointGroups;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointsView;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 */
public class AddBreakpointToGroupAction implements IViewActionDelegate {
	
	private Object[] fBreakpoints= null;
	private BreakpointsView fView= null;
	
	private static String fgLastValue= null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		String initialValue= fgLastValue;
		if (initialValue == null) {
		    initialValue= fView.getAutoGroup();
		}
		SelectBreakpointGroupDialog dialog = new SelectBreakpointGroupDialog(fView, BreakpointGroupMessages.getString("AddBreakpointToGroupAction.0"), BreakpointGroupMessages.getString("AddBreakpointToGroupAction.1"), initialValue, null); //$NON-NLS-1$ //$NON-NLS-2$
		int dialogResult = dialog.open();
		if (dialogResult == Window.OK) {
			String value= dialog.getValue();
			if (value.equals("")) { //$NON-NLS-1$
				value= null;
			}
			fgLastValue= value;
			try {
				for (int i = 0; i < fBreakpoints.length; i++) {
					((IBreakpoint) fBreakpoints[i]).setGroup(value);
				}
			} catch (CoreException e) {
				DebugUIPlugin.errorDialog(dialog.getShell(), BreakpointGroupMessages.getString("AddBreakpointToGroupAction.3"), BreakpointGroupMessages.getString("AddBreakpointToGroupAction.4"), e); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection sel) {
		IStructuredSelection selection= (IStructuredSelection) sel;
		Iterator iterator = selection.iterator();
		while (iterator.hasNext()) {
			if (!(iterator.next() instanceof IBreakpoint)) {
				action.setEnabled(false);
				fBreakpoints= null;
				return;
			}
		}
		action.setEnabled(true);
		fBreakpoints= selection.toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		fView= (BreakpointsView) view;
	}

}
