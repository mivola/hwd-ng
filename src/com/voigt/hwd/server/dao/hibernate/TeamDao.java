package com.voigt.hwd.server.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.voigt.hwd.domain.League;
import com.voigt.hwd.domain.Team;
import com.voigt.hwd.server.dao.ITeamDao;
import com.voigt.hwd.server.util.Utils;

/**
 * DAO for team beans.
 * 
 */
public class TeamDao extends GenericDaoHibernateImpl<Team, Serializable> implements ITeamDao {

	public TeamDao() {
		super(Team.class);
	}

	public List<Team> getTeams(League league) {
		Criteria crit = getSession().createCriteria(Team.class);
		crit.add(Restrictions.eq(Team.LEAGUES, league));

		List<Team> list = Utils.cast(crit.list());

		return list;
	}

	@Override
	public Team getByOLId(int oLId) {
		Criteria crit = getSession().createCriteria(Team.class);
		crit.add(Restrictions.eq(Team.OL_ID, oLId));
		return (Team) crit.uniqueResult();
	}
}