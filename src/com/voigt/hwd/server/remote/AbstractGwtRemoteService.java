package com.voigt.hwd.server.remote;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.gwt.PersistentRemoteService;

import com.voigt.hwd.server.GwtApplicationContext;

public abstract class AbstractGwtRemoteService extends PersistentRemoteService {

    public AbstractGwtRemoteService() {
	super();
	// set the configured bean manager
	GwtApplicationContext context = GwtApplicationContext.getInstance();
	PersistentBeanManager persistentBeanManager = (PersistentBeanManager) context.getBean("beanManager");
	setBeanManager(persistentBeanManager);
    }

    protected GwtApplicationContext getContext() {
	return GwtApplicationContext.getInstance();
    }

}
