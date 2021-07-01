package it.plansoft.skills.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.plansoft.skills.Service.BaseCrudService;

/**
 * 	Xontroller astratto con service e logger
 * @author ecalvisi
 *
 * @param <SERVICE>
 */

public abstract class AbstractController<SERVICE extends BaseCrudService> {
	
	// (Service + Log)
	protected final static Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	protected SERVICE service;
}
