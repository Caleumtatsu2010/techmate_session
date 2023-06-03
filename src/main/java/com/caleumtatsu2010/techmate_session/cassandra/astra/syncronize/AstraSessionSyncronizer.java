package com.caleumtatsu2010.techmate_session.cassandra.astra.syncronize;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.dao.account.CASAccountDao;
import com.caleumtatsu2010.cassandra.dao.cart.CASCartDao;
import com.caleumtatsu2010.cassandra.dao.cart.item.CASCartItemDao;
import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.cassandra.models.database.CASPath;
import com.caleumtatsu2010.cassandra.models.database.astra.AstraDatabases;
import com.caleumtatsu2010.cassandra.models.database.astra.KeySpace;
import com.caleumtatsu2010.techmate_session.cassandra.SessionSyncronizer;
import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
import com.caleumtatsu2010.utility.object.reflect.ObjectUtilityInvoker;

import java.util.List;
import java.util.UUID;

public class AstraSessionSyncronizer implements SessionSyncronizer {
	
	private AstraConnector astraConnector;
	private AstraSession astraSession;
	private String astraKeyspace;
	
	
	public void AstraSessionSyncronizer(AstraConnector astraConnector, AstraSession casSession, String astraKeyspace) {
		this.astraConnector = astraConnector;
		this.astraSession = casSession;
		this.astraKeyspace = astraKeyspace;
	}
	
	public void syncToAstraObject(Object objectDao, Object param, Object uuid) {
		Object methodReturn = ObjectUtilityInvoker.invokeGetMethod(objectDao, "get", uuid);
		if (methodReturn != null) {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "update", param, uuid);
		} else {
			ObjectUtilityInvoker.invokeSetMethod(objectDao, "insert", param);
		}
	}
	
	
	@Override
	public void syncSessionToCAS(AstraSession casSession) {
	
//		CASCartDao casCartDao = new CASCartDao(this.astraConnector, KeySpace.techmate);
//		CASCart casCart = casSession.getCasCart();
//		if (casCartDao.get(casCart.getId()) != null) {
//			casCartDao.update(casCart, casCart.getId());
//		} else {
//			casCartDao.insert(casCart);
//		}
		
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
