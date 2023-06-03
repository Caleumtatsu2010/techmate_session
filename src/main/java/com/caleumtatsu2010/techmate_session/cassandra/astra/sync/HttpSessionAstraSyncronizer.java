package com.caleumtatsu2010.techmate_session.cassandra.astra.sync;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.dao.cart.CASCartDao;
import com.caleumtatsu2010.cassandra.dao.cart.item.CASCartItemDao;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.cassandra.models.database.astra.KeySpace;
import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

import java.util.List;

public class HttpSessionAstraSyncronizer implements HttpSessionSyncronizer {
	
	private AstraConnector astraConnector;
	private String astraKeySpace;
	
	public void AstraSessionSyncronizer(AstraConnector astraConnector, AstraSession casSession, String astraKeyspace) {
		this.astraConnector = astraConnector;
	}
	
	public void syncAstraCrud(Object objectDao, Object param, Object uuid) {
		Object methodReturn = ObjectUtilityInvoker.invokeGetMethod(objectDao, "get", uuid);
		if (methodReturn != null) {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "update", param, uuid);
		} else {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "insert", param);
		}
	}
	
	
	@Override
	public void syncHttpSession(AstraSession casSession) {
	
		CASCartDao casCartDao = new CASCartDao(this.astraConnector, this.astraKeySpace);
		CASCart casCart = casSession.getCasCart();
		syncAstraCrud(casCartDao, )
		if (casCartDao.get(casCart.getId()) != null) {
			casCartDao.update(casCart, casCart.getId());
		} else {
			casCartDao.insert(casCart);
		}
		
		syncAstraCrud()
		
		CASCartItemDao casCartItemDao = new CASCartItemDao(this.astraConnector, KeySpace.techmate);
		List<CASCartItem> casCartItemList = casSession.getCasCartItemList();
		for (int i = 0; i < casCartItemList.size(); i++) {
			if (casCartItemDao.get(casCartItemList.get(i).getId()) != null) {
				casCartItemDao.update(casCartItemList.get(i), casCartItemList.get(i).getId());
			} else {
				casCartItemDao.insert(casCartItemList.get(i));
			}
		}
	}
	
	
}
