package um.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import um.pojo.Admin;

@Transactional
@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	// me retorna la sesion hibernate, invoca al get
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Admin admin) {
		getSession().save(admin);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Admin> findAll() {

		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("from Admin"); // MySQL --> select * from admin;

		return query.list();
	}

	//cuando seleccionas uno, te redirecciona a la pagina y te muestra ese id que seleccionaste te sale la consulta hecha en la terminal
	@Override
	public Admin findById(int id) {

		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.eq("idAd", id));

		return (Admin) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findByNombre(String nombre) {
		
		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.like("nombre", "%" +  nombre + "%"));

		return crit.list();
	}

	@Override
	public void update(Admin admin) {
		getSession().update(admin);
	}

	@Override
	public void delete(Admin admin){
		getSession().delete(admin);
		
		//hibernate trabaja con instancias del objeto
		//por eso le pasamos admin y no el id
	}

}
