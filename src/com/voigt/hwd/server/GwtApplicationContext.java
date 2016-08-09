/**
 * 
 */
package com.voigt.hwd.server;

import java.io.Serializable;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Match;
import com.voigt.hwd.domain.MatchDay;
import com.voigt.hwd.domain.Season;
import com.voigt.hwd.server.service.IGenericService;
import com.voigt.hwd.server.service.IIdentificationService;
import com.voigt.hwd.server.service.IMatchFixtureService;
import com.voigt.hwd.server.service.ITeamService;

/**
 * Global application context
 * 
 * @author bruno.marchesson
 * 
 */
public class GwtApplicationContext {
	/**
	 * The stateless configuration file
	 */
	private static final String STATELESS_CONTEXT_FILE = "com/voigt/hwd/resources/applicationContext.xml";

	/**
	 * Unique instance of the singleton
	 */
	private static GwtApplicationContext _instance;

	/**
	 * the Spring context
	 */
	protected GenericApplicationContext springContext;

	public static synchronized final GwtApplicationContext getInstance() {
		if (_instance == null) {
			_instance = new GwtApplicationContext();
		}
		return _instance;
	}

	private GwtApplicationContext() {
		initContextFile();
	}

	/**
	 * Get a bean from its name
	 */
	public Object getBean(String beanName) {
		return springContext.getBean(beanName);
	}

	/**
	 * Init Spring context
	 */
	private void initContextFile() {
		springContext = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(springContext);
		xmlReader.loadBeanDefinitions(new ClassPathResource(STATELESS_CONTEXT_FILE));
		springContext.refresh();
	}

	public ITeamService getTeamService() {
		return (ITeamService) springContext.getBean(ITeamService.NAME);
		// return (ITeamService) springContext.getBean("teamServiceProxy");
	}

	public IMatchFixtureService getMatchFixtureService() {
		return (IMatchFixtureService) springContext.getBean(IMatchFixtureService.NAME + "Proxy");
		// return (IMatchFixtureService)
		// springContext.getBean(IMatchFixtureService.NAME);
		// return (ITeamService) springContext.getBean("teamServiceProxy");
	}

	public IIdentificationService getIdentificationService() {
		return (IIdentificationService) springContext.getBean(IIdentificationService.NAME);
	}

	@SuppressWarnings("unchecked")
	public IGenericService<Match, Serializable> getMatchService() {
		IGenericService<Match, Serializable> matchService = (IGenericService<Match, Serializable>) springContext
				.getBean("matchService");
		return matchService;
	}

	@SuppressWarnings("unchecked")
	public IGenericService<League, Serializable> getLeagueService() {
		IGenericService<League, Serializable> leagueService = (IGenericService<League, Serializable>) springContext
				.getBean("leagueService");
		return leagueService;
	}

	@SuppressWarnings("unchecked")
	public IGenericService<Season, Serializable> getSeasonService() {
		IGenericService<Season, Serializable> seasonService = (IGenericService<Season, Serializable>) springContext
				.getBean("seasonService");
		return seasonService;
	}

	@SuppressWarnings("unchecked")
	public IGenericService<MatchDay, Serializable> getMatchDayService() {
		IGenericService<MatchDay, Serializable> matchService = (IGenericService<MatchDay, Serializable>) springContext
				.getBean("matchDayService");
		return matchService;
	}

}
